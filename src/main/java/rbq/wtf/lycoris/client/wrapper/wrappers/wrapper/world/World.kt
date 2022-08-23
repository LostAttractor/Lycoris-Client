package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.world

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block.state.IBlockState
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.BlockPos
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.world.World", targetMap = MapEnum.VANILLA189)
open class World(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "getBlockState", targetMap = MapEnum.VANILLA189)
        lateinit var getBlockState: Method
    }

    fun getBlockState(blockPos: BlockPos): IBlockState {
        return IBlockState(invoke(getBlockState, blockPos)!!)
    }
}