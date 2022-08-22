package rbq.wtf.lycoris.client.manager

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.config.FileConfig
import rbq.wtf.lycoris.client.config.configs.ModulesConfig
import rbq.wtf.lycoris.client.config.configs.ValuesConfig
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.utils.MinecraftInstance
import java.io.File
import java.lang.reflect.Field

class ConfigManager : MinecraftInstance() {
    val dir: File = Client.runPath.resolve(Client.CLIENT_NAME + "-" + Client.GAME_VERSION).toFile()
    val fontsDir = File(dir, "fonts")
    val settingsDir = File(dir, "settings")
    val modulesConfig: FileConfig = ModulesConfig(File(dir, "modules.json"))
    val valuesConfig: FileConfig = ValuesConfig(File(dir, "values.json"))

    //val clickGuiConfig: FileConfig = ClickGuiConfig(File(dir, "clickgui.json"))
    //val accountsConfig: AccountsConfig = AccountsConfig(File(dir, "accounts.json"))
    //val friendsConfig: FriendsConfig = FriendsConfig(File(dir, "friends.json"))
    //val xrayConfig: FileConfig = XRayConfig(File(dir, "xray-blocks.json"))
    //val hudConfig: FileConfig = HudConfig(File(dir, "hud.json"))
    //val shortcutsConfig: FileConfig = ShortcutsConfig(File(dir, "shortcuts.json"))
    //val backgroundFile = File(dir, "userbackground.png")
    var firstStart = false

    /**
     * Constructor of file manager
     * Setup everything important
     */
    init {
        setupFolder()
        //loadBackground()
    }

    /**
     * Setup folder
     */
    fun setupFolder() {
        if (!dir.exists()) {
            dir.mkdir()
            firstStart = true
        }
        if (!fontsDir.exists()) fontsDir.mkdir()
        if (!settingsDir.exists()) settingsDir.mkdir()
    }

    /**
     * Load all configs in file manager
     */
    fun loadAllConfigs() {
        for (field: Field in javaClass.declaredFields) {
            if (field.type == FileConfig::class.java) {
                try {
                    if (!field.isAccessible) field.isAccessible = true
                    val fileConfig = field[this] as FileConfig
                    loadConfig(fileConfig)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                    Logger.error("Failed to load config file of field " + field.name + ".", "ConfigManager")
                }
            }
        }
    }

    /**
     * Load a list of configs
     *
     * @param configs list
     */
    fun loadConfigs(vararg configs: FileConfig) {
        for (fileConfig: FileConfig in configs) loadConfig(fileConfig)
    }

    /**
     * Load one config
     *
     * @param config to load
     */
    fun loadConfig(config: FileConfig) {
        if (!config.hasConfig()) {
            Logger.info("Skipped loading config: " + config.file.name + ".", "ConfigManager")
            saveConfig(config, true)
            return
        }
        try {
            config.loadConfig()
            Logger.info("Loaded config: " + config.file.name + ".", "ConfigManager")
        } catch (t: Throwable) {
            t.printStackTrace()
            Logger.error("Failed to load config file: " + config.file.name + ".", "ConfigManager")
        }
    }

    /**
     * Save all configs in file manager
     */
    fun saveAllConfigs() {
        for (field: Field in javaClass.declaredFields) {
            if (field.type == FileConfig::class.java) {
                try {
                    if (!field.isAccessible) field.isAccessible = true
                    val fileConfig = field[this] as FileConfig
                    saveConfig(fileConfig)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                    Logger.error("Failed to save config file of field " + field.name + ".", "ConfigManager")
                }
            }
        }
    }

    /**
     * Save a list of configs
     *
     * @param configs list
     */
    fun saveConfigs(vararg configs: FileConfig) {
        for (fileConfig: FileConfig in configs) saveConfig(fileConfig)
    }

    /**
     * Save one config
     *
     * @param config to save
     */
    fun saveConfig(config: FileConfig) {
        saveConfig(config, false)
    }

    /**
     * Save one config
     *
     * @param config         to save
     * @param ignoreStarting check starting
     */
    private fun saveConfig(config: FileConfig, ignoreStarting: Boolean) {
        if (!ignoreStarting && Client.isStarting) return
        try {
            if (!config.hasConfig()) config.createConfig()
            config.saveConfig()
            Logger.info("Saved config: ${config.file.name}.", "ConfigManger")
        } catch (t: Throwable) {
            t.printStackTrace()
            Logger.error("[FileManager] Failed to save config file: ${config.file.name}.", "ConfigManager")
        }
    }

    /**
     * Load background for background
     */
//    fun loadBackground() {
//        if (backgroundFile.exists()) {
//            try {
//                val bufferedImage = ImageIO.read(FileInputStream(backgroundFile)) ?: return
//                LiquidBounce.INSTANCE.setBackground(ResourceLocation(LiquidBounce.CLIENT_NAME.toLowerCase() + "/background.png"))
//                mc.getTextureManager().loadTexture(LiquidBounce.INSTANCE.getBackground(), DynamicTexture(bufferedImage))
//                ClientUtils.getLogger().info("[FileManager] Loaded background.")
//            } catch (e: Exception) {
//                ClientUtils.getLogger().error("[FileManager] Failed to load background.", e)
//            }
//        }
//    }

    companion object {
        val PRETTY_GSON: Gson = GsonBuilder().setPrettyPrinting().create()
    }
}
