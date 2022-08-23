package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
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