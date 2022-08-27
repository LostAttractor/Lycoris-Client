package rbq.wtf.lycoris.client.wrapper.wrappers.entity

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.potion.Potion
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = MapEnum.VANILLA189)
open class EntityLivingBase(obj: Any) : Entity(obj) {
    val moveForward: Float
        get() = getField(Companion.moveForward) as Float
    val moveStrafing: Float
        get() = getField(Companion.moveStrafing) as Float

    fun isPotionActive(p_isPotionActive_1_: Potion): Boolean {
        return invoke(isPotionActive, p_isPotionActive_1_.wrapObject) as Boolean
    }

    companion object {
        @WrapField(mcpName = "moveStrafing", targetMap = MapEnum.VANILLA189)
        lateinit var moveStrafing: Field

        @WrapField(mcpName = "moveForward", targetMap = MapEnum.VANILLA189)
        lateinit var moveForward: Field

        @WrapMethod(mcpName = "isPotionActive", targetMap = MapEnum.VANILLA189)
        lateinit var isPotionActive: Method
    }
}