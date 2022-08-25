package rbq.wtf.lycoris.client.wrapper.wrappers.entity

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.util.FoodStats
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.entity.player.EntityPlayer", targetMap = MapEnum.VANILLA189)
open class EntityPlayer(obj: Any?) : EntityLivingBase(
    obj!!
) {
    val capabilities: PlayerCapabilities
        get() = PlayerCapabilities(getField(Companion.capabilities)!!)
    val foodStats: FoodStats
        get() = FoodStats(getField(Companion.foodStats)!!)
    val isUsingItem: Boolean
        get() = invoke(Companion.isUsingItem) as Boolean

    companion object {
        @WrapClass(mcpName = "net.minecraft.entity.player.EntityPlayer", targetMap = MapEnum.VANILLA189)
        lateinit var EntityPlayerClass: Class<*>

        @WrapField(mcpName = "foodStats", targetMap = MapEnum.VANILLA189)
        lateinit var foodStats: Field

        @WrapField(mcpName = "capabilities", targetMap = MapEnum.VANILLA189)
        lateinit var capabilities: Field

        @WrapMethod(mcpName = "isUsingItem", targetMap = MapEnum.VANILLA189)
        lateinit var isUsingItem: Method
    }
}