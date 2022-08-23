package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.gui.Gui", targetMap = MapEnum.VANILLA189)
open class Gui(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "drawModalRectWithCustomSizedTexture", targetMap = MapEnum.VANILLA189)
        lateinit var drawModalRectWithCustomSizedTexture: Method

        @WrapMethod(mcpName = "drawRect", targetMap = MapEnum.VANILLA189)
        lateinit var drawRect: Method

        fun drawModalRectWithCustomSizedTexture(
            x: Int,
            y: Int,
            u: Float,
            v: Float,
            width: Int,
            height: Int,
            textureWidth: Float,
            textureHeight: Float
        ) {
            ReflectUtil.invokeStatic(
                drawModalRectWithCustomSizedTexture,
                x,
                y,
                u,
                v,
                width,
                height,
                textureWidth,
                textureHeight
            )
        }

        @JvmStatic
        fun drawRect(left: Int, top: Int, right: Int, bottom: Int, color: Int) {
            ReflectUtil.invokeStatic(drawRect, left, top, right, bottom, color)
        }
    }
}