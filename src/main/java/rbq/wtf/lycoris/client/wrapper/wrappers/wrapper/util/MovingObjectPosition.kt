package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
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