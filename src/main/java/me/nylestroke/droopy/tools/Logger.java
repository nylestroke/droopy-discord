package me.nylestroke.droopy.tools;

import me.nylestroke.droopy.enums.LoggerColors;

public abstract class Logger {
    private static final LoggerColors erroredColor = LoggerColors.ERRORED;
    private static final LoggerColors resetColor = LoggerColors.RESET;
    private static final LoggerColors successfulColor = LoggerColors.SUCCESSFUL;
    private static final LoggerColors infoColor = LoggerColors.INFO;

    public static void error(Exception error) {
        System.out.println(erroredColor + "Something went wrong: " + error.getMessage() + resetColor);
    }
    public static void info(String message) {
        System.out.println(infoColor + "Information: " +  message + resetColor);
    }

    public static void successful(String message) {
        System.out.println(successfulColor + "Successfully done :" + message + resetColor);
    }
}
