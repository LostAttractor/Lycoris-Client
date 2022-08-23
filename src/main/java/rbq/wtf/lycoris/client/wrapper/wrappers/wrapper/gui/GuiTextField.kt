package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.*
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.FontRenderer
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA189)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA1122)
class GuiTextField(obj: Any) : Gui(obj) {
    constructor(
        componentId: Int,
        fontrendererObj: FontRenderer,
        x: Int,
        y: Int,
        par5Width: Int,
        par6Height: Int
    ) : this(
        ReflectUtil.construction(
            GuiTextField_I_FontRenderer_IIII,
            componentId,
            fontrendererObj.wrapObject,
            x,
            y,
            par5Width,
            par6Height
        )
    )

    var text: String
        get() = getField(Companion.text) as String
        set(s) {
            invoke(setText, s)
        }

    fun setMaxStringLength(max: Int) {
        setField(maxStringLength, max)
    }

    fun setEnableBackgroundDrawing(max: Boolean) {
        setField(enableBackgroundDrawing, max)
    }

    fun setFocused(b: Boolean) {
        invoke(setFocused, b)
    }

    fun setCanLoseFocus(b: Boolean) {
        setField(canLoseFocus, b)
    }

    fun drawTextBox() {
        invoke(drawTextBox)
    }

    fun deleteFromCursor(length: Int) {
        invoke(deleteFromCursor, length)
    }

    fun func_146197_a(p_146197_1_: Int, p_146197_2_: Int, p_146197_3_: Boolean): Int {
        return invoke(func_146197_a, p_146197_1_, p_146197_2_, p_146197_3_) as Int
    }

    val cursorPosition: Int
        get() = getField(Companion.cursorPosition) as Int

    fun writeText(s: String?) {
        invoke(writeText, s)
    }

    fun textboxKeyTyped(c: Char, i: Int): Boolean {
        return invoke(textboxKeyTyped, c, i) as Boolean
    }

    fun updateCursorCounter() {
        invoke(updateCursorCounter)
    }

    fun mouseClicked(i1: Int, i2: Int, i3: Int) {
        invoke(mouseClicked, i1, i2, i3)
    }

    companion object {
        @WrapClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA189)
        @WrapClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA1122)
        lateinit var GuiTextFieldClass: Class<*>

        @WrapField(mcpName = "text", targetMap = MapEnum.VANILLA189)
        @WrapField(mcpName = "text", targetMap = MapEnum.VANILLA1122)
        lateinit var text: Field

        @WrapConstructor(
            targetMap = MapEnum.VANILLA189,
            signature = [Int::class, FontRenderer::class, Int::class, Int::class, Int::class, Int::class]
        )
        @WrapConstructor(
            targetMap = MapEnum.VANILLA1122,
            signature = [Int::class, FontRenderer::class, Int::class, Int::class, Int::class, Int::class]
        )
        lateinit var GuiTextField_I_FontRenderer_IIII: Constructor<*>

        @WrapField(mcpName = "maxStringLength", targetMap = MapEnum.VANILLA189)
        @WrapField(mcpName = "maxStringLength", targetMap = MapEnum.VANILLA1122)
        lateinit var maxStringLength: Field

        @WrapField(mcpName = "enableBackgroundDrawing", targetMap = MapEnum.VANILLA189)
        @WrapField(mcpName = "enableBackgroundDrawing", targetMap = MapEnum.VANILLA1122)
        lateinit var enableBackgroundDrawing: Field

        @WrapMethod(mcpName = "setFocused", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "setFocused", targetMap = MapEnum.VANILLA1122)
        lateinit var setFocused: Method

        @WrapField(mcpName = "canLoseFocus", targetMap = MapEnum.VANILLA189)
        @WrapField(mcpName = "canLoseFocus", targetMap = MapEnum.VANILLA1122)
        lateinit var canLoseFocus: Field

        @WrapMethod(mcpName = "drawTextBox", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "drawTextBox", targetMap = MapEnum.VANILLA1122)
        lateinit var drawTextBox: Method

        @WrapMethod(mcpName = "func_146197_a", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "getNthWordFromPosWS", targetMap = MapEnum.VANILLA1122)
        lateinit var func_146197_a: Method

        @WrapMethod(mcpName = "deleteFromCursor", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "deleteFromCursor", targetMap = MapEnum.VANILLA1122)
        lateinit var deleteFromCursor: Method

        @WrapField(mcpName = "cursorPosition", targetMap = MapEnum.VANILLA189)
        @WrapField(mcpName = "cursorPosition", targetMap = MapEnum.VANILLA1122)
        lateinit var cursorPosition: Field

        @WrapMethod(mcpName = "writeText", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "writeText", targetMap = MapEnum.VANILLA1122)
        lateinit var writeText: Method

        @WrapMethod(mcpName = "setText", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "setText", targetMap = MapEnum.VANILLA1122)
        lateinit var setText: Method

        @WrapMethod(mcpName = "textboxKeyTyped", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "textboxKeyTyped", targetMap = MapEnum.VANILLA1122)
        lateinit var textboxKeyTyped: Method

        @WrapMethod(mcpName = "updateCursorCounter", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "updateCursorCounter", targetMap = MapEnum.VANILLA1122)
        lateinit var updateCursorCounter: Method

        @WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA189)
        @WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA1122)
        lateinit var mouseClicked: Method
    }
}