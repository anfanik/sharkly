package me.anfanik.sharkly.item;

import java.util.stream.IntStream;

public class InventorySlot {

    public static final int HELMET_SLOT = 39;
    public static final int CHESTPLATE_SLOT = 38;
    public static final int LEGGINGS_SLOT = 37;
    public static final int BOOTS_SLOT = 36;
    public static final int[] ARMOR_SLOTS = { HELMET_SLOT, CHESTPLATE_SLOT, LEGGINGS_SLOT, BOOTS_SLOT };

    public static final int[] HOTBAR_SLOTS = IntStream.range(0, 9).toArray();

}
