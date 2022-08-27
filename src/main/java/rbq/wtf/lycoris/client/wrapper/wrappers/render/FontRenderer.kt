package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer", targetMap = MapEnum.VANILLA189)
class FontRenderer(obj: Any) : IWrapper(obj)