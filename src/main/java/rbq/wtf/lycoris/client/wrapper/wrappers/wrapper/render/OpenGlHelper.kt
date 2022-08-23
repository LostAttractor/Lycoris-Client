package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper", targetMap = MapEnum.VANILLA189)
class OpenGlHelper(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper", targetMap = MapEnum.VANILLA189)
        lateinit var OpenGlHelperClass: Class<*>

        @WrapMethod(mcpName = "glBlendFunc", targetMap = MapEnum.VANILLA189, signature = "(IIII)V")
        lateinit var glBlendFunc: Method

        @JvmStatic
        fun glBlendFunc(i1: Int, i2: Int, i3: Int, i4: Int) {
            ReflectUtil.invokeStatic(glBlendFunc, i1, i2, i3, i4)
        }
    }
}