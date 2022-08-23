package rbq.wtf.lycoris.client.module.modules.render

import org.lwjgl.input.Keyboard
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.detector.MargeleAntiCheatDetector
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.IGuiScreen
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
        if (MargeleAntiCheatDetector.getHyGui() != null) {
            val hyGui = MargeleAntiCheatDetector.getHyGui()
            try {
                val constructor =
                    hyGui.getConstructor(Int::class.javaPrimitiveType, MargeleAntiCheatDetector.getGuiTab())
                constructor.isAccessible = true
                val gui = constructor.newInstance(0, MargeleAntiCheatDetector.getHyTab())
                Minecraft.minecraft.displayGuiScreenBypass(IGuiScreen(gui))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return
        }
        if (Objects.isNull(Minecraft.minecraft.currentScreen)) {
            Minecraft.minecraft.displayGuiScreenBypass(BridgeUtil.createGuiScreen(Client.clickGUI))
        }
    }
}