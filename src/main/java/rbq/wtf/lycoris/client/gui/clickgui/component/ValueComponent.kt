package rbq.wtf.lycoris.client.gui.clickgui.component

import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import rbq.wtf.lycoris.client.font.FontLoaders
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI
import rbq.wtf.lycoris.client.gui.clickgui.utils.RenderUtil
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.utils.MathUtils
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.ModeValue
import rbq.wtf.lycoris.client.value.NumberValue
import java.awt.Color

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
    }

    private val valueComponents = ArrayList<ValueComponent>()

    private var valueWheel = 0F
    private var listPixel = 0F
    private val valueY
        get() = offsetY - valueWheel + listPixel

    fun changeModule(module: Module?) {
        this.module = module
        loadValues()
    }

    fun loadValues() {
        valueComponents.clear()
        valueWheel = 0F

        if (module != null) {
            listPixel = 0F
            module!!.values.forEach {
                when (it) {
                    is BooleanValue -> {
                        addComponent(BooleanValueComponent(
                            offsetX,
                            valueY,
                            it,
                            clickGUI
                        ))
                    }
                    is NumberValue -> {
                        addComponent(NumberValueComponent(
                            offsetX,
                            valueY,
                            it,
                            clickGUI
                        ))
                    }
                    is ModeValue -> {
                        addComponent(ModeValueComponent(
                            offsetX,
                            valueY,
                            it,
                            clickGUI
                        ))
                    }
                }
            }
            addComponent(ModuleBindComponent(
                offsetX,
                valueY,
                module!!,
                clickGUI
            ))
        }
    }

    private fun addComponent(component: ValueComponent) {
        valueComponents.add(component)
        listPixel += component.height
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

            //var valueY = offsetY - valueWheel
            listPixel = 0F
            valueComponents.forEach {
                it.updateOffset(offsetX, valueY)
                listPixel += it.height
            }
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        valueComponents.forEach { it.mouseClicked(mouseX, mouseY, mouseButton) }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {
        valueComponents.forEach { it.mouseReleased(mouseX, mouseY, mouseButton) }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        valueComponents.forEach { it.keyTyped(typedChar, keyCode) }
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        RenderUtil.startGlScissor(x.toInt(), y.toInt() + 10, width.toInt(), height.toInt() - 10)
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
        const val WEIGHT = ValueListComponent.WEIGHT
        const val HEIGHT = 20F

        const val HOVER_OFFSET_X = 5
        const val HOVER_OFFSET_Y = 2
        const val BUTTON_OFFSET_X = 295F
        const val BUTTON_WEIGHT = 10F
        const val BUTTON_CIRCLE_OFFSET_Y = HEIGHT / 2
        const val BUTTON_END_X = BUTTON_OFFSET_X + BUTTON_WEIGHT
    }
    constructor(offsetX: Float, offsetY: Float, value: BooleanValue, clickGUI: ClickGUI) :
            this(
                offsetX, offsetY,
                WEIGHT,
                HEIGHT,
                HOVER_OFFSET_X,
                HOVER_OFFSET_Y, value, clickGUI
            )

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontLoaders.default20.drawString(value.name, x, y, Color(255, 255, 255).rgb)
        if (value.get()) { //
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 6.0,
                Color(79, 66, 184).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4, x + BUTTON_END_X + 1, y + 16,
                Color(79, 66, 184).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_END_X).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 6.0,
                Color(79, 66, 184).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 5.0,
                Color(26, 16, 46).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4 + 1, x + BUTTON_END_X + 1, y + 16 - 1,
                Color(26, 16, 46).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_END_X).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 5.0,
                Color(26, 16, 46).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_END_X).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 3.0,
                Color(98, 96, 207).rgb, 5
            )
        } else {
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 6.0,
                Color(83, 69, 193).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4, x + BUTTON_END_X + 1, y + 16,
                Color(83, 69, 193).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_END_X).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 6.0,
                Color(83, 69, 193).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 5.0,
                Color(25, 25, 25).rgb, 5
            )
            RenderUtil.drawRect(
                x + BUTTON_OFFSET_X, y + 4 + 1, x + BUTTON_END_X + 1, y + 15,
                Color(25, 25, 25).rgb
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_END_X).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 5.0,
                Color(25, 25, 25).rgb, 5
            )
            RenderUtil.drawFilledCircle(
                (x + BUTTON_OFFSET_X + 1).toDouble(), (y + BUTTON_CIRCLE_OFFSET_Y).toDouble(), 3.0,
                Color(80, 81, 81).rgb, 5
            )
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isHovered(
                (x + BUTTON_OFFSET_X).toInt(),
                y.toInt(),
                (x + BUTTON_END_X).toInt(),
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
    companion object {
        const val WEIGHT = ValueListComponent.WEIGHT
        const val HEIGHT = 45F
        const val HOVER_OFFSET_X = 0
        const val HOVER_OFFSET_Y = 0
        const val BUTTON_WEIGHT = 305F //295
        const val BUTTON_OFFSET_Y = 19F
        const val BUTTON_CIRCLE_RADIUS = 6.0
        const val BUTTON_CIRCLE_RADIUS_S = 5.0
        const val BUTTON_CIRCLE_OFFSET_Y = BUTTON_OFFSET_Y + BUTTON_CIRCLE_RADIUS
        const val BUTTON_END_Y = BUTTON_OFFSET_Y + BUTTON_CIRCLE_RADIUS * 2
    }

    private var settingValue: Float = value.get()
    private val currentValue
        get() = if (onSetting) settingValue else value.get()

    constructor(offsetX: Float, offsetY: Float, value: NumberValue, clickGUI: ClickGUI) :
            this(
                offsetX, offsetY,
                WEIGHT,
                HEIGHT,
                HOVER_OFFSET_X,
                HOVER_OFFSET_Y, value, clickGUI
            )

    private var onSetting = false

    override fun updateComponent(mouseX: Int, mouseY: Int) {
        if (onSetting) {
//            if ((mouseX - (x + 8)) in 0F..BUTTON_WEIGHT - 8F) {
            val current =
                (mouseX - (this.x + 8)) / (BUTTON_WEIGHT - 8) * (value.maximum - value.minimum) + value.minimum
            if (current in value.minimum..value.maximum) settingValue = MathUtils.round(current, value.increase)
            if (current <= value.minimum) settingValue = value.minimum
            if (current >= value.maximum) settingValue = value.maximum
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
        ) {
            settingValue = currentValue;
            onSetting = true
        }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (mouseButton == 0 && onSetting) {
            value.set(settingValue)
            onSetting = false
        }
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontLoaders.default20.drawString(
            value.name + "[" + currentValue + "]",
            x,
            y,
            Color(255, 255, 255).rgb
        )
        val buttonOffsetX =
            (BUTTON_WEIGHT - 8) * ((currentValue - value.minimum) / (value.maximum - value.minimum)) //295
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

class ModeValueComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int,
    val value: ModeValue,
    clickGUI: ClickGUI
) : ValueComponent(clickGUI) {
    companion object {
        const val WEIGHT = ValueListComponent.WEIGHT
        const val HEIGHT = 35F
        const val HOVER_OFFSET_X = 10
        const val HOVER_OFFSET_Y = 0
        const val BUTTON_OFFSET_X = 220F
        const val BUTTON_OFFSET_Y = 10F
        const val BUTTON_WEIGHT = 90F
        const val BUTTON_HEIGHT = 20F
        const val BUTTON_END_X = BUTTON_OFFSET_X + BUTTON_WEIGHT
        const val BUTTON_END_Y = BUTTON_OFFSET_Y + BUTTON_HEIGHT
    }

    constructor(offsetX: Float, offsetY: Float, value: ModeValue, clickGUI: ClickGUI) :
            this(
                offsetX, offsetY,
                WEIGHT,
                HEIGHT,
                HOVER_OFFSET_X,
                HOVER_OFFSET_Y, value, clickGUI
            )
    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontLoaders.default20.drawString(
            value.name,
            x,
            y,
            Color(255, 255, 255).rgb
        )
        RenderUtil.drawFastRoundedRect(
            x + BUTTON_OFFSET_X,
            y + BUTTON_OFFSET_Y,
            x + BUTTON_END_X,
            y + BUTTON_END_Y,
            2f,
            Color(83, 69, 193).rgb
        )
        RenderUtil.drawFastRoundedRect(
            x + BUTTON_OFFSET_X + 1,
            y + BUTTON_OFFSET_Y + 1,
            x + BUTTON_END_X - 1,
            y + BUTTON_END_Y - 1,
            2f,
            Color(27, 27, 27).rgb
        )
        println(value.modeName)
        FontLoaders.default20.drawCenteredString(
            value.modeName,
            x + BUTTON_OFFSET_X + 45,
            y + BUTTON_OFFSET_Y + 6,
            Color(255, 255, 255).rgb
        )
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (this.isHovered(
                (x + BUTTON_OFFSET_X).toInt(),
                (y + BUTTON_OFFSET_Y).toInt(),
                (x + BUTTON_END_X).toInt(),
                (y + BUTTON_END_Y).toInt(),
                mouseX,
                mouseY
            )) {
            value.incrementSelection()
        }
    }
}

class ModuleBindComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int,
    val module: Module,
    clickGUI: ClickGUI
) : ValueComponent(clickGUI) {
    companion object {
        const val WEIGHT = ValueListComponent.WEIGHT
        const val HEIGHT = 15F //18?
        const val HOVER_OFFSET_X = 0
        const val HOVER_OFFSET_Y = 0
    }

    private var onBinding = false

    constructor(offsetX: Float, offsetY: Float, module: Module, clickGUI: ClickGUI) :
            this(offsetX, offsetY, WEIGHT, HEIGHT, HOVER_OFFSET_X, HOVER_OFFSET_Y, module, clickGUI)

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (onBinding) {
            FontLoaders.default18.drawStringWithShadow(
                "Bind to...",
                x.toDouble(),
                y.toDouble(),
                -1
            )
        } else {
            FontLoaders.default18.drawStringWithShadow(
                "Bind:" + Keyboard.getKeyName(module.keyBind),
                x.toDouble(),
                y.toDouble(),
                -1
            )
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (this.isHovered(
                x.toInt(),
                (y).toInt(),
                (x + FontLoaders.default18.getStringWidth("Bind:" + Keyboard.getKeyName(module.keyBind))).toInt(),
                (endY).toInt(),
                mouseX,
                mouseY
            )
        ) {
            if (mouseButton == 0) {
                onBinding = true
                //clickGUI.currentActiveTextValue = this
            } else if (Mouse.isButtonDown(3)) {
                module.keyBind = Keyboard.CHAR_NONE
            }
        } else {
            if (onBinding) {
                onBinding = false
            }
        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (onBinding) {
            module.keyBind = keyCode
            onBinding = false
        }
    }
}
