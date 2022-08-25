package rbq.wtf.lycoris.client.wrapper.wrappers.util.text

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.wrappers.util.ChatStyle
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.util.IChatComponent", targetMap = MapEnum.VANILLA189)
class IChatComponent(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "getUnformattedText", targetMap = MapEnum.VANILLA189)
        lateinit var getUnformattedText: Method

        @WrapMethod(mcpName = "getFormattedText", targetMap = MapEnum.VANILLA189)
        lateinit var getFormattedText: Method

        @WrapMethod(mcpName = "getChatStyle", targetMap = MapEnum.VANILLA189)
        lateinit var getChatStyle: Method
    }

    val unformattedText: String
        get() = invoke(getUnformattedText) as String
    val formattedText: String
        get() = invoke(getFormattedText) as String
    val chatStyle: ChatStyle
        get() = ChatStyle(invoke(getChatStyle)!!)
}