package rbq.wtf.lycoris.client.utils;

public class Logger {
    public static final boolean enabledLog = true;

    public enum LogLevel{
        LOG,
        WARNING,
        ERROR
    }

    public static void log(String msg) {
        if (enabledLog) {
            System.out.println("[Lycoris] " + msg);
        }
    }

    public static void log(String msg, LogLevel level) {
        if (enabledLog) {
            System.out.println("[Lycoris] [" + level.toString() + "] " + msg);
        }
    }

    public static void log(String msg, String category) {
        if (enabledLog) {
            System.out.println("[Lycoris] [" + category + "] " + msg);
        }
    }

    public static void log(String msg, String category, LogLevel level) {
        if (enabledLog) {
            System.out.println("[Lycoris] [" + category + "] ["+  level.toString() + "] " + msg);
        }
    }
}
