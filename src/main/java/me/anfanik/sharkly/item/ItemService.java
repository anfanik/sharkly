package me.anfanik.sharkly.item;

import me.anfanik.sharkly.Sharkly;
import me.anfanik.sharkly.service.Service;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemService extends Service {

    static ItemService get() {
        return Sharkly.getService(ItemService.class);
    }

    void makeActive(ItemStack item, ItemUseHandler handler);

    void makeActiveOnce(ItemStack item, ItemUseHandler handler);

    void unmakeActive(ItemStack item);

    void unmakeActive(ItemStack item, ItemUseHandler handler);

    void lock(ItemStack item);

    void unlock(ItemStack item);

    void lockSlots(int... slots);

    void unlockSlots(int... slots);

    void lockSlots(Player player, int... slots);

    void unlockSlots(Player player, int... slots);

}
