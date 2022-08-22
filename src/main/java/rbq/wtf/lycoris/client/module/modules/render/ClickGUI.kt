package rbq.wtf.lycoris.client.module.modules.render

import org.lwjgl.input.Keyboard
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.detector.MargeleAntiCheatDetector
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.ModeValue
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
    val waterMark = BooleanValue("WaterMark", true)
    val arrayList = BooleanValue("ArrayList", true)
    val rainbowMode = ModeValue("Rainbow Mode", arrayOf("Rainbow", "Astolfo", "Static", "StaticRainbow"), 0)

    override fun onEnable() {
        if (MargeleAntiCheatDetector.getHyGui() != null) {
            val hyGui = MargeleAntiCheatDetector.getHyGui()
            try {
                val constructor =
                    hyGui.getConstructor(Int::class.javaPrimitiveType, MargeleAntiCheatDetector.getGuiTab())
                constructor.isAccessible = true
                val gui = constructor.newInstance(0, MargeleAntiCheatDetector.getHyTab())
                Minecraft.getMinecraft().displayGuiScreenBypass(IGuiScreen(gui))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return
        }
        val guiScreenWrapper = Minecraft.getMinecraft().currentScreen
        if (Objects.isNull(guiScreenWrapper.wrapObject)) {
            Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiScreen(Client.clickGUI))
        }
    }
}