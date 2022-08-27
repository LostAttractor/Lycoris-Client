package rbq.wtf.lycoris.client.wrapper.wrappers.potion

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = MapEnum.VANILLA189)
class Potion(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "blindness", targetMap = MapEnum.VANILLA189)
        lateinit var blindnessPotion: Field
        val blindness: Potion
            get() = Potion(ReflectUtil.getFieldStatic(blindnessPotion)!!)
    }
}