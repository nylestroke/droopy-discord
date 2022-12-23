package me.nylestroke.droopy.tools;

import me.nylestroke.droopy.enums.LoggerColors;

public abstract class Logger {
    private static final String erroredColor = LoggerColors.ERRORED.getValue();
    private static final String infoColor = LoggerColors.INFO.getValue();
    private static final String successfulColor = LoggerColors.SUCCESSFUL.getValue();
    private static final String resetColor = LoggerColors.RESET.getValue();

    public static void error(Exception error) {
        System.out.println(erroredColor + "Something went wrong: " + error.getMessage() + resetColor);
    }
    public static void info(String message) {
        System.out.println(infoColor + "Information: " +  message + resetColor);
    }

    public static void successful(String message) {
        System.out.println(successfulColor + "Successfully done: " + message + resetColor);
    }
}
