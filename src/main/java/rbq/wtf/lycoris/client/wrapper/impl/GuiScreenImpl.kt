package rbq.wtf.lycoris.client.wrapper.impl

abstract class GuiScreenImpl {
    abstract fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float)
    open fun initGui() {}
    open fun onGuiClosed() {}
    open fun keyTyped(typedChar: Char, keyCode: Int): Boolean = true
    open fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {}
    open fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {}
    open fun updateScreen() {}
}