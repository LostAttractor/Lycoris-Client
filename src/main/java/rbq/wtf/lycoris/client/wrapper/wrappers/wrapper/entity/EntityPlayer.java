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
    @WrapField(mcpName = "foodStats", targetMap = MapEnum.VANILLA189)
    public static Field foodStats;
    @WrapField(mcpName = "capabilities", targetMap = MapEnum.VANILLA189)
    public static Field capabilities;

    @WrapMethod(mcpName = "isUsingItem", targetMap = MapEnum.VANILLA189)
    public static Method isUsingItem;

    public EntityPlayer(Object obj) {
        super(obj);
    }

    public EntityLivingBase getEntityLivingBaseInstance() {
        return new EntityLivingBase(getWrapObject());
    }

    public PlayerCapabilities getCapabilities() {
        return new PlayerCapabilities(getField(capabilities));
    }

    public FoodStats getFoodStats() {
        return new FoodStats(getField(foodStats));
    }

    public boolean isUsingItem() {
        return (Boolean) invoke(isUsingItem);
    }
}
