package rbq.lycoris.client.wrapper.wrappers.block

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.block.Block", targetMap = MapEnum.VANILLA189)
class Block(obj: Any) : IWrapper(obj)