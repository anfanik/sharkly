package me.anfanik.sharkly.utility.chat;

import lombok.experimental.UtilityClass;
import lombok.val;
import me.anfanik.sharkly.utility.PlayerWrapper;
import me.anfanik.steda.api.utility.TextUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class ChatUtility {

    public void sendMessage(Player player, String message) {
        if (player != null) {
            player.sendMessage(TextUtility.colorize(message));
        } else {
            throw new IllegalStateException("player is not online");
        }
    }

    public void sendMessage(Player player, ChatLevel level, String message) {
        sendMessage(player, level.format(message));
    }

    public void broadcastMessage(ChatLevel level, String message) {
        val formatted = level.format(message);
        Bukkit.getOnlinePlayers().forEach(player -> sendMessage(player, formatted));
    }

    public void fine(Player player, String message) {
        sendMessage(player, ChatLevels.FINE, message);
    }

    public void fine(PlayerWrapper wrapper, String message) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.FINE, message));
    }

    public void broadcastFine(String message) {
        broadcastMessage(ChatLevels.FINE, message);
    }

    public void warning(Player player, String message) {
        sendMessage(player, ChatLevels.WARNING, message);
    }

    public void warning(PlayerWrapper wrapper, String message) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.WARNING, message));
    }

    public void broadcastWarning(String message) {
        broadcastMessage(ChatLevels.WARNING, message);
    }

    public void error(Player player, String message) {
        sendMessage(player, ChatLevels.ERROR, message);
    }

    public void error(PlayerWrapper wrapper, String message) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.ERROR, message));
    }

    public void broadcastError(String message) {
        broadcastMessage(ChatLevels.ERROR, message);
    }

    public void debug(Player player, String message) {
        sendMessage(player, ChatLevels.DEBUG, message);
    }

    public void debug(PlayerWrapper wrapper, String message) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.DEBUG, message));
    }

    public void broadcastDebug(String message) {
        broadcastMessage(ChatLevels.DEBUG, message);
    }

}
