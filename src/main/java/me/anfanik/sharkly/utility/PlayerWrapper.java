package me.anfanik.sharkly.utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Consumer;

public interface PlayerWrapper {

    String getName();

    UUID getUid();

    default Player getPlayer() {
        return Bukkit.getPlayer(getUid());
    }

    default void ifPlayer(Consumer<Player> consumer) {
        Player player = getPlayer();
        if (player != null) {
            consumer.accept(player);
        }
    }

}
