package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.util.MovementInput", targetMap = MapEnum.VANILLA189)
class MovementInput(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapField(mcpName = "moveStrafe", targetMap = MapEnum.VANILLA189)
        lateinit var moveStrafe: Field

        @WrapField(mcpName = "moveForward", targetMap = MapEnum.VANILLA189)
        lateinit var moveForward: Field

        @WrapField(mcpName = "jump", targetMap = MapEnum.VANILLA189)
        lateinit var jump: Field

        @WrapField(mcpName = "sneak", targetMap = MapEnum.VANILLA189)
        lateinit var sneak: Field
    }

    val moveForward: Float
        get() = getField(Companion.moveForward) as Float
    val moveStrafe: Float
        get() = getField(Companion.moveStrafe) as Float
    val jump: Boolean
        get() = getField(Companion.jump) as Boolean
    val sneak: Boolean
        get() = getField(Companion.sneak) as Boolean
}