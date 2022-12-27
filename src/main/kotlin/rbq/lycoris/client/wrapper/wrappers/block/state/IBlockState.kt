package rbq.lycoris.client.wrapper.wrappers.block.state

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.wrappers.block.Block
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.block.state.IBlockState", targetMap = MapEnum.VANILLA189)
class IBlockState(obj: Any) : IWrapper(obj) {
    val block: Block
        get() = Block(invoke(getBlock)!!)

    companion object {
        @WrapMethod(mcpName = "getBlock", targetMap = MapEnum.VANILLA189)
        lateinit var getBlock: Method
    }
}