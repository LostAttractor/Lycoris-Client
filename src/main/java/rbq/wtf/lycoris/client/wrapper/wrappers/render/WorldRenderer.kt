package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.renderer.WorldRenderer", targetMap = MapEnum.VANILLA189)
class WorldRenderer(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "begin", targetMap = MapEnum.VANILLA189)
        lateinit var begin: Method

        @WrapMethod(mcpName = "pos", targetMap = MapEnum.VANILLA189)
        lateinit var pos: Method

        @WrapMethod(mcpName = "endVertex", targetMap = MapEnum.VANILLA189)
        lateinit var endVertex: Method

        @WrapMethod(
            mcpName = "color",
            targetMap = MapEnum.VANILLA189,
            signature = "(FFFF)Lnet/minecraft/client/renderer/WorldRenderer;"
        )
        lateinit var color_4f: Method

        @WrapMethod(
            mcpName = "color",
            targetMap = MapEnum.VANILLA189,
            signature = "(IIII)Lnet/minecraft/client/renderer/WorldRenderer;"
        )
        lateinit var color_4I: Method

        @WrapMethod(mcpName = "putColorMultiplier", targetMap = MapEnum.VANILLA189)
        lateinit var putColorMultiplier: Method

        @WrapMethod(mcpName = "tex", targetMap = MapEnum.VANILLA189)
        lateinit var tex: Method
    }
    
    fun begin(glMode: Int, format: VertexFormat) {
        invoke(begin, glMode, format.wrapObject)
    }

    fun pos(x: Double, y: Double, z: Double): WorldRenderer {
        invoke(pos, x, y, z)
        return this
    }

    fun endVertex() = invoke(endVertex)

    fun color(red: Float, green: Float, blue: Float, alpha: Float): WorldRenderer {
        return WorldRenderer(invoke(color_4f, red, green, blue, alpha)!!)
    }

    fun color(i1: Int, i2: Int, i3: Int, i4: Int): WorldRenderer {
        return WorldRenderer(invoke(color_4I, i1, i2, i3, i4)!!)
    }

    fun tex(u2: Double, v1: Double): WorldRenderer {
        return WorldRenderer(invoke(tex, u2, v1)!!)
    }
}