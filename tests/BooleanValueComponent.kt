package rbq.wtf.lycoris.client.gui.clickgui.component

import rbq.wtf.lycoris.client.gui.Font.FontLoaders
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI
import rbq.wtf.lycoris.client.gui.clickgui.utils.RenderUtil
import rbq.wtf.lycoris.client.value.BooleanValue
import java.awt.Color

class BooleanValueComponent(
    private val value: BooleanValue, clickGUI: ClickGUI,
    override var startX: Float,
    override var startY: Float,
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int
) : ClickableComponent(clickGUI) {

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontLoaders.default20.drawString(value.name,x, y, Color(255, 255, 255).rgb)
        //        RenderUtil.stopGlScissor();
        if (value.get()) {
            //Draw Switch Background
            /*Render Glow*/
//            RenderUtil.startGlScissor((int) (x + 287), (int) (y + 15),26, 8);
//            RenderUtil.drawFilledCircle(x + 295, y + 10, 6,
//                    new Color(79,66,184).getRGB(),5);
//            RenderUtil.drawRect(x + 295 - 1, y + 4, x + 295 + 10,
//                    y + 16,
//                    new Color(79,66,184).getRGB());
//            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 6,
//                    new Color(79,66,184).getRGB(),5);
//            BlurUtil.blurAll(3);
//            RenderUtil.stopGlScissor();
            /*Glow End*/
            RenderUtil.drawFilledCircle(
                (x + 295).toDouble(), (y + 10).toDouble(), 6.0,
                Color(79, 66, 184).rgb, 5
            )
            RenderUtil.drawRect(
                (x + 295 - 1).toFloat(), (y + 4).toFloat(), (x + 295 + 10).toFloat(),
                (
                        y + 16).toFloat(),
                Color(79, 66, 184).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + 305 - 1).toDouble(), (y + 10).toDouble(), 6.0,
                Color(79, 66, 184).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + 295).toDouble(), (y + 10).toDouble(), 5.0,
                Color(26, 16, 46).rgb, 5
            )
            RenderUtil.drawRect(
                (x + 295 - 1).toFloat(), (y + 5).toFloat(), (x + 295 + 10).toFloat(),
                (
                        y + 15).toFloat(),
                Color(26, 16, 46).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + 305 - 1).toDouble(), (y + 10).toDouble(), 5.0,
                Color(26, 16, 46).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + 305 - 1).toDouble(), (y + 10).toDouble(), 3.0,
                Color(98, 96, 207).rgb, 5
            )
        } else {
            //Draw Switch Background
//            RenderUtil.startGlScissor((int) (x + 287), (int) (y + 15),26, 10);
//            RenderUtil.drawFilledCircle(x + 295, y + 10, 6,
//                    new Color(83,69,193).getRGB(),5);
//            RenderUtil.drawRect(x + 295 - 1, y + 4, x + 295 + 10,
//                    y + 16,
//                    new Color(83,69,193).getRGB());
//            RenderUtil.drawFilledCircle(x + 305 - 1, y + 10, 6,
//                    new Color(83,69,193).getRGB(),5);
//            BlurUtil.blurAll(3);
//            RenderUtil.stopGlScissor();
            RenderUtil.drawFilledCircle(
                (x + 295).toDouble(), (y + 10).toDouble(), 6.0,
                Color(83, 69, 193).rgb, 5
            )
            RenderUtil.drawRect(
                (x + 295 - 1).toFloat(), (y + 4).toFloat(), (x + 295 + 10).toFloat(),
                (
                        y + 16).toFloat(),
                Color(83, 69, 193).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + 305 - 1).toDouble(), (y + 10).toDouble(), 6.0,
                Color(83, 69, 193).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + 295).toDouble(), (y + 10).toDouble(), 5.0,
                Color(25, 25, 25).rgb, 5
            )
            RenderUtil.drawRect(
                (x + 295 - 1).toFloat(), (y + 5).toFloat(), (x + 295 + 10).toFloat(),
                (
                        y + 15).toFloat(),
                Color(25, 25, 25).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + 305 - 1).toDouble(), (y + 10).toDouble(), 5.0,
                Color(25, 25, 25).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + 295).toDouble(), (y + 10).toDouble(), 3.0,
                Color(80, 81, 81).rgb, 5
            )
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isHovered(
                x + 295 - 5, y + 3, x + 295 + 10,
                y + 17, mouseX, mouseY
            )
        ) {
            value.set(!value.get())
        }
    }

    override fun mouseReleased(mouseButton: Int) {
        TODO("Not yet implemented")
    }
}