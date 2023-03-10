package rbq.lycoris.client.wrapper.wrappers

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding", targetMap = MapEnum.VANILLA189)
class KeyBinding(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapField(mcpName = "pressed", targetMap = MapEnum.VANILLA189)
        lateinit var pressed: Field

        @WrapMethod(mcpName = "onTick", targetMap = MapEnum.VANILLA189)
        lateinit var onTick: Method

        @WrapMethod(mcpName = "getKeyCode", targetMap = MapEnum.VANILLA189)
        lateinit var getKeyCode: Method

        @WrapMethod(mcpName = "setKeyCode", targetMap = MapEnum.VANILLA189)
        lateinit var setKeyCode: Method

        @WrapMethod(mcpName = "isKeyDown", targetMap = MapEnum.VANILLA189)
        lateinit var isKeyDown: Method

//        @WrapField(mcpName = "keyCode", targetMap = MapEnum.VANILLA189)
//        lateinit var keyCode: Field

        fun onTick(keyCode: Int) {
            ReflectUtil.invokeStatic(onTick, keyCode)
        }
    }

    fun getKeyCode(): Int = invoke(getKeyCode) as Int
    fun setKeyCode(keyCode: Int) {
        invoke(setKeyCode, keyCode)
    }

    fun isKeyDown(): Boolean = invoke(isKeyDown) as Boolean

    var isPressed: Boolean
        get() = getField(pressed) as Boolean
        set(v) {
            setField(pressed, v)
        }
//    val keyCode: Int
//        get() = getField(Companion.keyCode) as Int
}