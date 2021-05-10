package me.anfanik.sharkly.state;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.Plugin;

public class DefaultMinigameState extends MinigameState {

    public DefaultMinigameState(Plugin plugin) {
        super(plugin);
    }

    @EventHandler
    private void disableJoin(AsyncPlayerPreLoginEvent event) {
        event.disallow(
                AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                "§cИзвините, сервер недоступен."
        );
    }

}
