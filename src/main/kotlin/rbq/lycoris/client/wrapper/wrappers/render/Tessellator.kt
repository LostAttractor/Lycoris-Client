package rbq.lycoris.client.wrapper.wrappers.render

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Field
import java.lang.reflect.Method

class Tessellator(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "getInstance", targetMap = MapEnum.VANILLA189)
        lateinit var getInstance: Method

        @WrapField(mcpName = "worldRenderer", targetMap = MapEnum.VANILLA189)
        lateinit var worldRenderer: Field

        @WrapMethod(mcpName = "draw", targetMap = MapEnum.VANILLA189)
        lateinit var draw: Method

        @JvmStatic
        val instance: Tessellator
            get() = Tessellator(ReflectUtil.invokeStatic(getInstance)!!)
    }

    val worldRenderer: WorldRenderer
        get() = WorldRenderer(getField(Companion.worldRenderer)!!)

    fun draw() {
        invoke(draw)
    }
}