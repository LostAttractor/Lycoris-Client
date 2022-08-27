package rbq.wtf.lycoris.client.wrapper.wrappers.gui

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiChat", targetMap = MapEnum.VANILLA189)
class GuiChat(obj: Any) : GuiScreen(obj) {
    var inputField: GuiTextField
        get() = GuiTextField(getField(Companion.inputField)!!)
        set(textField) {
            setField(Companion.inputField, textField.wrapObject)
        }

    fun setSentHistoryCursor(i: Int) {
        setField(sentHistoryCursor, i)
    }

    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapField(mcpName = "inputField", targetMap = MapEnum.VANILLA189)
        lateinit var inputField: Field

        @WrapField(mcpName = "sentHistoryCursor", targetMap = MapEnum.VANILLA189)
        lateinit var sentHistoryCursor: Field

        @JvmStatic
        fun isGuiChat(guiScreen: GuiScreen): Boolean {
            return wrapClass.isInstance(guiScreen.wrapObject)
        }
    }
}