package rbq.wtf.lycoris.client.gui.clickgui.component

import rbq.wtf.lycoris.client.gui.Font.FontLoaders
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI
import rbq.wtf.lycoris.client.gui.clickgui.utils.RenderUtil
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.utils.MathUtils
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.NumberValue
import java.awt.Color
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

class ValueListComponent(
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
                if (it is NumberValue) {
                    val component = NumberValueComponent(
                        offsetX,
                        valueY,
                        width,
                        NumberValueComponent.HEIGHT.toFloat(),
                        NumberValueComponent.HOVER_OFFSET_X,
                        NumberValueComponent.HOVER_OFFSET_Y,
                        it,
                        clickGUI
                    )
                    valueComponents.add(component)
                    offsetY = component.height /*+ NumberValueComponent.OFFSET_Y*/
                }
                valueY += offsetY
                listPixel += offsetY
            }
        }
    }

    override fun updateComponent(mouseX: Int, mouseY: Int) {
        valueComponents.forEach { it.updateComponent(mouseX, mouseY) }
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

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        valueComponents.forEach { it.mouseClicked(mouseX, mouseY, mouseButton) }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {
        valueComponents.forEach { it.mouseReleased(mouseX, mouseY, mouseButton) }
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        RenderUtil.startGlScissor(x.toInt(), y.toInt(), width.toInt(), height.toInt())
        valueComponents.forEach { it.render(mouseX, mouseY, partialTicks) }
        RenderUtil.stopGlScissor()
    }
}

abstract class ValueComponent(clickGUI: ClickGUI) : ClickableComponent(clickGUI)

class BooleanValueComponent(
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

class NumberValueComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int,
    val value: NumberValue,
    clickGUI: ClickGUI
) : ValueComponent(clickGUI) {
//    private override var x = 0f
//    private override var y = 0f

//    init {
//        height = 45
//    }

    companion object {
        const val HEIGHT = 45
        const val HOVER_OFFSET_X = 0
        const val HOVER_OFFSET_Y = 0
        const val BUTTON_WEIGHT = 305 //295
        const val BUTTON_OFFSET_Y = 19
        const val BUTTON_CIRCLE_RADIUS = 6.0
        const val BUTTON_CIRCLE_RADIUS_S = 5.0
        const val BUTTON_CIRCLE_OFFSET_Y = BUTTON_OFFSET_Y + BUTTON_CIRCLE_RADIUS
        const val BUTTON_END_Y = BUTTON_OFFSET_Y + BUTTON_CIRCLE_RADIUS * 2
    }

    var onSetting = false

    override fun updateComponent(mouseX: Int, mouseY: Int) {
        if (onSetting) {
            Logger.debug("1")
            if ((mouseX - (x + 8)) in 0F..BUTTON_WEIGHT - 8F) {
                Logger.debug("2 ${mouseX - (x + 8)}")
                val current = (mouseX - (this.x + 8)) / (BUTTON_WEIGHT - 8) * (value.maximum - value.minimum) + value.minimum
                Logger.debug("3 $current")
                if (current in value.minimum..value.maximum) value.set(MathUtils.round(current, value.increase))
            }
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (this.isHovered(
                (this.x).toInt(),
                (this.y + BUTTON_OFFSET_Y).toInt(),
                (this.endX).toInt(),
                (this.endY).toInt(),
                mouseX,
                mouseY
            ) && mouseButton == 0
        ) onSetting = true
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (mouseButton == 0 && onSetting) onSetting = false
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontLoaders.default20.drawString(
            value.name + "[" + value.get() + "]",
            x,
            y,
            Color(255, 255, 255).rgb
        )
        val buttonOffsetX = (BUTTON_WEIGHT - 8) * ((value.get() - value.minimum) / (value.maximum - value.minimum)) //295
        RenderUtil.drawFilledCircle(
            (x + BUTTON_CIRCLE_RADIUS - 1), (y + BUTTON_CIRCLE_OFFSET_Y), BUTTON_CIRCLE_RADIUS,
            Color(45, 37, 104).rgb, 5
        )
        RenderUtil.drawRect(
            (x + BUTTON_CIRCLE_RADIUS).toFloat(), y + BUTTON_OFFSET_Y, x + BUTTON_WEIGHT + 1,
            y + BUTTON_END_Y.toFloat(),
            Color(45, 36, 104).rgb
        )
        RenderUtil.drawFilledCircle(
            (x + BUTTON_WEIGHT).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y), BUTTON_CIRCLE_RADIUS,
            Color(45, 37, 104).rgb, 5
        )
        RenderUtil.drawFilledCircle(
            (x + BUTTON_CIRCLE_RADIUS_S), (y + BUTTON_CIRCLE_OFFSET_Y), BUTTON_CIRCLE_RADIUS_S,
            Color(45, 45, 45).rgb, 5
        )
        RenderUtil.drawRect(
            x + BUTTON_CIRCLE_RADIUS_S.toFloat(), y + BUTTON_OFFSET_Y + 1, x + BUTTON_WEIGHT,
            (y + BUTTON_END_Y - 1).toFloat(),
            Color(45, 45, 45).rgb
        )
        RenderUtil.drawFilledCircle(
            (x + BUTTON_CIRCLE_RADIUS_S), y + BUTTON_CIRCLE_OFFSET_Y, BUTTON_CIRCLE_RADIUS_S,
            Color(45, 45, 45).rgb, 5
        )
        RenderUtil.drawFilledCircle(
            (x + BUTTON_CIRCLE_RADIUS - 1), y + BUTTON_CIRCLE_OFFSET_Y, BUTTON_CIRCLE_RADIUS,
            Color(97, 79, 237).rgb, 5
        )
        RenderUtil.drawRect(
            (x + 8 - 1), y + BUTTON_OFFSET_Y, (x + 8 + 1 + buttonOffsetX),
            y + 31,
            Color(97, 79, 237).rgb
        )
        RenderUtil.drawFilledCircle(
            (x + 8 + buttonOffsetX).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y), 8.0,
            Color(97, 79, 237).rgb, 5
        )
    }
}
