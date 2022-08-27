package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper", targetMap = MapEnum.VANILLA189)
class OpenGlHelper(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "glBlendFunc", targetMap = MapEnum.VANILLA189, signature = "(IIII)V")
        lateinit var glBlendFunc: Method

        @JvmStatic
        fun glBlendFunc(i1: Int, i2: Int, i3: Int, i4: Int) {
            ReflectUtil.invokeStatic(glBlendFunc, i1, i2, i3, i4)
        }
    }
}