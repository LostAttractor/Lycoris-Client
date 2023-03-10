package rbq.lycoris.client.wrapper.wrappers.init

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.utils.ReflectUtil
import rbq.lycoris.client.wrapper.wrappers.block.Block
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.init.Blocks", targetMap = MapEnum.VANILLA189)
class Blocks(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "air", targetMap = MapEnum.VANILLA189)
        lateinit var airBlock: Field

        val air: Block
            get() = Block(ReflectUtil.getFieldStatic(airBlock)!!)
    }
}