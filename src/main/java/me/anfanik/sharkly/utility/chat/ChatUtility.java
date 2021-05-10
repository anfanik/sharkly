package me.anfanik.sharkly.utility.chat;

import lombok.experimental.UtilityClass;
import lombok.val;
import me.anfanik.sharkly.utility.Formatter;
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

    public void sendMessage(Player player, ChatLevel level, String message, Object... arguments) {
        sendMessage(player, level.format(Formatter.format(message, arguments)));
    }

    public void broadcastMessage(ChatLevel level, String message, Object... arguments) {
        val formatted = level.format(Formatter.format(message, arguments));
        Bukkit.getOnlinePlayers().forEach(player -> sendMessage(player, formatted));
    }

    public void fine(Player player, String message, Object... arguments) {
        sendMessage(player, ChatLevels.FINE, message, arguments);
    }

    public void fine(PlayerWrapper wrapper, String message, Object... arguments) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.FINE, message, arguments));
    }

    public void broadcastFine(String message, Object... arguments) {
        broadcastMessage(ChatLevels.FINE, message, arguments);
    }

    public void warning(Player player, String message, Object... arguments) {
        sendMessage(player, ChatLevels.WARNING, message, arguments);
    }

    public void warning(PlayerWrapper wrapper, String message, Object... arguments) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.WARNING, message, arguments));
    }

    public void broadcastWarning(String message, Object... arguments) {
        broadcastMessage(ChatLevels.WARNING, message, arguments);
    }

    public void error(Player player, String message, Object... arguments) {
        sendMessage(player, ChatLevels.ERROR, message, arguments);
    }

    public void error(PlayerWrapper wrapper, String message, Object... arguments) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.ERROR, message, arguments));
    }

    public void broadcastError(String message, Object... arguments) {
        broadcastMessage(ChatLevels.ERROR, message, arguments);
    }

    public void debug(Player player, String message, Object... arguments) {
        sendMessage(player, ChatLevels.DEBUG, message, arguments);
    }

    public void debug(PlayerWrapper wrapper, String message, Object... arguments) {
        wrapper.ifPlayer(player -> sendMessage(player, ChatLevels.DEBUG, message, arguments));
    }

    public void broadcastDebug(String message, Object... arguments) {
        broadcastMessage(ChatLevels.DEBUG, message, arguments);
    }

}
