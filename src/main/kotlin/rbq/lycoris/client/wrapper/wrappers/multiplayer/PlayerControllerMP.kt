package rbq.lycoris.client.wrapper.wrappers.multiplayer

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP", targetMap = MapEnum.VANILLA189)
class PlayerControllerMP(obj: Any) : IWrapper(obj) {
    val curBlockDamageMP: Float
        get() = getField(Companion.curBlockDamageMP) as Float

    companion object {
        @WrapField(mcpName = "curBlockDamageMP", targetMap = MapEnum.VANILLA189)
        lateinit var curBlockDamageMP: Field
    }
}