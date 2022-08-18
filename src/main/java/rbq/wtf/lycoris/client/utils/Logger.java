package rbq.wtf.lycoris.client.utils;

import rbq.wtf.lycoris.client.Client;

public class Logger {

    public static void log(String msg) {
        if (Client.enabledLog) {
            System.out.println("[Lycoris] " + msg);
        }
    }

    public static void log(String msg, LogLevel level) {
        if (Client.enabledLog && (Client.showDebugLevelLog || level != LogLevel.DEBUG)) {
            System.out.println("[Lycoris] [" + level.toString() + "] " + msg);
        }
    }

    public static void log(String msg, String category) {
        if (Client.enabledLog) {
            System.out.println("[Lycoris] [" + category + "] " + msg);
        }
    }

    public static void log(String msg, String category, LogLevel level) {
        if (Client.enabledLog && (Client.showDebugLevelLog || level != LogLevel.DEBUG)) {
            System.out.println("[Lycoris] [" + category + "] [" + level.toString() + "] " + msg);
        }
    }

    public enum LogLevel {
        LOG,
        WARNING,
        ERROR,
        DEBUG
    }
}
