package rbq.wtf.lycoris.client.wrapper.wrappers.entity

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.*
import rbq.wtf.lycoris.client.wrapper.wrappers.util.MovementInput
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP", targetMap = MapEnum.VANILLA189)
class EntityPlayerSP(obj: Any?) : EntityPlayer(obj) {
    val movementInput: MovementInput
        get() = MovementInput(getField(Companion.movementInput)!!)

    fun setSprinting(sprint: Boolean) {
        invoke(setSprinting, sprint)
    }

    fun setSprintingTicksLeft(TicksLeft: Int) {
        setField(sprintingTicksLeft, TicksLeft)
    }

    override val isSneaking: Boolean
        get() = invoke(Companion.isSneaking) as Boolean

    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapField(mcpName = "sprintingTicksLeft", targetMap = MapEnum.VANILLA189)
        lateinit var sprintingTicksLeft: Field

        @WrapField(mcpName = "movementInput", targetMap = MapEnum.VANILLA189)
        lateinit var movementInput: Field

        @WrapMethod(mcpName = "onUpdateWalkingPlayer", targetMap = MapEnum.VANILLA189)
        lateinit var onUpdateWalkingPlayer: Method

        @WrapMethod(mcpName = "onLivingUpdate", targetMap = MapEnum.VANILLA189)
        lateinit var onLivingUpdate: Method

        @WrapMethod(mcpName = "setSprinting", targetMap = MapEnum.VANILLA189)
        lateinit var setSprinting: Method

        @WrapMethod(mcpName = "isSneaking", targetMap = MapEnum.VANILLA189)
        lateinit var isSneaking: Method
    }
}