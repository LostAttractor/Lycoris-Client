package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.entity.player.PlayerCapabilities", targetMap = MapEnum.VANILLA189)
public class PlayerCapabilities extends IWrapper {
    @WrapField(mcpName = "allowFlying", targetMap = MapEnum.VANILLA189)
    public static Field allowFlying;

    public PlayerCapabilities(Object obj) {
        super(obj);
    }

    public boolean isAllowFlying() {
        return (boolean) getField(allowFlying);
    }
}
