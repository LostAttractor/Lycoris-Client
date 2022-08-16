package rbq.wtf.lycoris.client.utils;

import rbq.wtf.lycoris.client.LycorisClient;

public class Logger {
    public static boolean debug;

    public static void debug(String msg) {
            System.out.println("[Lycoris Client] [Debug] " + msg);
    }
}
