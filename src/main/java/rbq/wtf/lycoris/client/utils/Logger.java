package rbq.wtf.lycoris.client.utils;

import rbq.wtf.lycoris.client.Client;

public class Logger {

    public static void info(String msg) {
        log(msg, LogLevel.INFO);
    }

    public static void info(String msg, String category) {
        log(msg, category, LogLevel.INFO);
    }

    public static void warning(String msg) {
        log(msg, LogLevel.WARNING);
    }

    public static void warning(String msg, String category) {
        log(msg, category, LogLevel.WARNING);
    }

    public static void error(String msg) {
        log(msg, LogLevel.ERROR);
    }

    public static void error(String msg, String category) {
        log(msg, category, LogLevel.ERROR);
    }

    public static void debug(String msg) {
        log(msg, LogLevel.DEBUG);
    }

    public static void debug(String msg, String category) {
        log(msg, category, LogLevel.DEBUG);
    }

    private static void log(String msg, LogLevel level) {
        if (Client.enabledLog && (Client.showDebugLevelLog || level != LogLevel.DEBUG)) {
            System.out.println("[Lycoris] [" + level.toString() + "] " + msg);
        }
    }

    private static void log(String msg, String category, LogLevel level) {
        if (Client.enabledLog && (Client.showDebugLevelLog || level != LogLevel.DEBUG)) {
            System.out.println("[Lycoris] [" + category + "] [" + level.toString() + "] " + msg);
        }
    }

    public enum LogLevel {
        INFO,
        WARNING,
        ERROR,
        DEBUG
    }
}
