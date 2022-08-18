package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.FoodStats;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.player.EntityPlayer", targetMap = MapEnum.VANILLA189)
public class EntityPlayer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.entity.player.EntityPlayer", targetMap = MapEnum.VANILLA189)
    public static Class<?> EntityPlayerClass;

    @WrapField(mcpName = "sprintingTicksLeft", targetMap = MapEnum.VANILLA189)
    public static Field sprintingTicksLeft;
    @WrapField(mcpName = "foodStats", targetMap = MapEnum.VANILLA189)
    public static Field foodStats;

    @WrapMethod(mcpName = "onUpdateWalkingPlayer", targetMap = MapEnum.VANILLA189)
    public static Method onUpdateWalkingPlayer;
    @WrapMethod(mcpName = "setSprinting", targetMap = MapEnum.VANILLA189)
    public static Method setSprinting;

    public EntityPlayer(Object obj) {
        super(obj);
    }

    public boolean isMoving() {
        return new EntityLivingBase(getWrapObject()).isMoving();
    }

    public void setSprinting(boolean sprint) {
        invoke(setSprinting, sprint);
    }

    public void setSprintingTicksLeft(int TicksLeft){
        setField(sprintingTicksLeft, TicksLeft);
    }

    public FoodStats getFoodStats() {
        return new FoodStats(getField(foodStats));
    }
}
