package rbq.wtf.lycoris.client.clickgui.component

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.font.FontLoaders
import rbq.wtf.lycoris.client.clickgui.ClickGUI
import rbq.wtf.lycoris.client.clickgui.utils.RenderUtil
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import java.awt.Color

class ModuleButtonListComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    var category: ModuleCategory, clickGUI: ClickGUI
) : Component(clickGUI) {

    companion object {
        const val OFFSET_X = 0F
        const val OFFSET_Y = 45F
        const val WEIGHT = 120F //100
        const val HEIGHT = ClickGUI.HEIGHT - OFFSET_Y - 7
        const val HOVER_OFFSET_X = 0
        const val HOVER_OFFSET_Y = 7 //5 8
    }

    private var moduleWheel = 0F
    private val moduleButtons = ArrayList<ModuleButtonComponent>()

    //TODO: 暂存ModuleWheel数值

    fun changeCategory(category: ModuleCategory) {
        this.category = category
        loadModules()
    }

    fun loadModules() {
        moduleButtons.clear()
        moduleWheel = 0F

        var moduleY = offsetY - moduleWheel
        Client.moduleManager.getModulesInType(category).forEach {
            moduleButtons.add(
                ModuleButtonComponent(
                    offsetX,
                    moduleY,
                    width,
                    FontLoaders.default20.height.toFloat(),
                    HOVER_OFFSET_X,
                    HOVER_OFFSET_Y,
                    it,
                    clickGUI
                )
            )
            moduleY += FontLoaders.default20.height.toFloat() + HOVER_OFFSET_Y * 2
        }
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        RenderUtil.startGlScissor(x.toInt(), y.toInt() + 10, width.toInt(), height.toInt() - 10) //45
        moduleButtons.forEach { it.render(mouseX, mouseY, partialTicks) }
        RenderUtil.stopGlScissor()
    }

    override fun mouseWheelScroll(mouseX: Int, mouseY: Int, mouseWheel: Int) {
        //Modules Mouse Wheel
        val listPixel =
            (FontLoaders.default20.height.toFloat() + 15F) * Client.moduleManager.getModulesInType(category).size - 15F
        val mouseWheelMin = 0F
        val mouseWheelMax = listPixel - height

        if (isHovered(mouseX, mouseY)) {
            if (mouseWheel < 0 && moduleWheel < mouseWheelMax) {
                moduleWheel =
                    if (moduleWheel + 7 < mouseWheelMax) moduleWheel + 7 else mouseWheelMax
            }
            if (mouseWheel > 0 && moduleWheel > mouseWheelMin) {
                moduleWheel =
                    if (moduleWheel - 7 > mouseWheelMin) moduleWheel - 7 else mouseWheelMin
            }
            var moduleY = offsetY - moduleWheel
            moduleButtons.forEach {
                it.updateOffset(offsetX, moduleY)
                moduleY += FontLoaders.default20.height.toFloat() + HOVER_OFFSET_Y * 2
            }
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        moduleButtons.forEach { it.mouseClicked(mouseX, mouseY, mouseButton) }
    }
}


class ModuleButtonComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int,
    val module: Module, clickGUI: ClickGUI
) : ClickableComponent(clickGUI) {

    companion object {
        const val TEXT_OFFSET_X = 13F
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val color = if (module.state) Color(87, 72, 216) else Color(255, 255, 255)
        FontLoaders.default20.drawString(module.name, x + TEXT_OFFSET_X, y, color.rgb)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isHovered(mouseX, mouseY)) {
            when (mouseButton) {
                0 -> module.toggle()
                1 -> clickGUI.setModule(module)
                else -> {}
            }
        }
    }
}