package rbq.wtf.lycoris.client.manager

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.event.EventTarget
import rbq.wtf.lycoris.client.event.KeyEvent
import rbq.wtf.lycoris.client.event.Listenable
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.modules.combat.AutoClicker
import rbq.wtf.lycoris.client.module.modules.movement.KeepSprint
import rbq.wtf.lycoris.client.module.modules.msic.SelfDestruct
import rbq.wtf.lycoris.client.module.modules.render.ClickGUI
import rbq.wtf.lycoris.client.module.modules.render.HUD
import rbq.wtf.lycoris.client.utils.Logger
import java.util.*

class ModuleManager : Listenable {

    val modules = TreeSet<Module> { module1, module2 -> module1.name.compareTo(module2.name) }
    private val moduleClassMap = hashMapOf<Class<*>, Module>()

    init {
        this.registerModules()
        Client.eventManager.registerListener(this)
    }

    /**
     * Register all modules
     */
    private fun registerModules() {
        Logger.info("Loading modules...", "ModuleManager")

        registerModules(
            AutoClicker::class.java,
            KeepSprint::class.java,
            HUD::class.java,
            ClickGUI::class.java,
            SelfDestruct::class.java
        )

//        registerModule(NoScoreboard)
//        registerModule(Fucker)
//        registerModule(ChestAura)
//        registerModule(AntiBot)

        Logger.info("Successful loaded ${modules.size} modules.", "ModuleManager")
    }

    /**
     * Register [module]
     */
    private fun registerModule(module: Module) {
        modules += module
        moduleClassMap[module.javaClass] = module

        generateCommand(module)
        Client.eventManager.registerListener(module)
    }

    /**
     * Register [moduleClass]
     */
    private fun registerModule(moduleClass: Class<out Module>) {
        try {
            registerModule(moduleClass.newInstance())
        } catch (e: Throwable) {
            Logger.error(
                "Failed to load module: ${moduleClass.name} (${e.javaClass.name}: ${e.message})",
                "ModuleManager"
            )
        }
    }

    /**
     * Register a list of modules
     */
    @SafeVarargs
    fun registerModules(vararg modules: Class<out Module>) {
        modules.forEach(this::registerModule)
    }

    /**
     * Unregister module
     */
    fun unregisterModule(module: Module) {
        modules.remove(module)
        moduleClassMap.remove(module::class.java)
        Client.eventManager.unregisterListener(module)
    }

    /**
     * Generate command for [module]
     */
    internal fun generateCommand(module: Module) {
        val values = module.values

        if (values.isEmpty())
            return

        //Client.commandManager.registerCommand(ModuleCommand(module, values))
    }

    /**
     * Legacy stuff
     *
     * TODO: Remove later when everything is translated to Kotlin
     */

    /**
     * Get module by [moduleClass]
     */
    fun getModuleByClass(moduleClass: Class<*>) = moduleClassMap[moduleClass]!!

    operator fun get(clazz: Class<*>) = getModuleByClass(clazz)

    /**
     * Get module by [moduleName]
     */
    fun getModuleByName(moduleName: String?) = modules.find { it.name.equals(moduleName, ignoreCase = true) }

    /**
     * Get modules by [ModuleCategory]
     */
    fun getModulesInType(moduleCategory: ModuleCategory) = modules.filter { it.category == moduleCategory }

    /**
     * Module related events
     */

    /**
     * Handle incoming key presses
     */
    @EventTarget
    private fun onKey(event: KeyEvent) = modules.filter { it.keyBind == event.key }.forEach { it.toggle() }

    override fun handleEvents() = true
}