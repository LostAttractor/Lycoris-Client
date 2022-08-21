package rbq.wtf.lycoris.client

import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl
import rbq.wtf.lycoris.client.event.EventManager
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI
import rbq.wtf.lycoris.client.manager.CommandManager
import rbq.wtf.lycoris.client.manager.ConfigManager
import rbq.wtf.lycoris.client.manager.KeyBindManager
import rbq.wtf.lycoris.client.manager.ModuleManager
import rbq.wtf.lycoris.client.transformer.TransformManager
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.wrapper.Wrapper
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft
import java.nio.file.Path
import java.nio.file.Paths

object Client {
    const val enabledLog = true
    const val showDebugLevelLog = true
    const val developEnv = true
    var isVanilla = true
    var game_version = "1.8.9"
    @JvmField
    var runPath: Path = Paths.get("").toAbsolutePath()

    @JvmField
    var eventManager: EventManager = EventManager()
    lateinit var moduleManager: ModuleManager
    lateinit var configManager: ConfigManager
    lateinit var commandManager: CommandManager
    lateinit var keyBindManager: KeyBindManager
    lateinit var clickGUI: ClickGUI

    fun start() {
        Logger.info("Start Initialize Client")
        Wrapper.init() // Load Wrappers
        BridgeUtil.init()
        InstrumentationImpl.init() // Load Native
        TransformManager.init() // Load Transformers
        moduleManager = ModuleManager()
        configManager = ConfigManager()
        commandManager = CommandManager()
        keyBindManager = KeyBindManager()
        clickGUI = ClickGUI()

        eventManager.registerListener(keyBindManager)

        Logger.info("Client Initialized Successful")
    }
}