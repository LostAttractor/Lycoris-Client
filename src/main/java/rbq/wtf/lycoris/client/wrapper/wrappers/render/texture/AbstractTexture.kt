package rbq.wtf.lycoris.client.wrapper.wrappers.render.texture

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
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