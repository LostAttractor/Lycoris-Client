package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution",targetMap = MapEnum.MDK189)
public class ScaledResolution extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.ScaledResolution",targetMap = MapEnum.MDK189)
    public static Class ScaledResolution;
    @WrapField(mcpName = "scaledWidthD",targetMap = MapEnum.MDK189)
    public static Field scaledWidthD;
    @WrapField(mcpName = "scaledHeightD",targetMap = MapEnum.MDK189)
    public static Field scaledHeightD;
    @WrapField(mcpName = "scaledWidth",targetMap = MapEnum.MDK189)
    public static Field scaledWidth;
    @WrapField(mcpName = "scaledHeight",targetMap = MapEnum.MDK189)
    public static Field scaledHeight;
    @WrapField(mcpName = "scaleFactor",targetMap = MapEnum.MDK189)
    public static Field scaleFactor;

    public ScaledResolution(Object obj) {
        super(obj);
    }
    public ScaledResolution(Minecraft mc){
        super(ReflectUtil.construction(ScaledResolution,mc.getWrapObject()));
    }
    public int getScaledWidth()
    {
        return (int) ReflectUtil.getField(scaledWidth,getWrapObject());
    }

    public int getScaledHeight()
    {
        return (int) ReflectUtil.getField(scaledHeight,getWrapObject());
    }

    public double getScaledWidth_double()
    {
        return (double) ReflectUtil.getField(scaledWidthD,getWrapObject());
    }

    public double getScaledHeight_double()
    {
        return (double) ReflectUtil.getField(scaledHeightD,getWrapObject());
    }

    public int getScaleFactor()
    {
        return (int) ReflectUtil.getField(scaleFactor,getWrapObject());
    }
}
