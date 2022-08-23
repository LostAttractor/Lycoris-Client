package rbq.wtf.lycoris.client.clickgui.component

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.clickgui.ClickGUI

abstract class Component(val clickGUI: ClickGUI) : IComponent

abstract class ClickableComponent(val clickGUI: ClickGUI) : IClickableComponent

interface IClickableComponent : IComponent {
    override var hoverOffsetX: Int
    override var hoverOffsetY: Int

    override val hoverStartX: Int
        get() = (x - hoverOffsetX).toInt()
    override val hoverStartY: Int
        get() = (y - hoverOffsetY).toInt()

    override val hoverEndX: Int
        get() = (endX + hoverOffsetX).toInt()
    override val hoverEndY: Int
        get() = (endY + hoverOffsetY).toInt()
}

interface IComponent {
    val startX: Float
        get() = Client.clickGUI.startX
    val startY: Float
        get() = Client.clickGUI.startY
    var offsetX: Float
    var offsetY: Float
    var width: Float
    var height: Float

    val x: Float
        get() = startX + offsetX
    val y: Float
        get() = startY + offsetY

    val endX: Float
        get() = x + width
    val endY: Float
        get() = y + height

    val hoverStartX: Int
        get() = x.toInt()
    val hoverStartY: Int
        get() = y.toInt()

    val hoverEndX: Int
        get() = endX.toInt()
    val hoverEndY: Int
        get() = endY.toInt()

    val hoverOffsetX: Int
        get() = 0
    val hoverOffsetY: Int
        get() = 0

    fun render(mouseX: Int, mouseY: Int, partialTicks: Float)
    fun keyTyped(typedChar: Char, keyCode: Int) {}
    fun updateComponent(mouseX: Int, mouseY: Int) {}
    fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {}
    fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {}
    fun mouseWheelScroll(mouseX: Int, mouseY: Int, mouseWheel: Int) {}

//    fun updateStart(startX: Float, startY: Float) {
//        this.startX = startX
//        this.startY = startY
//    }

    fun updateOffset(offsetX: Float, offsetY: Float) {
        this.offsetX = offsetX
        this.offsetY = offsetY
    }

    fun isHovered(startX: Int, startY: Int, endX: Int, endY: Int, mouseX: Int, mouseY: Int): Boolean {
        return mouseX in startX - hoverOffsetX..endX + hoverOffsetX && mouseY in startY - hoverOffsetY..endY + hoverOffsetY
    }

    fun isHovered(mouseX: Int, mouseY: Int): Boolean {
        return mouseX in hoverStartX..hoverEndX && mouseY in hoverStartY..hoverEndY
    }
}