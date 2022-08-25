package rbq.wtf.lycoris.client.utils

import rbq.wtf.lycoris.client.wrapper.wrappers.Minecraft

open class MinecraftInstance {
    companion object {
        @JvmField
        val mc: Minecraft = Minecraft.minecraft
    }
}