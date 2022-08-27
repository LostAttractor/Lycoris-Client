package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.util.event.HoverEvent
import rbq.wtf.lycoris.client.wrapper.wrappers.util.event.click.ClickEvent
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.util.ChatStyle", targetMap = MapEnum.VANILLA189)
class ChatStyle(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "getChatHoverEvent", targetMap = MapEnum.VANILLA189)
        lateinit var getChatHoverEvent: Method

        @WrapMethod(mcpName = "getChatClickEvent", targetMap = MapEnum.VANILLA189)
        lateinit var getChatClickEvent: Method

        @WrapMethod(mcpName = "getInsertion", targetMap = MapEnum.VANILLA189)
        lateinit var getInsertion: Method
    }

    val chatHoverEvent: HoverEvent
        get() = HoverEvent(invoke(getChatHoverEvent)!!)
    val chatClickEvent: ClickEvent
        get() = ClickEvent(invoke(getChatClickEvent)!!)
    val insertion: String
        get() = invoke(getInsertion) as String
}