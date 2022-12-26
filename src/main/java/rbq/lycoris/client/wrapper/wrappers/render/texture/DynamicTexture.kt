package rbq.lycoris.client.wrapper.wrappers.render.texture

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.utils.ReflectUtil
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