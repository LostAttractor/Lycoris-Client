package rbq.wtf.lycoris.client.wrapper.wrappers.render.texture

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.awt.image.BufferedImage

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture", targetMap = MapEnum.VANILLA189)
class DynamicTexture(obj: Any) : AbstractTexture(obj) {
    constructor(bufferedImage: BufferedImage) : this(ReflectUtil.construction(wrapClass, bufferedImage))
    constructor(textureWidth: Int, textureHeight: Int) : this(
        ReflectUtil.construction(
            wrapClass,
            textureWidth,
            textureHeight
        )
    )

    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;
    }
}