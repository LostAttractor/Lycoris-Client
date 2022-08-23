package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import java.awt.image.BufferedImage

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture", targetMap = MapEnum.VANILLA189)
class DynamicTexture(obj: Any) : AbstractTexture(obj) {
    constructor(bufferedImage: BufferedImage) : this(ReflectUtil.construction(DynamicTextureClass, bufferedImage))
    constructor(textureWidth: Int, textureHeight: Int) : this(
        ReflectUtil.construction(
            DynamicTextureClass,
            textureWidth,
            textureHeight
        )
    )

    companion object {
        @WrapClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture", targetMap = MapEnum.VANILLA189)
        lateinit var DynamicTextureClass: Class<*>
    }
}