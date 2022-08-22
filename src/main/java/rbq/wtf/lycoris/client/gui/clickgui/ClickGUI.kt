package rbq.wtf.lycoris.client.gui.clickgui

import org.lwjgl.input.Mouse
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.gui.clickgui.component.*
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.modules.render.ClickGUI
import rbq.wtf.lycoris.client.wrapper.wrappers.impl.GuiScreenImpl

class ClickGUI : GuiScreenImpl() {
    companion object {
        const val WEIGHT = 445F
        const val HEIGHT = 327F
    }

    private val componentList = ArrayList<Component>()

    private val backGroundComponent: BackGroundCompoent
    private val categoryButtonListComponent: CategoryButtonListComponent
    private val moduleButtonListComponent: ModuleButtonListComponent
    private val valueListComponent: ValueListComponent

    var currentModuleType = ModuleCategory.values()[0]
    var currentModule: Module? = if (Client.moduleManager.getModulesInType(currentModuleType)
            .isNotEmpty()
    ) Client.moduleManager.getModulesInType(currentModuleType)[0] else null
    var currentActiveTextValue: Component? = null
    
    private var startX = 100F
    private var startY = 100F
    var onMoving = false
    var moveOffsetX = 0F
    var moveOffsetY = 0F

    init {
        backGroundComponent = BackGroundCompoent(
            startX,
            startY,
            BackGroundCompoent.OFFSET_X, BackGroundCompoent.OFFSET_Y,
            BackGroundCompoent.WEIGHT, BackGroundCompoent.HEIGHT,
            this
        )
        categoryButtonListComponent = CategoryButtonListComponent(
            startX,
            startY,
            CategoryButtonListComponent.OFFSET_X,
            CategoryButtonListComponent.OFFSET_Y,
            CategoryButtonListComponent.WEIGHT,
            CategoryButtonListComponent.HEIGHT,
            this
        )
        moduleButtonListComponent = ModuleButtonListComponent(
            startX,
            startY,
            ModuleButtonListComponent.OFFSET_X,
            ModuleButtonListComponent.OFFSET_Y,
            ModuleButtonListComponent.WEIGHT,
            ModuleButtonListComponent.HEIGHT,
            currentModuleType,
            this
        )
        valueListComponent = ValueListComponent(
            startX,
            startY,
            ValueListComponent.OFFSET_X,
            ValueListComponent.OFFSET_Y,
            ValueListComponent.WEIGHT,
            ValueListComponent.HEIGHT,
            Client.moduleManager.getModulesInType(currentModuleType)[0],
            this
        )
        componentList.add(backGroundComponent)
        componentList.add(categoryButtonListComponent)
        componentList.add(moduleButtonListComponent)
        componentList.add(valueListComponent)
    }

    override fun initGui() {
        categoryButtonListComponent.loadCategory()
        moduleButtonListComponent.loadModules()
        valueListComponent.loadValues()
        onMoving = false
        moveOffsetX = 0F
        moveOffsetY = 0F
    }

    override fun onGuiClosed() {
        Client.moduleManager.getModuleByClass(ClickGUI::class.java).state = false
        onMoving = false
        moveOffsetX = 0F
        moveOffsetY = 0F
    }

    fun setCategory(category: ModuleCategory) {
        currentModuleType = category
        moduleButtonListComponent.changeCategory(category)
        currentModule =
            if (Client.moduleManager.getModulesInType(category).isNotEmpty()) Client.moduleManager.getModulesInType(
                category
            )[0] else null
        setModule(currentModule)
    }

    fun setModule(module: Module?) {
        currentModule = module
        valueListComponent.changeModule(module)
        currentActiveTextValue = null
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (onMoving) {
            startX = mouseX - moveOffsetX
            startY = mouseY - moveOffsetY
            componentList.forEach { it.updateStart(startX, startY) }
        }

        componentList.forEach { it.render(mouseX, mouseY, partialTicks) }

        val mouseWheel: Int = Mouse.getDWheel()
        if (mouseWheel != 0)
            componentList.forEach { it.mouseWheelScroll(mouseX, mouseY, mouseWheel) }

        //DrawCategory
//        var categoryX = 0f
//        for (c in categoryButtonList) {
//            c.updateComponent(
//                startX + 14 + categoryX,
//                startY + 14,
//                mouseX,
//                mouseY
//            )
//            categoryX += c.height + 15
//        }

        //DrawValues
//        var valueY = (startY + 45)
//        for (component in valueComponentList) {
//            component.updateComponent(
//                startX + VALUECOMPONENT_XOFFSET,
//                (valueWheel + valueY).toInt(),
//                mouseX,
//                mouseY
//            )
//            valueY += component.height
//        }
//        if (isHovered((startX + 110), startY + 35.0f, startX + 440.0f, startY + 330.0f, mouseX, mouseY)) {
//            if (mouseWheel < 0 && valueY + valueWheel - startY > 325) {
//                valueWheel = valueWheel - 7
//            }
//            if (mouseWheel > 0 && valueWheel != 0) {
//                valueWheel = valueWheel + 7
//            }
//        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        //ClickGui Move
        if (onMoving) return
        componentList.forEach { it.mouseClicked(mouseX, mouseY, mouseButton) }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        //ClickGui Move Reset
        if (onMoving) {
            onMoving = false
            moveOffsetX = 0F
            moveOffsetY = 0F
        }
        componentList.forEach { it.mouseReleased(mouseX, mouseY, state) }
//        if (isHovered((startX + 110), startY + 35.0f, startX + 440.0f, startY + 330.0f, mouseX, mouseY)) {
//            if (currentModule != null) {
//                var valueY = (startY + 50)
//                for (component in valueComponentList) {
//                    component.mouseReleased(state)
//                    valueY += component.height
//                }
//            }
//        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int): Boolean {
        super.keyTyped(typedChar, keyCode)
        if (currentActiveTextValue != null) {
            currentActiveTextValue!!.keyTyped(typedChar, keyCode)
        }
        return true
    }
}