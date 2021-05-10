package me.anfanik.sharkly;

import lombok.Getter;
import me.anfanik.sharkly.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Sharkly {

    private static boolean initialized = false;
    @Getter
    private static Plugin plugin;

    private static final Map<Class<? extends Service>, Service> services = new HashMap<>();

    public static void initialize(Plugin plugin) {
        if (initialized) {
            SharklyLogger.error("Sharkly is already initialized!", new IllegalStateException());
            return;
        }

        Sharkly.plugin = plugin;
        initialized = true;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Service> T getService(Class<T> clazz) {
        if (services.containsKey(clazz)) {
            return (T) services.get(clazz);
        } else {
            throw new IllegalStateException(clazz.getSimpleName() + " service is not registered");
        }
    }

    public static <T extends Service> void registerService(Class<T> clazz, T service) {
        if (services.containsKey(clazz)) {
            throw new IllegalStateException(clazz.getSimpleName() + " service is already registered");
        } else {
            if (service instanceof Listener) {
                Bukkit.getPluginManager().registerEvents((Listener) service, plugin);
            }
            service.enable();
            services.put(clazz, service);
        }
    }

    public static <T extends Service> void unregisterService(Class<T> clazz, T service) {
        if (services.containsKey(clazz)) {
            services.remove(clazz);
            if (service instanceof Listener) {
                HandlerList.unregisterAll((Listener) service);
            }
            service.disable();
        } else {
            throw new IllegalStateException(clazz.getSimpleName() + " service is not registered");
        }
    }

}
