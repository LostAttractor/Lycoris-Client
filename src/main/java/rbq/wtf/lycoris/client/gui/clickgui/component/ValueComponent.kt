package rbq.wtf.lycoris.client.gui.clickgui.component

import rbq.wtf.lycoris.client.gui.Font.FontLoaders
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI
import rbq.wtf.lycoris.client.gui.clickgui.utils.RenderUtil
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.value.BooleanValue
import java.awt.Color

class ValueListComponent(
    override var startX: Float,
    override var startY: Float,
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    var module: Module?, clickGUI: ClickGUI
) : Component(clickGUI) {

    companion object {
        const val WEIGHT = ClickGUI.WEIGHT - ModuleButtonListComponent.WEIGHT
        const val HEIGHT =
            ClickGUI.HEIGHT - CategoryButtonListComponent.HEIGHT - 7 - 10 //统一减7, 右下角LOGO然后文字高度是10, 所以再-10
        const val OFFSET_X = ModuleButtonListComponent.WEIGHT
        const val OFFSET_Y = CategoryButtonListComponent.HEIGHT
        //const val HOVER_OFFSET_X = 5
        //const val HOVER_OFFSET_Y = 2
    }

    private var valueWheel = 0F
    private var listPixel = 0F
    private val valueComponents = ArrayList<ValueComponent>()

    fun changeModule(module: Module?) {
        this.module = module
        loadValues()
    }

    fun loadValues() {
        valueComponents.clear()
        valueWheel = 0F

        if (module != null) {
            listPixel = 0F
            var valueY = offsetY - valueWheel
            module!!.values.forEach {
                var offsetY = 0F
                if (it is BooleanValue) {
                    val component = BooleanValueComponent(
                        startX,
                        startY,
                        offsetX,
                        valueY,
                        width,
                        BooleanValueComponent.HEIGHT.toFloat(),
                        BooleanValueComponent.HOVER_OFFSET_X,
                        BooleanValueComponent.HOVER_OFFSET_Y,
                        it,
                        clickGUI
                    )
                    valueComponents.add(component)
                    offsetY = component.height + BooleanValueComponent.OFFSET_Y
                }
                valueY += offsetY
                listPixel += offsetY
            }
        }
    }

    override fun mouseWheelScroll(mouseX: Int, mouseY: Int, mouseWheel: Int) {
        //Modules Mouse Wheel
        val mouseWheelMin = 0F
        val mouseWheelMax = listPixel - height

        if (isHovered(mouseX, mouseY)) {
            if (mouseWheel < 0 && valueWheel < mouseWheelMax) {
                valueWheel =
                    if (valueWheel + 7 < mouseWheelMax) valueWheel + 7 else mouseWheelMax
            }
            if (mouseWheel > 0 && valueWheel > mouseWheelMin) {
                valueWheel =
                    if (valueWheel - 7 > mouseWheelMin) valueWheel - 7 else mouseWheelMin
            }

            var valueY = offsetY - valueWheel
            valueComponents.forEach {
                it.updateOffset(offsetX, valueY)
                if (it is BooleanValueComponent) {
                    valueY += it.height + BooleanValueComponent.OFFSET_Y
                }
            }
        }
    }

    override fun updateStart(startX: Float, startY: Float) {
        super.updateStart(startX, startY)
        valueComponents.forEach { it.updateStart(startX, startY) }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        valueComponents.forEach { it.mouseClicked(mouseX, mouseY, mouseButton) }
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        RenderUtil.startGlScissor(x.toInt(), y.toInt(), width.toInt(), height.toInt())
        valueComponents.forEach { it.render(mouseX, mouseY, partialTicks) }
        RenderUtil.stopGlScissor()
    }
}

abstract class ValueComponent(clickGUI: ClickGUI) : ClickableComponent(clickGUI)

class BooleanValueComponent(
    override var startX: Float,
    override var startY: Float,
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int,
    val value: BooleanValue,
    clickGUI: ClickGUI
) : ValueComponent(clickGUI) {

    companion object {
        const val HEIGHT = 20
        const val OFFSET_Y = 5
        const val HOVER_OFFSET_X = 0
        const val HOVER_OFFSET_Y = 2
        const val BUTTON_OFFSET_X = 295
        const val BUTTON_HOVER_OFFSET_X = 10
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontLoaders.default20.drawString(value.name, x, y, Color(255, 255, 255).rgb)
        if (value.get()) { //
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + 10).toDouble(), 6.0,
                Color(79, 66, 184).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4, x + BUTTON_OFFSET_X + 10 + 1, y + 16,
                Color(79, 66, 184).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 10).toDouble(), (y + 10).toDouble(), 6.0,
                Color(79, 66, 184).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + 10).toDouble(), 5.0,
                Color(26, 16, 46).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4 + 1, x + BUTTON_OFFSET_X + 10 + 1, y + 16 - 1,
                Color(26, 16, 46).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 10).toDouble(), (y + 10).toDouble(), 5.0,
                Color(26, 16, 46).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 10).toDouble(), (y + 10).toDouble(), 3.0,
                Color(98, 96, 207).rgb, 5
            )
        } else {
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + 10).toDouble(), 6.0,
                Color(83, 69, 193).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4, x + BUTTON_OFFSET_X + 10 + 1, y + 16,
                Color(83, 69, 193).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 10).toDouble(), (y + 10).toDouble(), 6.0,
                Color(83, 69, 193).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + 10).toDouble(), 5.0,
                Color(25, 25, 25).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4 + 1, x + BUTTON_OFFSET_X + 10 + 1, y + 15,
                Color(25, 25, 25).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 10).toDouble(), (y + 10).toDouble(), 5.0,
                Color(25, 25, 25).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + 10).toDouble(), 3.0,
                Color(80, 81, 81).rgb, 5
            )
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isHovered(
                (x + BUTTON_OFFSET_X - BUTTON_HOVER_OFFSET_X).toInt(),
                y.toInt(),
                (x + BUTTON_OFFSET_X + BUTTON_HOVER_OFFSET_X).toInt(),
                endY.toInt(),
                mouseX,
                mouseY
            ) && mouseButton == 0
        )
            value.set(!value.get())
    }
}