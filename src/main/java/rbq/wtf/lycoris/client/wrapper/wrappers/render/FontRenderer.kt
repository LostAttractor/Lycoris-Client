package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper

@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer", targetMap = MapEnum.VANILLA189)
class FontRenderer(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClass(mcpName = "net.minecraft.client.gui.FontRenderer", targetMap = MapEnum.VANILLA189)
        lateinit var FontRendererClass: Class<*>
    }
}