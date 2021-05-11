package me.anfanik.sharkly.service;

import org.bukkit.plugin.Plugin;

public interface Service {

    default void enable(Plugin plugin) {
    }

    default void disable() {
    }

}
