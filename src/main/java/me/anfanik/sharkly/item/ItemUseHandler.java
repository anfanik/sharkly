package me.anfanik.sharkly.item;

import me.anfanik.sharkly.utility.Hand;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public interface ItemUseHandler {

    void handle(Player player, Hand hand);

    static ItemUseHandler anyHand(Consumer<Player> handler) {
        return (player, hand) -> handler.accept(player);
    }

    static ItemUseHandler primaryHand(Consumer<Player> handler) {
        return (player, hand) -> {
            if (hand == Hand.PRIMARY) {
                handler.accept(player);
            }
        } ;
    }

    static ItemUseHandler secondaryHand(Consumer<Player> handler) {
        return (player, hand) -> {
            if (hand == Hand.SECONDARY) {
                handler.accept(player);
            }
        } ;
    }

}
