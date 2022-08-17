package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.Vec3", targetMap = MapEnum.VANILLA189)
public class Vec3 extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.util.Vec3", targetMap = MapEnum.VANILLA189)
    public static Class Vec3Class;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {double.class, double.class, double.class})
    public static Constructor Vec3_DDD;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {Vec3i.class})
    public static Constructor Vec3_Vec3i;
    @WrapMethod(mcpName = "addVector", targetMap = MapEnum.VANILLA189)
    public static Method addVector;
    @WrapField(mcpName = "xCoord", targetMap = MapEnum.VANILLA189)
    public static Field xCoord;
    @WrapField(mcpName = "yCoord", targetMap = MapEnum.VANILLA189)
    public static Field yCoord;
    @WrapField(mcpName = "zCoord", targetMap = MapEnum.VANILLA189)
    public static Field zCoord;
    @WrapMethod(mcpName = "add", targetMap = MapEnum.VANILLA189)
    public static Method add;
    @WrapMethod(mcpName = "squareDistanceTo", targetMap = MapEnum.VANILLA189)
    public static Method squareDistanceTo;
    @WrapMethod(mcpName = "distanceTo", targetMap = MapEnum.VANILLA189)
    public static Method distanceTo;
    @WrapMethod(mcpName = "subtract", targetMap = MapEnum.VANILLA189, signature = "(Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/Vec3;")
    public static Method subtract_Vec;
    @WrapMethod(mcpName = "lengthVector", targetMap = MapEnum.VANILLA189)
    public static Method lengthVector;

    public Vec3(Object obj) {
        super(obj);
    }

    public Vec3(Vec3i vec3i) {
        this(ReflectUtil.construction(Vec3_Vec3i, vec3i.getWrapObject()));
    }

    public Vec3(double x, double y, double z) {
        this(ReflectUtil.construction(Vec3_DDD, x, y, z));
    }

    public Vec3 addVector(double x, double y, double z) {
        return new Vec3(invoke(addVector, x, y, z));
    }

    public double getXCoord() {
        return (double) getField(xCoord);
    }

    public double getYCoord() {
        return (double) getField(yCoord);
    }

    public double getZCoord() {
        return (double) getField(zCoord);
    }

    public Vec3 add(Vec3 vec3) {
        return new Vec3(invoke(add, vec3.getWrapObject()));
    }

    public double squareDistanceTo(Vec3 vec3) {
        return (double) invoke(squareDistanceTo, vec3.getWrapObject());
    }

    public double distanceTo(Vec3 vec3) {
        return (double) invoke(distanceTo, vec3.getWrapObject());
    }

    public Vec3 subtract(Vec3 vec3) {
        return new Vec3(invoke(subtract_Vec, vec3.getWrapObject()));
    }

    public double lengthVector() {
        return (double) invoke(lengthVector);
    }
}