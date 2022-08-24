package rbq.wtf.lycoris.client.detector

object MargeleAntiCheatDetector {
    @JvmStatic
    val isMAC: Boolean
        get() = getMAC() != null

    @JvmStatic
    fun getMAC(): Class<*>? {
        return try {
            this.javaClass.classLoader.loadClass("cn.margele.netease.clientside.MargeleAntiCheat")
        } catch (e: ClassNotFoundException) {
            null
        }
    }

    @JvmStatic
    val hyGui: Class<*>?
        get() = try {
            this.javaClass.classLoader.loadClass("cn.hycraft.core.gui.HyCraftGui")
        } catch (e: ClassNotFoundException) {
            null
        }

    @JvmStatic
    val guiTab: Class<*>?
        get() = try {
            this.javaClass.classLoader.loadClass("cn.hycraft.core.util.render.EnumUtils\$GuiTab")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            null
        }

    @JvmStatic
    val hyTab: Enum<*>?
        get() = try {
            this.javaClass.classLoader.loadClass("cn.hycraft.core.util.render.EnumUtils\$GuiTab").enumConstants[0] as Enum<*>
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}