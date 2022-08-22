package rbq.wtf.lycoris.client.gui.clickgui.component

import rbq.wtf.lycoris.client.gui.Font.FontLoaders
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI
import rbq.wtf.lycoris.client.gui.clickgui.utils.RenderUtil
import java.awt.Color

class BackGroundComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    clickGUI: ClickGUI
) : Component(clickGUI) {

    companion object {
        const val OFFSET_X = 0F
        const val OFFSET_Y = 0F
        const val WEIGHT = ClickGUI.WEIGHT
        const val HEIGHT = ClickGUI.HEIGHT
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        RenderUtil.drawFastRoundedRect(
            (startX - 1f), (startY - 1f), (startX + ClickGUI.WEIGHT + 1f),
            (startY + ClickGUI.HEIGHT + 1f), 5f, Color(15, 15, 15, 33).rgb
        )
        RenderUtil.drawFastRoundedRect(
            startX - 1.5f, startY - 1.5f, startX + ClickGUI.WEIGHT + 1.5f,
            startY + ClickGUI.HEIGHT + 1.5f, 5f, Color(15, 15, 15, 31).rgb
        )
        RenderUtil.drawFastRoundedRect(
            startX - 2f, startY - 2f, startX + ClickGUI.WEIGHT + 2f,
            startY + ClickGUI.HEIGHT + 2f, 5f, Color(15, 15, 15, 29).rgb
        )
        RenderUtil.drawFastRoundedRect(
            startX, startY, startX + ClickGUI.WEIGHT, startY + ClickGUI.HEIGHT, 5f, Color(15, 15, 15).rgb
        )
        RenderUtil.drawHLine(
            startX, startY + 35f, startX + ClickGUI.WEIGHT, startY + 35, 4f,
            Color(69, 78, 238).rgb
        )
        RenderUtil.drawHLine(
            startX + 110f,
            startY + 35f,
            startX + 110f,
            startY + ClickGUI.HEIGHT,
            4f,
            Color(69, 78, 238).rgb
        )
        RenderUtil.drawHLine(
            startX + 107f,
            startY + 36f,
            startX + 107f,
            startY + ClickGUI.HEIGHT,
            4f,
            Color(38, 38, 38).rgb
        )
        RenderUtil.drawHLine(
            startX + 107f,
            startY + 37f,
            startX + 107f,
            startY + 220f,
            2f,
            Color(87, 87, 87).rgb
        )
        //DrawClientName
        FontLoaders.default15.drawStringWithShadow(
            "L",
            (startX + 403).toDouble(),
            (startY + 317).toDouble(),
            Color(96, 78, 238).rgb
        )
        FontLoaders.default15.drawStringWithShadow(
            "ycoris 2.0",
            (startX + 403 + FontLoaders.default15.getStringWidth("L")).toDouble(),
            (startY + 317).toDouble(),
            Color(255, 255, 255).rgb
        )
    }
}