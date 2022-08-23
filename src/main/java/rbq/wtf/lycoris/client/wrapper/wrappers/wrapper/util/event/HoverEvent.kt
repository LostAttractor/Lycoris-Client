package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper

@WrapperClass(mcpName = "net.minecraft.event.HoverEvent", targetMap = MapEnum.VANILLA189)
class HoverEvent(obj: Any) : IWrapper(obj)