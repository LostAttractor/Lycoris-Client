package rbq.lycoris.client.wrapper.wrappers

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.client.settings.GameSettings", targetMap = MapEnum.VANILLA189)
class GameSettings(obj: Any) : IWrapper(obj) {

    companion object {
        @WrapField(mcpName = "keyBindForward", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindForward: Field

        @WrapField(mcpName = "keyBindLeft", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindLeft: Field

        @WrapField(mcpName = "keyBindBack", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindBack: Field

        @WrapField(mcpName = "keyBindRight", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindRight: Field

        @WrapField(mcpName = "keyBindJump", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindJump: Field

        @WrapField(mcpName = "keyBindSneak", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindSneak: Field

        @WrapField(mcpName = "keyBindSprint", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindSprint: Field

        @WrapField(mcpName = "keyBindInventory", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindInventory: Field

        @WrapField(mcpName = "keyBindUseItem", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindUseItem: Field

        @WrapField(mcpName = "keyBindDrop", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindDrop: Field

        @WrapField(mcpName = "keyBindAttack", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindAttack: Field

        @WrapField(mcpName = "keyBindPickBlock", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindPickBlock: Field

        @WrapField(mcpName = "keyBindChat", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindChat: Field

        @WrapField(mcpName = "keyBindPlayerList", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindPlayerList: Field

        @WrapField(mcpName = "keyBindCommand", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindCommand: Field

        @WrapField(mcpName = "keyBindScreenshot", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindScreenshot: Field

        @WrapField(mcpName = "keyBindTogglePerspective", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindTogglePerspective: Field

        @WrapField(mcpName = "keyBindSmoothCamera", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindSmoothCamera: Field

        @WrapField(mcpName = "keyBindFullscreen", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindFullscreen: Field

        @WrapField(mcpName = "keyBindSpectatorOutlines", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindSpectatorOutlines: Field

        @WrapField(mcpName = "keyBindsHotbar", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindsHotbar: Field

        @WrapField(mcpName = "keyBindings", targetMap = MapEnum.VANILLA189)
        lateinit var keyBindings: Field

        @WrapField(mcpName = "mouseSensitivity", targetMap = MapEnum.VANILLA189)
        lateinit var mouseSensitivity: Field

        @WrapField(mcpName = "gammaSetting", targetMap = MapEnum.VANILLA189)
        lateinit var gammaSetting: Field

        @WrapField(mcpName = "chatLinks", targetMap = MapEnum.VANILLA189)
        lateinit var chatLinks: Field

        @WrapField(mcpName = "forceUnicodeFont", targetMap = MapEnum.VANILLA189)
        lateinit var forceUnicodeFont: Field

        @WrapField(mcpName = "viewBobbing", targetMap = MapEnum.VANILLA189)
        lateinit var viewBobbing: Field

        @WrapField(mcpName = "thirdPersonView", targetMap = MapEnum.VANILLA189)
        lateinit var thirdPersonView: Field

        @WrapField(mcpName = "guiScale", targetMap = MapEnum.VANILLA189)
        lateinit var guiScale: Field
    }

    val keyBindAttack: KeyBinding
        get() = getField(Companion.keyBindAttack)?.let { KeyBinding(it) }!!
    val keyBindUseItem: KeyBinding
        get() = getField(Companion.keyBindUseItem)?.let { KeyBinding(it) }!!
    val keyBindSneak: KeyBinding
        get() = getField(Companion.keyBindSneak)?.let { KeyBinding(it) }!!
    val keyBindJump: KeyBinding
        get() = getField(Companion.keyBindJump)?.let { KeyBinding(it) }!!
    val keyBindForward: KeyBinding
        get() = getField(Companion.keyBindForward)?.let { KeyBinding(it) }!!
    val keyBindRight: KeyBinding
        get() = getField(Companion.keyBindRight)?.let { KeyBinding(it) }!!
    val keyBindLeft: KeyBinding
        get() = getField(Companion.keyBindLeft)?.let { KeyBinding(it) }!!
    val keyBindBack: KeyBinding
        get() = getField(Companion.keyBindBack)?.let { KeyBinding(it) }!!
    val keyBindSprint: KeyBinding
        get() = getField(Companion.keyBindSprint)?.let { KeyBinding(it) }!!

    val mouseSensitivity: Float
        get() = getField(Companion.mouseSensitivity) as Float

    fun setGammaSetting(v: Float) {
        setField(gammaSetting, v)
    }

    val chatLinks: Boolean
        get() = getField(Companion.chatLinks) as Boolean
    val guiScale: Int
        get() = getField(Companion.guiScale) as Int
    val forceUnicodeFont: Boolean
        get() = getField(Companion.forceUnicodeFont) as Boolean
    var viewBobbing: Boolean
        get() = getField(Companion.viewBobbing) as Boolean
        set(b) {
            setField(Companion.viewBobbing, b)
        }

    fun setThirdPersonView(view: Int) {
        setField(thirdPersonView, view)
    }
}