package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.click;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.event.ClickEvent", targetMap = MapEnum.VANILLA189)
public class ClickEvent extends IWrapper {
    @WrapField(mcpName = "action", targetMap = MapEnum.VANILLA189)
    public static Field action;
    @WrapField(mcpName = "value", targetMap = MapEnum.VANILLA189)
    public static Field value;

    public ClickEvent(Object obj) {
        super(obj);
    }

    public Enum getAction() {
        return (Enum) getField(action);
    }

    public String getValue() {
        return (String) getField(value);
    }
}