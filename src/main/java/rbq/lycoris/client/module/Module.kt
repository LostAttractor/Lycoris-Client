package rbq.lycoris.client.module

import org.lwjgl.input.Keyboard
import rbq.lycoris.client.Client
import rbq.lycoris.client.event.Listenable
import rbq.lycoris.client.utils.ColorUtils.stripColor
import rbq.lycoris.client.utils.MinecraftInstance
import rbq.lycoris.client.value.Value

open class Module : MinecraftInstance(), Listenable {
    // Module information
    // TODO: Remove ModuleInfo and change to constructor (#Kotlin)
    var name: String
    var description: String
    var category: ModuleCategory
    var keyBind = Keyboard.CHAR_NONE
        set(keyBind) {
            field = keyBind
            Client.configManager.saveConfig(Client.configManager.modulesConfig)
        }
    var array = true
        set(array) {
            field = array
            Client.configManager.saveConfig(Client.configManager.modulesConfig)
        }
    val saveState: Boolean

    var slideStep = 0F

    init {
        val moduleInfo = javaClass.getAnnotation(ModuleInfo::class.java)!!

        name = moduleInfo.name
        description = moduleInfo.description
        category = moduleInfo.category
        keyBind = moduleInfo.keyBind
        array = moduleInfo.array
        saveState = moduleInfo.saveState
    }

    // Current state of module
    var state = false
        set(value) {
            if (field == value)
                return

            // Call toggle
            onToggle(value)

            // Play sound and add notification
            //mc.soundHandler.playSound(PositionedSoundRecord.create(ResourceLocation("random.click"), 1F))
            //Client.hud.addNotification(Notification("${if (value) "Enabled " else "Disabled "}$name"))

            // Call on enabled or disabled
            field = value
            if (value) {
                onEnable()
            } else {
                onDisable()
            }
            // Save module state
            Client.configManager.saveConfig(Client.configManager.modulesConfig)
        }


    // HUD
    val hue = Math.random().toFloat()
    var slide = 0F

    // Tag
    open val tag: String?
        get() = null

    val tagName: String
        get() = "$name${if (tag == null) "" else " §7$tag"}"

    val colorlessTagName: String
        get() = "$name${if (tag == null) "" else " " + stripColor(tag)}"

    /**
     * Toggle module
     */
    fun toggle() {
        state = !state
    }

    /**
     * Called when module toggled
     */
    open fun onToggle(state: Boolean) {}

    /**
     * Called when module enabled
     */
    open fun onEnable() {}

    /**
     * Called when module disabled
     */
    open fun onDisable() {}

    /**
     * Get module by [valueName]
     */
    open fun getValue(valueName: String) = values.find { it.name.equals(valueName, ignoreCase = true) }

    /**
     * Get all values of module
     */
    open val values: List<Value<*>>
        get() = javaClass.declaredFields.map { valueField ->
            valueField.isAccessible = true
            valueField[this]
        }.filterIsInstance<Value<*>>().filter { it.isSupported }

    /**
     * Events should be handled when module is enabled
     */
    override fun handleEvents() = state
}