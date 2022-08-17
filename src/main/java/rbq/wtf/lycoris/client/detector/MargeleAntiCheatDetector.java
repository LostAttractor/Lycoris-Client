package rbq.wtf.lycoris.client.detector;

public class MargeleAntiCheatDetector {
    public static boolean isMAC() {
        return getMAC() != null;
    }

    public static Class<?> getMAC() {
        try {
            return Class.forName("cn.margele.netease.clientside.MargeleAntiCheat");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getHyGui() {
        try {
            return Class.forName("cn.hycraft.core.gui.HyCraftGui");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getGuiTab() {
        try {
            return Class.forName("cn.hycraft.core.util.render.EnumUtils$GuiTab");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Enum<?> getHyTab() {
        try {
            return (Enum<?>) Class.forName("cn.hycraft.core.util.render.EnumUtils$GuiTab").getEnumConstants()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
