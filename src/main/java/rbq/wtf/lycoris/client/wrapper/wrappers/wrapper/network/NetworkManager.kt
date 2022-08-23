package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.network.NetworkManager", targetMap = MapEnum.VANILLA189)
class NetworkManager(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClass(mcpName = "net.minecraft.network.NetworkManager", targetMap = MapEnum.VANILLA189)
        lateinit var NetworkManagerClass: Class<*>

        @WrapMethod(mcpName = "sendPacket", targetMap = MapEnum.VANILLA189)
        lateinit var sendPacket: Method

        @WrapMethod(mcpName = "channelRead0", targetMap = MapEnum.VANILLA189)
        lateinit var channelRead0: Method
    }
}