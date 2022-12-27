package rbq.lycoris.client.wrapper.wrappers.gui

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.wrappers.Minecraft
import rbq.lycoris.client.wrapper.wrappers.util.text.IChatComponent
import java.lang.reflect.Field
import java.lang.reflect.Method

class IGuiScreen(obj: Any) : GuiScreen(obj)

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiScreen", targetMap = MapEnum.VANILLA189)
open class GuiScreen(obj: Any) : Gui(obj) {
    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapMethod(mcpName = "drawScreen", targetMap = MapEnum.VANILLA189)
        lateinit var drawScreen: Method

        @WrapMethod(mcpName = "initGui", targetMap = MapEnum.VANILLA189)
        lateinit var initGui: Method

        @WrapMethod(mcpName = "onGuiClosed", targetMap = MapEnum.VANILLA189)
        lateinit var onGuiClosed: Method

        @WrapField(mcpName = "mc", targetMap = MapEnum.VANILLA189)
        lateinit var mc: Field

        @WrapField(mcpName = "width", targetMap = MapEnum.VANILLA189)
        lateinit var width: Field

        @WrapField(mcpName = "height", targetMap = MapEnum.VANILLA189)
        lateinit var height: Field

        @WrapMethod(mcpName = "keyTyped", targetMap = MapEnum.VANILLA189)
        lateinit var keyTyped: Method

        @WrapMethod(mcpName = "sendChatMessage", targetMap = MapEnum.VANILLA189, signature = "(Ljava/lang/String;Z)V")
        lateinit var sendChatMessage_SZ_V: Method

        @WrapMethod(mcpName = "handleComponentHover", targetMap = MapEnum.VANILLA189)
        lateinit var handleComponentHover: Method


        @WrapMethod(mcpName = "updateScreen", targetMap = MapEnum.VANILLA189)
        lateinit var updateScreen: Method


        @WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA189)
        lateinit var mouseClicked: Method


        @WrapMethod(mcpName = "mouseReleased", targetMap = MapEnum.VANILLA189)
        lateinit var mouseReleased: Method
    }

    fun setMc(mcIn: Minecraft) {
        setField(mc, mcIn.wrapObject)
    }

    fun initGui() {
        invoke(initGui)
    }

    var height: Int
        get() = getField(Companion.height) as Int
        set(heightIn) {
            setField(Companion.height, heightIn)
        }
    var width: Int
        get() = getField(Companion.width) as Int
        set(widthIn) {
            setField(Companion.width, widthIn)
        }

    fun handleComponentHover(chatComponent: IChatComponent, i1: Int, i2: Int) {
        invoke(handleComponentHover, chatComponent.wrapObject, i1, i2)
    }

    fun sendChatMessage(s: String?) {
        invoke(sendChatMessage_SZ_V, s, true)
    }
}