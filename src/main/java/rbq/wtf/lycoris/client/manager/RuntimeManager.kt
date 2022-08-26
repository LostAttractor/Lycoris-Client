package rbq.wtf.lycoris.client.manager

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.utils.OnlineResource
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.Wrapper

object RuntimeManager {
    var firstStart = false

    fun init() {
        setupFolder()
        setupWrapper()
        setupJVMTILoader()
    }

    private fun setupWrapper() {
        Wrapper.MapEnv = MapEnum.VANILLA189
        Wrapper.useMapObf = !Client.developEnv //是否使用混淆后的名称，在MDK环境下需设为false
        //TODO 自动检查游戏版本
        Client.srgPath = Client.mapsPath.resolve("${Wrapper.MapEnv}.srg")
        Client.srgMap = OnlineResource(Client.srgPath)
    }

    private fun setupJVMTILoader() {
        Client.JVMTILib = OnlineResource(Client.JVMTILoaderPath)
    }

    private fun setupFolder() {
        if (!Client.configPath.exists()) {
            Client.configPath.mkdir()
            firstStart = true
        }
        if (!Client.runtimePath.exists()) Client.runtimePath.mkdir()
        if (!Client.mapsPath.exists()) Client.mapsPath.mkdir()
    }
}