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