package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.click

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.EnumWrapper

@WrapperClass(mcpName = "net.minecraft.event.ClickEvent\$Action", targetMap = MapEnum.VANILLA189)
class ClickEventAction(obj: Any) : EnumWrapper(obj) {
    companion object {
        @WrapEnum(mcpName = "OPEN_URL", targetMap = MapEnum.VANILLA189)
        lateinit var OPEN_URL: Enum<*>

        @WrapEnum(mcpName = "OPEN_FILE", targetMap = MapEnum.VANILLA189)
        lateinit var OPEN_FILE: Enum<*>

        @WrapEnum(mcpName = "RUN_COMMAND", targetMap = MapEnum.VANILLA189)
        lateinit var RUN_COMMAND: Enum<*>

        @WrapEnum(mcpName = "TWITCH_USER_INFO", targetMap = MapEnum.VANILLA189)
        lateinit var TWITCH_USER_INFO: Enum<*>

        @WrapEnum(mcpName = "SUGGEST_COMMAND", targetMap = MapEnum.VANILLA189)
        lateinit var SUGGEST_COMMAND: Enum<*>

        @WrapEnum(mcpName = "CHANGE_PAGE", targetMap = MapEnum.VANILLA189)
        lateinit var CHANGE_PAGE: Enum<*>
    }
}