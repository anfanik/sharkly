package me.anfanik.sharkly.state;

import me.anfanik.sharkly.Sharkly;
import me.anfanik.sharkly.SharklyLogger;
import org.bukkit.plugin.Plugin;

public class StateMachine {

    private static boolean initialized = false;
    private static MinigameState state;

    public static void initialize() {
        if (initialized) {
            SharklyLogger.error("State machine is already initialized!", new IllegalStateException());
            return;
        }

        Plugin plugin = Sharkly.getPlugin();
        state = new DefaultMinigameState(plugin);
        initialized = true;
    }

    public static void setState(MinigameState state) {
        if (StateMachine.state != null) {
            StateMachine.state.disable();
        }
        StateMachine.state = state;
        state.enable();
    }

}
