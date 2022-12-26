package rbq.lycoris.client.wrapper.wrappers.render

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer", targetMap = MapEnum.VANILLA189)
class FontRenderer(obj: Any) : IWrapper(obj)