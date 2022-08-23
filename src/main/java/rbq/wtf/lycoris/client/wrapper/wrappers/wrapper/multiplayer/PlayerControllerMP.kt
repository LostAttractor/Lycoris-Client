package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.multiplayer

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
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