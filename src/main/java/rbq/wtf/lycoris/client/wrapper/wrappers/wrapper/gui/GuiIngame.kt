package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiIngame", targetMap = MapEnum.VANILLA189)
class GuiIngame(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClass(mcpName = "net.minecraft.client.gui.GuiIngame", targetMap = MapEnum.VANILLA189)
        lateinit var GuiIngameClass: Class<*>

        @WrapMethod(mcpName = "renderTooltip", targetMap = MapEnum.VANILLA189)
        lateinit var renderTooltip: Method
    }
}