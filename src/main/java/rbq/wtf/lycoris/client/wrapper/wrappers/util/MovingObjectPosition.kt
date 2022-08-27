package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
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