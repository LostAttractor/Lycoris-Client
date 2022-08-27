package rbq.wtf.lycoris.client.wrapper.wrappers.block.state

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.block.Block
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