package rbq.wtf.lycoris.client.utils

import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft

open class MinecraftInstance {
    companion object {
        @JvmStatic
        val mc: Minecraft = Minecraft.getMinecraft()
    }
}