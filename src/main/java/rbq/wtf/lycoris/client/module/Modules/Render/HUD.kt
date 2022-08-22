package rbq.wtf.lycoris.client.module.modules.render

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.event.EventTarget
import rbq.wtf.lycoris.client.event.Render2DEvent
import rbq.wtf.lycoris.client.gui.Font.FontLoaders
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.ModeValue
import rbq.wtf.lycoris.client.value.NumberValue
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution
import java.awt.Color

@ModuleInfo(
    name = "HUD",
    description = "Toggles visibility of the HUD.",
    category = ModuleCategory.Render
)
class HUD : Module() {
    val waterMark = BooleanValue("WaterMark", true)
    val arrayList = BooleanValue("ArrayList", true)
    val rainbowMode = ModeValue("Rainbow Mode", arrayOf("Rainbow", "Astolfo", "Static", "StaticRainbow"), 0)
    val rainbowSpeed = NumberValue("RainbowSpeed", 100.0f, 0.0f, 2000.0f, 0.1f)
    override fun onEnable() {}

    @EventTarget
    fun onRender2D(e: Render2DEvent?) {
        val sc = ScaledResolution(Minecraft.getMinecraft())
        if (waterMark.get()) {
            FontLoaders.default25.drawStringWithShadow(
                "Dimples.love",
                5.0,
                5.0,
                Color(0, 200, 100).rgb
            )
        }
        if (arrayList.get()) {
            val sorted = Client.moduleManager.modules.filter { m -> (m.state && m.array) }
                .sortedBy { FontLoaders.default16.getStringWidth(it.name) }
            var posY = 0f
            for (m in sorted) {
                FontLoaders.default20.drawStringWithShadow(
                    m.name, (sc.scaledWidth - FontLoaders.default20.getStringWidth(m.name) - 5).toDouble(), (posY + 5).toDouble(),
                    Color(0, 200, 100).rgb
                )
                posY += FontLoaders.default16.getStringHeight(m.name) + 4
            }
        }
    }
}