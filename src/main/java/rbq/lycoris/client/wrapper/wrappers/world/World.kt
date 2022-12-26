package rbq.lycoris.client.wrapper.wrappers.world

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.wrappers.block.state.IBlockState
import rbq.lycoris.client.wrapper.wrappers.util.BlockPos
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