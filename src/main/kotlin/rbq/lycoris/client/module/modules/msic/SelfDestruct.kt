package rbq.lycoris.client.module.modules.msic

import rbq.lycoris.client.Client
import rbq.lycoris.client.detector.MargeleAntiCheatDetector
import rbq.lycoris.client.module.Module
import rbq.lycoris.client.module.ModuleCategory
import rbq.lycoris.client.module.ModuleInfo
import rbq.lycoris.client.transformer.TransformManager
import rbq.lycoris.client.wrapper.wrappers.Minecraft
import rbq.lycoris.client.wrapper.wrappers.gui.IGuiScreen

@ModuleInfo(name = "Self-Destruct", description = "", category = ModuleCategory.Msic, saveState = false)
class SelfDestruct : Module() {
    override fun onEnable() {
        this.state = false
        if (Minecraft.minecraft.currentScreen != null && Minecraft.minecraft.currentScreen!!.equals(Client.clickGUI)) {
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
            Minecraft.minecraft.displayGuiScreenBypass(rbq.lycoris.client.wrapper.bridge.BridgeUtil.createGuiScreen(null))
        }
        TransformManager.doTransform()
    }
}