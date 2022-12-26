package rbq.lycoris.client.wrapper.wrappers.entity

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.entity.player.PlayerCapabilities", targetMap = MapEnum.VANILLA189)
class PlayerCapabilities(obj: Any) : IWrapper(obj) {
    val isAllowFlying: Boolean
        get() = getField(allowFlying) as Boolean

    companion object {
        @WrapField(mcpName = "allowFlying", targetMap = MapEnum.VANILLA189)
        lateinit var allowFlying: Field
    }
}