package me.anfanik.sharkly.item;

import me.anfanik.sharkly.Sharkly;
import me.anfanik.sharkly.service.Service;
import org.bukkit.inventory.ItemStack;

public interface ItemService extends Service {

    static ItemService get() {
        return Sharkly.getService(ItemService.class);
    }

    void makeActive(ItemStack item, ItemUseHandler handler);

    void lock(ItemStack item);

}
