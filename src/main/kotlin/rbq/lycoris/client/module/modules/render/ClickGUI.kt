package rbq.lycoris.client.module.modules.render

import org.lwjgl.input.Keyboard
import rbq.lycoris.client.Client
import rbq.lycoris.client.detector.MargeleAntiCheatDetector
import rbq.lycoris.client.module.Module
import rbq.lycoris.client.module.ModuleCategory
import rbq.lycoris.client.module.ModuleInfo
import rbq.lycoris.client.value.BooleanValue
import rbq.lycoris.client.wrapper.wrappers.Minecraft
import rbq.lycoris.client.wrapper.wrappers.gui.IGuiScreen
import java.util.*

@ModuleInfo(
    name = "ClickGUI",
    description = "Opens the ClickGUI.",
    category = ModuleCategory.Render,
    keyBind = Keyboard.KEY_INSERT,
    saveState = false,
    array = false
)
class ClickGUI : Module() {
    val arrayList1 = BooleanValue("ArrayList1", true)
    val arrayList2 = BooleanValue("ArrayList2", true)
    val arrayList3 = BooleanValue("ArrayList3", true)
    val arrayList4 = BooleanValue("ArrayList4", true)
    val arrayList5 = BooleanValue("ArrayList5", true)
    val arrayList6 = BooleanValue("ArrayList6", true)
    val arrayList7 = BooleanValue("ArrayList7", true)
    val arrayList8 = BooleanValue("ArrayList8", true)
    val arrayList9 = BooleanValue("ArrayList9", true)
    val arrayList10 = BooleanValue("ArrayList10", true)
    val arrayList11 = BooleanValue("ArrayList11", true)
    val arrayList12 = BooleanValue("ArrayList12", true)
    val arrayList13 = BooleanValue("ArrayList13", true)
    val arrayList14 = BooleanValue("ArrayList14", true)
    val arrayList15 = BooleanValue("ArrayList15", true)
    val arrayList16 = BooleanValue("ArrayList16", true)
    val arrayList17 = BooleanValue("ArrayList17", true)
    val arrayList18 = BooleanValue("ArrayList18", true)

    override fun onEnable() {
        if (MargeleAntiCheatDetector.isMAC) {
            val hyGui = MargeleAntiCheatDetector.hyGui
            try {
                val constructor =
                    hyGui!!.getConstructor(Int::class.javaPrimitiveType, MargeleAntiCheatDetector.guiTab)
                constructor.isAccessible = true
                val gui = constructor.newInstance(0, MargeleAntiCheatDetector.hyTab)
                Minecraft.minecraft.displayGuiScreenBypass(IGuiScreen(gui))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return
        }
        if (Objects.isNull(Minecraft.minecraft.currentScreen)) {
            Minecraft.minecraft.displayGuiScreenBypass(
                rbq.lycoris.client.wrapper.bridge.BridgeUtil.createGuiScreen(
                    Client.clickGUI
                )
            )
        }
    }
}