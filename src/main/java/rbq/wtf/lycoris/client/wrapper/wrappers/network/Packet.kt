package rbq.wtf.lycoris.client.wrapper.wrappers.network

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
class Packet(obj: Any) : IWrapper(obj)