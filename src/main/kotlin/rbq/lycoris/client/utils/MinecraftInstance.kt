package rbq.lycoris.client.utils

import rbq.lycoris.client.wrapper.wrappers.Minecraft

open class MinecraftInstance {
    companion object {
        @JvmField
        val mc: Minecraft = Minecraft.minecraft
    }
}