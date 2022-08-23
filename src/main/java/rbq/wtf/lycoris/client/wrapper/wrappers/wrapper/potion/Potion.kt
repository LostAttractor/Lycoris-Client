package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.potion

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = MapEnum.VANILLA189)
class Potion(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "blindness", targetMap = MapEnum.VANILLA189)
        lateinit var blindness: Field

        @JvmStatic
        fun getBlindness(): Potion {
            return Potion(ReflectUtil.getFieldStatic(blindness)!!)
        }
    }
}