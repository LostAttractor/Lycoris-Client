package rbq.wtf.lycoris.client.module.modules.render

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.event.EventTarget
import rbq.wtf.lycoris.client.event.Render2DEvent
import rbq.wtf.lycoris.client.font.FontLoaders
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.utils.ColorUtils
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.ModeValue
import rbq.wtf.lycoris.client.value.NumberValue
import rbq.wtf.lycoris.client.wrapper.wrappers.Minecraft
import rbq.wtf.lycoris.client.wrapper.wrappers.gui.ScaledResolution
import java.awt.Color

@ModuleInfo(
    name = "HUD",
    description = "Toggles visibility of the HUD.",
    category = ModuleCategory.Render
)
class HUD : Module() {
    private val arrayList = BooleanValue("ArrayList", true)
    private val waterMark = BooleanValue("WaterMark", true)
    private val rainbow = BooleanValue("Rainbow", true)
    private val waterMarkMode = ModeValue("WaterMark Mode", arrayOf("LOGO", "MAGIC"), 0)
    private val rainbowMode = ModeValue("Rainbow Mode", arrayOf("Rainbow", "Astolfo", "Static", "StaticRainbow"), 0)
    private val rainbowSpeed = NumberValue("RainbowSpeed", 10.0f, 0.0f, 200.0f, 5f)
    override fun onEnable() {}

    @EventTarget
    fun onRender2D(e: Render2DEvent?) {
        val sc = ScaledResolution(Minecraft.minecraft)
        if (waterMark.get()) {
            val text = if (waterMarkMode.get() == 0) "Dimples.love" else ColorUtils.randomMagicText("Dimples.love")
            FontLoaders.default30.drawStringWithShadow(
                text,
                5.0,
                5.0,
                Color(0, 200, 100).rgb
            )
        }
        if (arrayList.get()) {
            val sorted = Client.moduleManager.modules.filter { m -> (m.state && m.array) }
                .sortedBy { -FontLoaders.default16.getStringWidth(it.name) }
            var posY = 0f
            var rainbowOffset = 0L
            for (m in sorted) {
                val color = if (rainbow.get()) ColorUtils.rainbow(rainbowOffset) else Color(0, 200, 100)
                FontLoaders.default20.drawStringWithShadow(
                    m.name,
                    (sc.scaledWidth - FontLoaders.default20.getStringWidth(m.name) - 5).toDouble(),
                    (posY + 5).toDouble(),
                    color.rgb
                )
                posY += FontLoaders.default16.getStringHeight(m.name) + 4
                rainbowOffset += rainbowSpeed.get().toLong() * 100
            }
        }
    }
}