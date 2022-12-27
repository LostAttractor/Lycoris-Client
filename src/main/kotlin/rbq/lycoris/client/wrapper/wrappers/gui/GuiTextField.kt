package rbq.lycoris.client.wrapper.wrappers.gui

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapConstructor
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.annotation.repeat.WrapConstructors
import rbq.lycoris.client.wrapper.annotation.repeat.WrapFields
import rbq.lycoris.client.wrapper.annotation.repeat.WrapMethods
import rbq.lycoris.client.wrapper.annotation.repeat.WrapperClasses
import rbq.lycoris.client.wrapper.utils.ReflectUtil
import rbq.lycoris.client.wrapper.wrappers.render.FontRenderer
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClasses(
    WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA189),
    WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA1122)
)
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
        @WrapConstructors(
            WrapConstructor(
                targetMap = MapEnum.VANILLA189,
                signature = [Int::class, FontRenderer::class, Int::class, Int::class, Int::class, Int::class]
            ),
            WrapConstructor(
                targetMap = MapEnum.VANILLA1122,
                signature = [Int::class, FontRenderer::class, Int::class, Int::class, Int::class, Int::class]
            )
        )
        lateinit var GuiTextField_I_FontRenderer_IIII: Constructor<*>

        @WrapFields(
            WrapField(mcpName = "text", targetMap = MapEnum.VANILLA189),
            WrapField(mcpName = "text", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var text: Field

        @WrapFields(
            WrapField(mcpName = "maxStringLength", targetMap = MapEnum.VANILLA189),
            WrapField(mcpName = "maxStringLength", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var maxStringLength: Field

        @WrapFields(
            WrapField(mcpName = "enableBackgroundDrawing", targetMap = MapEnum.VANILLA189),
            WrapField(mcpName = "enableBackgroundDrawing", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var enableBackgroundDrawing: Field

        @WrapMethods(
            WrapMethod(mcpName = "setFocused", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "setFocused", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var setFocused: Method

        @WrapFields(
            WrapField(mcpName = "canLoseFocus", targetMap = MapEnum.VANILLA189),
            WrapField(mcpName = "canLoseFocus", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var canLoseFocus: Field

        @WrapMethods(
            WrapMethod(mcpName = "drawTextBox", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "drawTextBox", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var drawTextBox: Method

        @WrapMethods(
            WrapMethod(mcpName = "func_146197_a", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "getNthWordFromPosWS", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var func_146197_a: Method

        @WrapMethods(
            WrapMethod(mcpName = "deleteFromCursor", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "deleteFromCursor", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var deleteFromCursor: Method

        @WrapFields(
            WrapField(mcpName = "cursorPosition", targetMap = MapEnum.VANILLA189),
            WrapField(mcpName = "cursorPosition", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var cursorPosition: Field

        @WrapMethods(
            WrapMethod(mcpName = "writeText", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "writeText", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var writeText: Method

        @WrapMethods(
            WrapMethod(mcpName = "setText", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "setText", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var setText: Method

        @WrapMethods(
            WrapMethod(mcpName = "textboxKeyTyped", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "textboxKeyTyped", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var textboxKeyTyped: Method

        @WrapMethods(
            WrapMethod(mcpName = "updateCursorCounter", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "updateCursorCounter", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var updateCursorCounter: Method

        @WrapMethods(
            WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA189),
            WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA1122)
        )
        lateinit var mouseClicked: Method
    }
}