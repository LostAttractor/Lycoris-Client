package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.FoodStats", targetMap = MapEnum.VANILLA189)
public class FoodStats extends IWrapper {
    @WrapField(mcpName = "foodLevel", targetMap = MapEnum.VANILLA189)
    public static Field foodLevel;

    public FoodStats(Object obj) {
        super(obj);
    }

    public int getFoodLevel() {
        return (int) getField(foodLevel);
    }
}