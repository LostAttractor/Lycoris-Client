package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = MapEnum.VANILLA189)
public class EntityLivingBase extends IWrapper {

    @WrapClass(mcpName = "net.minecraft.entity.EntityLivingBase", targetMap = MapEnum.VANILLA189)
    public static Class<?> EntityLivingBaseClass;

    @WrapField(mcpName = "moveStrafing", targetMap = MapEnum.VANILLA189)
    public static Field moveStrafing;
    @WrapField(mcpName = "moveForward", targetMap = MapEnum.VANILLA189)
    public static Field moveForward;
    public EntityLivingBase(Object obj) {
        super(obj);
    }

    public boolean isMoving() {
        return ((float) getField(moveForward)) != 0.0f || ((float) getField(moveStrafing)) != 0.0f;
    }
}
