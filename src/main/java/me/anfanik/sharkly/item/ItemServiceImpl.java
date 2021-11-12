package me.anfanik.sharkly.item;

import com.google.common.base.Preconditions;
import lombok.val;
import me.anfanik.sharkly.utility.Hand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemServiceImpl implements ItemService, Listener {

    private final Map<ItemStack, List<ItemUseHandler>> activeItems = new HashMap<>();
    private final List<ItemStack> lockedItems = new ArrayList<>();
    private final List<Integer> lockedSlots = new ArrayList<>();
    private final Map<Player, List<Integer>> personallyLockedSlots = new HashMap<>();

    private boolean ignoreCancelledEvents = false;

    @Override
    public void makeActive(ItemStack item, ItemUseHandler handler) {
        Preconditions.checkNotNull(item, "item is null");
        Preconditions.checkNotNull(handler, "handler is null");
        activeItems.computeIfAbsent(item, ignored -> new ArrayList<>()).add(handler);
    }

    @Override
    public void makeActiveOnce(ItemStack item, ItemUseHandler handler) {
        Preconditions.checkNotNull(item, "item is null");
        Preconditions.checkNotNull(handler, "handler is null");
        makeActive(item, (player, hand) -> {
            handler.handle(player, hand);
            unmakeActive(item, handler);
        });
    }

    @Override
    public void unmakeActive(ItemStack item) {
        Preconditions.checkNotNull(item, "item is null");
        activeItems.remove(item);
    }

    @Override
    public void unmakeActive(ItemStack item, ItemUseHandler handler) {
        Preconditions.checkNotNull(item, "item is null");
        Preconditions.checkNotNull(handler, "handler is null");
        activeItems.computeIfPresent(item, (ignored, handlers) -> {
            handlers.remove(handler);
            return handlers.isEmpty() ? null : handlers;
        });
    }

    @Override
    public void lock(ItemStack item) {
        Preconditions.checkNotNull(item, "item is null");
        lockedItems.add(item);
    }

    @Override
    public void unlock(ItemStack item) {
        Preconditions.checkNotNull(item, "item is null");
        lockedItems.remove(item);
    }

    @Override
    public void lockSlots(int... slots) {
        for (int slot : slots) {
            lockedSlots.add(slot);
        }
    }

    @Override
    public void unlockSlots(int... slots) {
        for (int slot : slots) {
            lockedSlots.remove(slot);
        }
    }

    @Override
    public void lockSlots(Player player, int... slots) {
        Preconditions.checkNotNull(player, "player is null");
        val playerLockedSlots = personallyLockedSlots.computeIfAbsent(player, ignored -> new ArrayList<>());
        for (int slot : slots) {
            playerLockedSlots.add(slot);
        }
    }

    @Override
    public void unlockSlots(Player player, int... slots) {
        Preconditions.checkNotNull(player, "player is null");
        personallyLockedSlots.computeIfPresent(player, (ignored, locked) -> {
            for (int slot : slots) {
                locked.remove(slot);
            }
            return locked.isEmpty() ? null : locked;
        });
    }

    public void setIgnoreCancelledEvents(boolean value) {
        this.ignoreCancelledEvents = value;
    }

    @EventHandler
    private void handleUse(PlayerInteractEvent event) {
        if (!event.isCancelled() || !ignoreCancelledEvents) {
            val player = event.getPlayer();
            val item = event.getItem();

            if (item != null && activeItems.containsKey(item)) {
                event.setCancelled(true);
                val handlers = this.activeItems.get(item);
                getHand(event.getAction()).ifPresent(hand ->
                    handlers.forEach(handler -> handler.handle(player, hand)));
            }
        }
    }

    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        val item = event.getCurrentItem();
        if (item != null && lockedItems.contains(item)) {
            event.setCancelled(true);
            return;
        }

        val slot = event.getSlot();
        if (lockedSlots.contains(slot)) {
            event.setCancelled(true);
            return;
        }

        val player = (Player) event.getWhoClicked();
        if (personallyLockedSlots.containsKey(player)) {
            val playerLockedSlots = personallyLockedSlots.get(player);
            if (playerLockedSlots.contains(slot)) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void handleDrop(PlayerDropItemEvent event) {
        val item = event.getItemDrop().getItemStack();
        if (item != null && lockedItems.contains(item)) {
            event.setCancelled(true);
        }
    }

    private Optional<Hand> getHand(Action action) {
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            return Optional.of(Hand.PRIMARY);
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            return Optional.of(Hand.SECONDARY);
        } else {
            return Optional.empty();
        }
    }

}
