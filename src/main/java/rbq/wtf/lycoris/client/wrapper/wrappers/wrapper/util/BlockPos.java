package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapConstructor;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.BlockPos", targetMap = MapEnum.VANILLA189)
public class BlockPos extends Vec3i {
    @WrapClass(mcpName = "net.minecraft.util.BlockPos", targetMap = MapEnum.VANILLA189)
    public static Class BlockPosClass;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {double.class, double.class, double.class})
    public static Constructor BlockPos_DDD;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {Entity.class})
    public static Constructor BlockPos_Entity;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {Vec3.class})
    public static Constructor BlockPos_Vec;
    @WrapMethod(mcpName = "offset", targetMap = MapEnum.VANILLA189, signature = "(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;")
    public static Method offset;
    @WrapMethod(mcpName = "offset", targetMap = MapEnum.VANILLA189, signature = "(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/BlockPos;")
    public static Method offset_I;
    @WrapMethod(mcpName = "down", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
    public static Method down;
    @WrapMethod(mcpName = "up", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
    public static Method up;
    @WrapMethod(mcpName = "south", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
    public static Method south_I;
    @WrapMethod(mcpName = "south", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
    public static Method south;
    @WrapMethod(mcpName = "north", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
    public static Method north;
    @WrapMethod(mcpName = "east", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
    public static Method east;
    @WrapMethod(mcpName = "west", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
    public static Method west;
    @WrapMethod(mcpName = "down", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
    public static Method down_I;
    @WrapMethod(mcpName = "north", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
    public static Method north_I;
    @WrapMethod(mcpName = "west", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
    public static Method west_I;
    @WrapMethod(mcpName = "add", targetMap = MapEnum.VANILLA189, signature = "(III)Lnet/minecraft/util/BlockPos;")
    public static Method add_III;

    public static BlockPos ORIGIN;

    public BlockPos(Object obj) {
        super(obj);
    }

    public BlockPos(double x, double y, double z) {
        super(ReflectUtil.construction(BlockPos_DDD, x, y, z));
    }

    public BlockPos(Entity entity) {
        this(ReflectUtil.construction(BlockPos_Entity, entity.getWrapObject()));
    }

    public BlockPos(Vec3 vec) {
        this(ReflectUtil.construction(BlockPos_Vec, vec.getWrapObject()));
    }

    public static BlockPos wrap(Object o) {
        return new BlockPos(o);
    }

    public BlockPos offset(Enum facing) {
        return new BlockPos(invoke(offset, facing));
    }

    public BlockPos offset(Enum facing, int i) {
        return new BlockPos(invoke(offset_I, facing, i));
    }

    public BlockPos down() {
        return new BlockPos(invoke(down));
    }

    public BlockPos up() {
        return new BlockPos(invoke(up));
    }

    public BlockPos south(int i) {
        return new BlockPos(invoke(south_I, i));
    }

    public BlockPos south() {
        return new BlockPos(invoke(south));
    }

    public BlockPos north() {
        return new BlockPos(invoke(north));
    }

    public BlockPos east() {
        return new BlockPos(invoke(east));
    }

    public BlockPos west() {
        return new BlockPos(invoke(west));
    }

    public BlockPos down(int i) {
        return new BlockPos(invoke(down_I, i));
    }

    public BlockPos north(int i) {
        return new BlockPos(invoke(north_I, i));
    }

    public BlockPos west(int i) {
        return new BlockPos(invoke(west_I, i));
    }

    public BlockPos add(int x, int y, int z) {
        return new BlockPos(invoke(add_III, x, y, z));
    }
}