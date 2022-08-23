package rbq.wtf.lycoris.client.gui.clickgui.component

import rbq.wtf.lycoris.client.font.FontLoaders
import rbq.wtf.lycoris.client.gui.clickgui.ClickGUI
import rbq.wtf.lycoris.client.module.ModuleCategory
import java.awt.Color

class CategoryButtonListComponent(
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
        const val HEIGHT = 45F
        const val HOVER_OFFSET_X = 7
        const val HOVER_OFFSET_Y = 14
        const val TEXT_OFFSET_X = 14
        const val TEXT_OFFSET_Y = 14
    }

    private val categoryButtons = ArrayList<CategoryButtonComponent>()

    fun loadCategory() {
        var categoryX = offsetX
        ModuleCategory.values().forEach {
            categoryButtons.add(
                CategoryButtonComponent(
                    categoryX + TEXT_OFFSET_X, offsetY + TEXT_OFFSET_Y,
                    FontLoaders.default20.getStringWidth(it.name).toFloat(),
                    FontLoaders.default20.height.toFloat(),
                    HOVER_OFFSET_X, HOVER_OFFSET_Y, it, clickGUI
                )
            )
            categoryX += FontLoaders.default20.getStringWidth(it.name) + TEXT_OFFSET_X
        }
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        categoryButtons.forEach { it.render(mouseX, mouseY, partialTicks) }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        var isHoveredCategory = false
        categoryButtons.forEach {
            if (it.mouseClickedB(mouseX, mouseY, mouseButton)) isHoveredCategory = true
        }
        if (!isHoveredCategory && isHovered(
                startX.toInt(),
                (startY - 10).toInt(),
                endX.toInt(),
                endY.toInt(),
                mouseX,
                mouseY
            ) && mouseButton == 0
        ) {
            clickGUI.moveOffsetX = mouseX - startX
            clickGUI.moveOffsetY = mouseY - startY
            clickGUI.onMoving = true
        }
    }
}

class CategoryButtonComponent(
    override var offsetX: Float,
    override var offsetY: Float,
    override var width: Float,
    override var height: Float,
    override var hoverOffsetX: Int,
    override var hoverOffsetY: Int,
    val category: ModuleCategory, clickGUI: ClickGUI
) : ClickableComponent(clickGUI) {

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val color = if (category == clickGUI.currentModuleType) Color(101, 81, 255) else Color(255, 255, 255)
        FontLoaders.default20.drawString(category.toString(), x, y, color.rgb)
    }

    fun mouseClickedB(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        mouseClicked(mouseX, mouseY, mouseButton)
        return isHovered(mouseX, mouseY) && mouseButton == 0
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isHovered(mouseX, mouseY) && mouseButton == 0) {
            clickGUI.setCategory(category)
        }
    }
}