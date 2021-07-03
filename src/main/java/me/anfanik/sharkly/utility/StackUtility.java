package me.anfanik.sharkly.utility;

import lombok.val;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

import static me.anfanik.sharkly.utility.ArrayUtility.skipFirst;

public class StackUtility {

    public static Optional<Plugin> findFirstStackClassByPlugin() {
        val originalTrace = Thread.currentThread().getStackTrace();
        try {
            val caller = Class.forName(originalTrace[originalTrace.length - 1].getClassName());
            for (val element : skipFirst(originalTrace, 2)) {
                try {
                    val clazz = Class.forName(element.getClassName());
                    if (clazz != caller) {
                        return Optional.of(JavaPlugin.getProvidingPlugin(clazz));
                    }
                } catch (IllegalArgumentException | IllegalStateException ignored) {
                    // Class is not loaded by plugin
                }
            }
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

}
