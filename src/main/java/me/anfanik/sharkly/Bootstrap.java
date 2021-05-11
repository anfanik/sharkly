package me.anfanik.sharkly;

import org.bukkit.plugin.java.JavaPlugin;

public class Bootstrap extends JavaPlugin {

    @Override
    public void onEnable() {
        Sharkly.initialize(this);
    }

}
