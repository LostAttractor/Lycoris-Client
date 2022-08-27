package rbq.wtf.lycoris.client.wrapper.wrappers.network

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.network.NetworkManager", targetMap = MapEnum.VANILLA189)
class NetworkManager(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>

        @WrapMethod(mcpName = "sendPacket", targetMap = MapEnum.VANILLA189)
        lateinit var sendPacket: Method

        @WrapMethod(mcpName = "channelRead0", targetMap = MapEnum.VANILLA189)
        lateinit var channelRead0: Method
    }
}