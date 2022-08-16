package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiIngame",targetMap = MapEnum.Srg1_8_9)
public class GuiIngame extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiIngame",targetMap = MapEnum.Srg1_8_9)
    public static Class<?> GuiIngameClass;
    public GuiIngame(Object obj) {
        super(obj);
    }
    @WrapMethod(mcpName = "renderTooltip",targetMap = MapEnum.Srg1_8_9)
    public static Method renderTooltip;
}
