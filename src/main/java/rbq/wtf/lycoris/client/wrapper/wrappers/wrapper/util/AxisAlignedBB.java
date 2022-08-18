package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.AxisAlignedBB", targetMap = MapEnum.VANILLA189)
public class AxisAlignedBB extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.util.AxisAlignedBB", targetMap = MapEnum.VANILLA189)
    public static Class AxisAlignedBBClass;
    @WrapField(mcpName = "minX", targetMap = MapEnum.VANILLA189)
    public static Field minX;
    @WrapField(mcpName = "minY", targetMap = MapEnum.VANILLA189)
    public static Field minY;
    @WrapField(mcpName = "minZ", targetMap = MapEnum.VANILLA189)
    public static Field minZ;
    @WrapField(mcpName = "maxX", targetMap = MapEnum.VANILLA189)
    public static Field maxX;
    @WrapField(mcpName = "maxY", targetMap = MapEnum.VANILLA189)
    public static Field maxY;
    @WrapField(mcpName = "maxZ", targetMap = MapEnum.VANILLA189)
    public static Field maxZ;
    @WrapMethod(mcpName = "offset", targetMap = MapEnum.VANILLA189)
    public static Method offset_3d;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {double.class, double.class, double.class, double.class, double.class, double.class})
    public static Constructor AxisAlignedBB_Constructor_DDDDDD;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {BlockPos.class, BlockPos.class})
    public static Constructor AxisAlignedBB_Constructor_BlockPos;
    @WrapMethod(mcpName = "expand", targetMap = MapEnum.VANILLA189)
    public static Method expand;
    @WrapMethod(mcpName = "addCoord", targetMap = MapEnum.VANILLA189)
    public static Method addCoord;
    @WrapMethod(mcpName = "calculateIntercept", targetMap = MapEnum.VANILLA189)
    public static Method calculateIntercept;
    @WrapMethod(mcpName = "isVecInside", targetMap = MapEnum.VANILLA189)
    public static Method isVecInside;

    public AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(ReflectUtil.construction(AxisAlignedBB_Constructor_DDDDDD, x1, y1, z1, x2, y2, z2));
    }

    public AxisAlignedBB(BlockPos pos1, BlockPos pos2) {
        this(ReflectUtil.construction(AxisAlignedBB_Constructor_BlockPos, pos1.getWrapObject(), pos2.getWrapObject()));
    }

    public AxisAlignedBB(Object obj) {
        super(obj);
    }

    public static AxisAlignedBB fromBounds(double x1, double y1, double z1, double x2, double y2, double z2) {
        double d0 = Math.min(x1, x2);
        double d1 = Math.min(y1, y2);
        double d2 = Math.min(z1, z2);
        double d3 = Math.max(x1, x2);
        double d4 = Math.max(y1, y2);
        double d5 = Math.max(z1, z2);
        return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
    }

    public double getMinZ() {
        return (double) getField(minZ);
    }

    public double getMinY() {
        return (double) getField(minY);
    }

    public double getMaxZ() {
        return (double) getField(maxZ);
    }

    public double getMaxY() {
        return (double) getField(maxY);
    }

    public double getMaxX() {
        return (double) getField(maxX);
    }

    public double getMinX() {
        return (double) getField(minX);
    }

    public AxisAlignedBB offset(double x, double y, double z) {
        return new AxisAlignedBB(invoke(offset_3d, x, y, z));
    }

    public AxisAlignedBB expand(double x, double y, double z) {
        return new AxisAlignedBB(invoke(expand, x, y, z));
    }

    public AxisAlignedBB addCoord(double x, double y, double z) {
        return new AxisAlignedBB(invoke(addCoord, x, y, z));
    }

    public boolean isVecInside(Vec3 vec3) {
        return (boolean) invoke(isVecInside, vec3.getWrapObject());
    }
}