package me.anfanik.sharkly.item;

import com.google.common.base.Preconditions;
import lombok.val;
import me.anfanik.sharkly.utility.Hand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemServiceImpl implements ItemService {

    private final Map<ItemStack, List<ItemUseHandler>> active = new HashMap<>();
    private final List<ItemStack> locked = new ArrayList<>();

    @Override
    public void makeActive(ItemStack item, ItemUseHandler handler) {
        Preconditions.checkNotNull(item, "item is null");
        active.computeIfAbsent(item, ignored -> new ArrayList<>()).add(handler);
    }

    @Override
    public void lock(ItemStack item) {
        Preconditions.checkNotNull(item, "item is null");
        locked.add(item);
    }

    @EventHandler
    private void handleUse(PlayerInteractEvent event) {
        val player = event.getPlayer();
        val item = event.getItem();

        if (item != null && active.containsKey(item)) {
            event.setCancelled(true);
            val handlers = this.active.get(item);
            getHand(event.getAction()).ifPresent(hand ->
                    handlers.forEach(handler -> handler.handle(player, hand)));
        }
    }

    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        val item = event.getCurrentItem();
        if (item != null && locked.contains(item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDrop(PlayerDropItemEvent event) {
        val item = event.getItemDrop().getItemStack();
        if (item != null && locked.contains(item)) {
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
