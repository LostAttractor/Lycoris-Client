package rbq.lycoris.client.wrapper.wrappers.network

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
class Packet(obj: Any) : IWrapper(obj)