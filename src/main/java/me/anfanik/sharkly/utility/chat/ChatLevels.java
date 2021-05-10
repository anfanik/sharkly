package me.anfanik.sharkly.utility.chat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatLevels implements ChatLevel {

    FINE("§ai"),
    WARNING("§6i"),
    ERROR("§ci"),
    DEBUG("§f䀩");

    private final String color;

    @Override
    public String format(String message) {
        return "§8[" + color + "§8] §r" + message;
    }

}
