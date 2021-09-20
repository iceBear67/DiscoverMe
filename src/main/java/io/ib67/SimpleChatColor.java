package io.ib67;

import java.util.Random;
import java.util.stream.Collectors;

public enum SimpleChatColor {
    AQUA("&b"),
    RED("&c"),
    GOLD("&6"),
    DARK_GREEN("&2"),
    LAPIS("&3"),
    GRAY("&7"),
    DARK_GRAY("&8"),
    DARK_BLUE("&9"),
    BLACK("&0"),
    PINK("&d"),
    DARK_RED("&4"),
    BOLD("&l"),
    DELETE("&m"),
    RESET("&r"),
    GREEN("&a"),
    YELLOW("&e"),
    WHITE("&f");

    public static final char COLOR_CHAR = '\u00a7';
    private String text;

    SimpleChatColor(String text) {
        this.text = text;
    }

    public static String randomColorize(String str) {
        return str.chars().mapToObj(e -> (char) e).map(e -> SimpleChatColor.values()[new Random().nextInt(SimpleChatColor.values().length - 1)].toString() + e).collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return text.replaceAll("&", COLOR_CHAR + "");
    }
}
