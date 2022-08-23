package rbq.wtf.lycoris.client

import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl
import rbq.wtf.lycoris.client.event.EventManager
import rbq.wtf.lycoris.client.clickgui.ClickGUI
import rbq.wtf.lycoris.client.manager.CommandManager
import rbq.wtf.lycoris.client.manager.ConfigManager
import rbq.wtf.lycoris.client.manager.ModuleManager
import rbq.wtf.lycoris.client.transformer.TransformManager
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.wrapper.Wrapper
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil
import java.nio.file.Path
import java.nio.file.Paths

object Client {
    const val CLIENT_NAME = "Lycoris"
    const val GAME_VERSION = "1.8.9"
    const val enabledLog = true
    const val showDebugLevelLog = true
    const val developEnv = false
    var isVanilla = true
    var isStarting = false

    @JvmField
    var runPath: Path = Paths.get("").toAbsolutePath()

    @JvmField
    var eventManager: EventManager = EventManager()
    lateinit var moduleManager: ModuleManager
    lateinit var configManager: ConfigManager
    lateinit var commandManager: CommandManager
    lateinit var clickGUI: ClickGUI

    fun start() {
        isStarting = true
        Logger.info("Start Initialize Client")
        Wrapper.init() // Load Wrappers
        BridgeUtil.init()
        InstrumentationImpl.init() // Load Native
        TransformManager.init() // Load Transformers
        configManager = ConfigManager()
        moduleManager = ModuleManager()
        commandManager = CommandManager()

        // Load configs
        configManager.loadConfigs(configManager.modulesConfig, configManager.valuesConfig)

        clickGUI = ClickGUI()

        isStarting = false

        Logger.info("Client Initialized Successful")
    }
}