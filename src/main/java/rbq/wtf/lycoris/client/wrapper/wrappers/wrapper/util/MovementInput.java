package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.HoverEvent;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.click.ClickEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.MovementInput", targetMap = MapEnum.VANILLA189)
public class MovementInput extends IWrapper {
    @WrapField(mcpName = "moveStrafe", targetMap = MapEnum.VANILLA189)
    public static Field moveStrafe;
    @WrapField(mcpName = "moveForward", targetMap = MapEnum.VANILLA189)
    public static Field moveForward;
    @WrapField(mcpName = "jump", targetMap = MapEnum.VANILLA189)
    public static Field jump;
    @WrapField(mcpName = "sneak", targetMap = MapEnum.VANILLA189)
    public static Field sneak;

    public MovementInput(Object obj) {
        super(obj);
    }

    public float getMoveForward() {
        return (float) getField(moveForward);
    }

    public float getMoveStrafe() {
        return (float) getField(moveStrafe);
    }

    public boolean getJump() {
        return (boolean) getField(jump);
    }

    public boolean getSneak() {
        return (boolean) getField(sneak);
    }
}