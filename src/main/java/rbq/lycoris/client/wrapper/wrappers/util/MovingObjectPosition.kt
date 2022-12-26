package rbq.lycoris.client.wrapper.wrappers.util

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.util.MovingObjectPosition", targetMap = MapEnum.VANILLA189)
class MovingObjectPosition(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "blockPos", targetMap = MapEnum.VANILLA189)
        lateinit var blockPos: Field
    }

    val blockPos: BlockPos
        get() = BlockPos(getField(Companion.blockPos)!!)
}