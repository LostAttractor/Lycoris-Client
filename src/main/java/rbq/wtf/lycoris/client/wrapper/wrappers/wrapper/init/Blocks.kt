package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.init

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block.Block
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.init.Blocks", targetMap = MapEnum.VANILLA189)
class Blocks(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "air", targetMap = MapEnum.VANILLA189)
        lateinit var airBlock: Field

        val air: Block
            get() = Block(ReflectUtil.getFieldStatic(airBlock))
    }
}