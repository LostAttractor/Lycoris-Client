package rbq.lycoris.client.wrapper.wrappers.gui

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiIngame", targetMap = MapEnum.VANILLA189)
class GuiIngame(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapMethod(mcpName = "renderTooltip", targetMap = MapEnum.VANILLA189)
        lateinit var renderTooltip: Method
    }
}