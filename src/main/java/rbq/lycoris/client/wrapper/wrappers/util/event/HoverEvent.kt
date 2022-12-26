package rbq.lycoris.client.wrapper.wrappers.util.event

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.event.HoverEvent", targetMap = MapEnum.VANILLA189)
class HoverEvent(obj: Any) : IWrapper(obj)