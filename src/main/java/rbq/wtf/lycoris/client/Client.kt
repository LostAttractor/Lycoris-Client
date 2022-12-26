package rbq.wtf.lycoris.client

import rbq.wtf.lycoris.client.clickgui.ClickGUI
import rbq.wtf.lycoris.client.event.EventManager
import rbq.wtf.lycoris.client.manager.CommandManager
import rbq.wtf.lycoris.client.manager.ConfigManager
import rbq.wtf.lycoris.client.manager.ModuleManager
import rbq.wtf.lycoris.client.manager.RuntimeManager
import rbq.wtf.lycoris.client.transformer.TransformManager
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.utils.OnlineResource
import rbq.wtf.lycoris.client.wrapper.Wrapper
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

object Client {
    const val CLIENT_NAME = "Lycoris"
    const val GAME_VERSION = "1.8.9"
    const val enabledLog = true
    const val showDebugLevelLog = true
    const val developEnv = true
    var isVanilla = true
    var isStarting = false
    var isLoaded = false

    @JvmField
    var runPath: Path = Paths.get("").toAbsolutePath()
    val configPath: File = runPath.resolve("$CLIENT_NAME-$GAME_VERSION").toFile()

    @JvmField
    val runtimePath: File = configPath.resolve(".runtime")
    val mapsPath = runtimePath.resolve("maps")

    @JvmField
    val JVMTILoaderPath = runtimePath.resolve("Lycoris-Native-Loader.dll")
    lateinit var srgPath: File
    lateinit var srgMap: OnlineResource
    lateinit var JVMTILib: OnlineResource

    @JvmField
    var eventManager: EventManager = EventManager()
    lateinit var moduleManager: ModuleManager
    lateinit var configManager: ConfigManager
    lateinit var commandManager: CommandManager
    lateinit var clickGUI: ClickGUI

    fun start() {
        Logger.info("Start Initialize Client")
        Logger.info("Running in .minecraft Path: $runPath")
        if (isLoaded) {
            if (!TransformManager.transformed) {
                Logger.info("Re-Transforming Class...")
                TransformManager.doTransform()
                Logger.info("Client Initialized Successful")
            }
            return
        }
        isStarting = true
        RuntimeManager.init() // 加载/检测运行环境状态，补全运行时
        Wrapper.init() // Load Wrappers
        BridgeUtil.init()
        TransformManager.init() // Load Transformers
        configManager = ConfigManager()
        moduleManager = ModuleManager()
        commandManager = CommandManager()

        // Load configs
        configManager.loadConfigs(configManager.modulesConfig, configManager.valuesConfig)

        clickGUI = ClickGUI()

        isStarting = false
        isLoaded = true

        Logger.info("Client Initialized Successful")
    }
}