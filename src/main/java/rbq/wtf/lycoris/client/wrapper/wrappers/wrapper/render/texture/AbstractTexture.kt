package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.AbstractTexture", targetMap = MapEnum.VANILLA189)
open class AbstractTexture(obj: Any) : IWrapper(obj) {

    val glTextureId: Int
        get() = invoke(getGlTextureId) as Int

    companion object {
        @WrapMethod(mcpName = "getGlTextureId", targetMap = MapEnum.VANILLA189)
        lateinit var getGlTextureId: Method
    }
}