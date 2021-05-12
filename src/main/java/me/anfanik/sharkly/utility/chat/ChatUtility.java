package me.anfanik.sharkly.utility.chat;

import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;
import me.anfanik.sharkly.utility.Formatter;
import me.anfanik.sharkly.utility.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class ChatUtility {

    private String prepare(String message, Object... arguments) {
        var formatted = Formatter.replaceArguments(message, arguments);
        formatted = Formatter.format(formatted);
        return formatted;
    }

    public void sendMessage(Player player, String message, Object... arguments) {
        var formatted = prepare(message, arguments);
        player.sendMessage(formatted);
    }

    public void broadcastMessage(String message, Object... arguments) {
        var formatted = prepare(message, arguments);
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(formatted));
    }

    public void sendMessage(Player player, ChatLevel level, String message, Object... arguments) {
        var formatted = prepare(level.format(message), arguments);
        player.sendMessage(formatted);
    }

    public void broadcastMessage(ChatLevel level, String message, Object... arguments) {
        var formatted = prepare(level.format(message), arguments);
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(formatted));
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
