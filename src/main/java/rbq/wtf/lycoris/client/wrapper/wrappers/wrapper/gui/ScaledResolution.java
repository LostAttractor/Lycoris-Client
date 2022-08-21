package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution", targetMap = MapEnum.VANILLA189)
public class ScaledResolution extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.ScaledResolution", targetMap = MapEnum.VANILLA189)
    public static Class<?> ScaledResolution;
    @WrapField(mcpName = "scaledWidthD", targetMap = MapEnum.VANILLA189)
    public static Field scaledWidthD;
    @WrapField(mcpName = "scaledHeightD", targetMap = MapEnum.VANILLA189)
    public static Field scaledHeightD;
    @WrapField(mcpName = "scaledWidth", targetMap = MapEnum.VANILLA189)
    public static Field scaledWidth;
    @WrapField(mcpName = "scaledHeight", targetMap = MapEnum.VANILLA189)
    public static Field scaledHeight;
    @WrapField(mcpName = "scaleFactor", targetMap = MapEnum.VANILLA189)
    public static Field scaleFactor;

    public ScaledResolution(Object obj) {
        super(obj);
    }

    public ScaledResolution(Minecraft mc) {
        super(ReflectUtil.construction(ScaledResolution, mc.getWrapObject()));
    }

    public int getScaledWidth() {
        return (int) getField(scaledWidth);
    }

    public int getScaledHeight() {
        return (int) getField(scaledHeight);
    }

    public double getScaledWidth_double() {
        return (double) getField(scaledWidthD);
    }

    public double getScaledHeight_double() {
        return (double) getField(scaledHeightD);
    }

    public int getScaleFactor() {
        return (int) getField(scaleFactor);
    }
}
