package rbq.lycoris.client

import rbq.lycoris.client.clickgui.ClickGUI
import rbq.lycoris.client.event.EventManager
import rbq.lycoris.client.manager.CommandManager
import rbq.lycoris.client.manager.ConfigManager
import rbq.lycoris.client.manager.ModuleManager
import rbq.lycoris.client.manager.RuntimeManager
import rbq.lycoris.client.transformer.TransformManager
import rbq.lycoris.client.utils.Logger
import rbq.lycoris.client.utils.OnlineResource
import rbq.lycoris.client.wrapper.Wrapper
import rbq.lycoris.client.wrapper.bridge.BridgeUtil
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

    val runPath: Path = Paths.get("").toAbsolutePath()
    val configPath: File = runPath.resolve("$CLIENT_NAME-$GAME_VERSION").toFile()
    val runtimePath: File = configPath.resolve(".runtime")
    val mapsPath = runtimePath.resolve("maps")
    val JVMTILoaderPath = runtimePath.resolve("Lycoris-Native-Loader.dll")

    lateinit var srgPath: File

    lateinit var srgMap: OnlineResource
    lateinit var JVMTILib: OnlineResource

//    @JvmField
    lateinit var eventManager: EventManager
    lateinit var moduleManager: ModuleManager
    lateinit var configManager: ConfigManager
    lateinit var commandManager: CommandManager
    lateinit var clickGUI: ClickGUI

    fun start() {
        Logger.info("Start Initialize Client")
        Logger.info("Running in .minecraft Path: $runPath")

        if (isLoaded) { // Re-Inject
            if (!TransformManager.transformed) { //Self Destructed
                TransformManager.reTransform()
            }
            return
        }

        isStarting = true

        RuntimeManager.init() // 加载/检测运行环境状态，补全运行时 (Native)
        Wrapper.init() // Load Wrappers
        BridgeUtil.init() // Load Margele Bridge

        eventManager = EventManager()
        configManager = ConfigManager()
        moduleManager = ModuleManager()
        commandManager = CommandManager()
        clickGUI = ClickGUI()

        // Load configs
        configManager.loadConfigs(configManager.modulesConfig, configManager.valuesConfig)

        TransformManager.init() // Load Transformers

        isStarting = false
        isLoaded = true

        Logger.info("Client Initialized Successful")
    }
}