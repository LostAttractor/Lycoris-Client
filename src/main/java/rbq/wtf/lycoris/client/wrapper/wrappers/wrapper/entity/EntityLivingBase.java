package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.potion.Potion;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = MapEnum.VANILLA189)
public class EntityLivingBase extends Entity {

    @WrapClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = MapEnum.VANILLA189)
    public static Class<?> EntityLivingBaseClass;
    @WrapField(mcpName = "moveStrafing", targetMap = MapEnum.VANILLA189)
    public static Field moveStrafing;
    @WrapField(mcpName = "moveForward", targetMap = MapEnum.VANILLA189)
    public static Field moveForward;
    @WrapMethod(mcpName = "isPotionActive", targetMap = MapEnum.VANILLA189)
    public static Method isPotionActive;

    public EntityLivingBase(Object obj) {
        super(obj);
    }

    public float getMoveForward() {
        return (float) getField(moveForward);
    }

    public float getMoveStrafing() {
        return (float) getField(moveStrafing);
    }

    public boolean isPotionActive(Potion p_isPotionActive_1_) {
        return (boolean) invoke(isPotionActive, p_isPotionActive_1_.getWrapObject());
    }
}
