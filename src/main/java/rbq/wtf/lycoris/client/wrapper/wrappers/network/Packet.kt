package rbq.wtf.lycoris.client.wrapper.wrappers.network

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper

@WrapperClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
class Packet(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
        lateinit var PacketClass: Class<*>
    }
}