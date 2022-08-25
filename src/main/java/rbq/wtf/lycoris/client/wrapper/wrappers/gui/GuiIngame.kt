package rbq.wtf.lycoris.client.wrapper.wrappers.gui

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper
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