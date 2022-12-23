package me.nylestroke.droopy.enums;

public enum LoggerColors {
    ERRORED("\u001B[31m"),
    SUCCESSFUL("\u001B[32m"),
    INFO("\u001B[36m"),
    RESET("\u001B[0m");

    private final String ANSI_COLOR;

    LoggerColors(String color) {
        this.ANSI_COLOR = color;
    }

    public String getValue() {
        return ANSI_COLOR;
    }
}
