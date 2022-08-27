package rbq.wtf.lycoris.client.module.modules.msic

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.detector.MargeleAntiCheatDetector
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.transformer.TransformManager
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.Minecraft
import rbq.wtf.lycoris.client.wrapper.wrappers.gui.IGuiScreen

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
            Minecraft.minecraft.displayGuiScreenBypass(BridgeUtil.createGuiScreen(null))
        }
        TransformManager.doTransform()
    }
}