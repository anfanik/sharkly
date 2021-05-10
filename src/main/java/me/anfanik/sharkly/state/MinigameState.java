package me.anfanik.sharkly.state;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class MinigameState implements Listener {

    protected final Plugin plugin;
    private final List<Listener> listeners = new ArrayList<>();

    public MinigameState(Plugin plugin) {
        this.plugin = plugin;
    }

    public final void enable() {
        registerListener(this);
        onEnable();
    }

    public void onEnable() {
    }

    public final void disable() {
        listeners.forEach(HandlerList::unregisterAll);
        onDisable();
    }

    public void onDisable() {
    }

    public void registerListener(Listener listener) {
        listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    public void unregisterListener(Listener listener) {
         listeners.remove(listener);
         HandlerList.unregisterAll(listener);
    }

}
