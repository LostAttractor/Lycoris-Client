package rbq.wtf.lycoris.client.wrapper.wrappers.block

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.block.Block", targetMap = MapEnum.VANILLA189)
class Block(obj: Any) : IWrapper(obj)