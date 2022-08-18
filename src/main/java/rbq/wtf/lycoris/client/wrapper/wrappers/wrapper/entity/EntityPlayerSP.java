package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.MovementInput;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP", targetMap = MapEnum.VANILLA189)
public class EntityPlayerSP extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP", targetMap = MapEnum.VANILLA189)
    public static Class<?> EntityPlayerSPClass;
    @WrapField(mcpName = "sprintingTicksLeft", targetMap = MapEnum.VANILLA189)
    public static Field sprintingTicksLeft;
    @WrapField(mcpName = "movementInput", targetMap = MapEnum.VANILLA189)
    public static Field movementInput;
    @WrapMethod(mcpName = "onUpdateWalkingPlayer", targetMap = MapEnum.VANILLA189)
    public static Method onUpdateWalkingPlayer;
    @WrapMethod(mcpName = "setSprinting", targetMap = MapEnum.VANILLA189)
    public static Method setSprinting;
    @WrapMethod(mcpName = "isSneaking", targetMap = MapEnum.VANILLA189)
    public static Method isSneaking;

    public EntityPlayerSP(Object obj) {
        super(obj);
    }

    public EntityPlayer getEntityPlayerInstance() {
        return new EntityPlayer(getWrapObject());
    }

    public MovementInput getMovementInput() {
        return new MovementInput(getField(movementInput));
    }

    public void setSprinting(boolean sprint) {
        invoke(setSprinting, sprint);
    }

    public void setSprintingTicksLeft(int TicksLeft) {
        setField(sprintingTicksLeft, TicksLeft);
    }

    public boolean isSneaking() {
        return (boolean) invoke(isSneaking);
    }
}
