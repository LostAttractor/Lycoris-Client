package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
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