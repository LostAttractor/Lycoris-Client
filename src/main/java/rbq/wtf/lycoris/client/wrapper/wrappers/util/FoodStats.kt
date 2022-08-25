package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.util.FoodStats", targetMap = MapEnum.VANILLA189)
class FoodStats(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "foodLevel", targetMap = MapEnum.VANILLA189)
        lateinit var foodLevel: Field
    }

    val foodLevel: Int
        get() = getField(Companion.foodLevel) as Int
}