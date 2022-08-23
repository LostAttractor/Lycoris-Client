package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper

@WrapperClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
class Packet(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
        lateinit var PacketClass: Class<*>
    }
}