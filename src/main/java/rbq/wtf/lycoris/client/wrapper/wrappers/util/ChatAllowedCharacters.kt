package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.util.ChatAllowedCharacters", targetMap = MapEnum.VANILLA189)
class ChatAllowedCharacters(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "isAllowedCharacter", targetMap = MapEnum.VANILLA189)
        lateinit var isAllowedCharacter: Method

        @WrapMethod(mcpName = "filterAllowedCharacters", targetMap = MapEnum.VANILLA189)
        lateinit var filterAllowedCharacters: Method

        fun isAllowedCharacter(chars: Char): Boolean {
            return ReflectUtil.invokeStatic(isAllowedCharacter, chars) as Boolean
        }
    }
}