package me.nylestroke.droopy.tools;

public abstract class Logger {

    public static void error(Exception error) {
        System.out.println("\u001B[31m" + "Something went wrong: " + error.getMessage() + "\u001B[0m");
    }
    public static void info(String message) {
        System.out.println("\u001B[36m" + "Information: " +  message + "\u001B[0m");
    }

    public static void successful(String message) {
        System.out.println("\u001B[32m" + "Successfully done :" + message + "\u001B[0m");
    }
}
