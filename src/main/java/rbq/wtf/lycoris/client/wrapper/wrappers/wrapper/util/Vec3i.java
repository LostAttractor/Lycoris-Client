package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapConstructor;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


@WrapperClass(mcpName = "net.minecraft.util.Vec3i", targetMap = MapEnum.VANILLA189)
public class Vec3i extends IWrapper {
    @WrapField(mcpName = "x", targetMap = MapEnum.VANILLA189)
    public static Field x;
    @WrapField(mcpName = "y", targetMap = MapEnum.VANILLA189)
    public static Field y;
    @WrapField(mcpName = "z", targetMap = MapEnum.VANILLA189)
    public static Field z;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {double.class, double.class, double.class})
    public static Constructor Vec3i_DDD;
    @WrapMethod(mcpName = "distanceSq", targetMap = MapEnum.VANILLA1122, signature = "(DDD)D")
    public static Method distanceSq;

    public Vec3i(Object obj) {
        super(obj);
    }

    public Vec3i(double xIn, double yIn, double zIn) {
        this(ReflectUtil.construction(Vec3i_DDD, xIn, yIn, zIn));
    }

    public int getX() {
        return (int) ReflectUtil.getField(x, getWrapObject());
    }

    public int getY() {
        return (int) ReflectUtil.getField(y, getWrapObject());
    }

    public int getZ() {
        return (int) ReflectUtil.getField(z, getWrapObject());
    }

    public double distanceTo(double posX, double posY, double posZ) {
        return (double) invoke(distanceSq, posX, posY, posZ);
    }
}