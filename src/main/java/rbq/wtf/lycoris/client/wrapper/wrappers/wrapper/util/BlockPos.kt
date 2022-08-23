package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapConstructor
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.Entity
import java.lang.reflect.Constructor
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.util.BlockPos", targetMap = MapEnum.VANILLA189)
class BlockPos(obj: Any) : Vec3i(obj) {
    constructor(x: Double, y: Double, z: Double) : this(ReflectUtil.construction(BlockPos_DDD, x, y, z))
    constructor(entity: Entity) : this(ReflectUtil.construction(BlockPos_Entity, entity.wrapObject))
    constructor(vec: Vec3) : this(ReflectUtil.construction(BlockPos_Vec, vec.wrapObject))

    companion object {
        @WrapClass(mcpName = "net.minecraft.util.BlockPos", targetMap = MapEnum.VANILLA189)
        lateinit var BlockPosClass: Class<*>

        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [Double::class, Double::class, Double::class])
        lateinit var BlockPos_DDD: Constructor<*>

        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [Entity::class])
        lateinit var BlockPos_Entity: Constructor<*>

        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [Vec3::class])
        lateinit var BlockPos_Vec: Constructor<*>

        @WrapMethod(
            mcpName = "offset",
            targetMap = MapEnum.VANILLA189,
            signature = "(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;"
        )
        lateinit var offset: Method

        @WrapMethod(
            mcpName = "offset",
            targetMap = MapEnum.VANILLA189,
            signature = "(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/BlockPos;"
        )
        lateinit var offset_I: Method

        @WrapMethod(mcpName = "down", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
        lateinit var down: Method

        @WrapMethod(mcpName = "up", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
        lateinit var up: Method

        @WrapMethod(mcpName = "south", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
        lateinit var south_I: Method

        @WrapMethod(mcpName = "south", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
        lateinit var south: Method

        @WrapMethod(mcpName = "north", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
        lateinit var north: Method

        @WrapMethod(mcpName = "east", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
        lateinit var east: Method

        @WrapMethod(mcpName = "west", targetMap = MapEnum.VANILLA189, signature = "()Lnet/minecraft/util/BlockPos;")
        lateinit var west: Method

        @WrapMethod(mcpName = "down", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
        lateinit var down_I: Method

        @WrapMethod(mcpName = "north", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
        lateinit var north_I: Method

        @WrapMethod(mcpName = "west", targetMap = MapEnum.VANILLA189, signature = "(I)Lnet/minecraft/util/BlockPos;")
        lateinit var west_I: Method

        @WrapMethod(mcpName = "add", targetMap = MapEnum.VANILLA189, signature = "(III)Lnet/minecraft/util/BlockPos;")
        lateinit var add_III: Method
        lateinit var ORIGIN: BlockPos
    }

    fun offset(facing: Enum<*>): BlockPos {
        return invoke(offset, facing)?.let { BlockPos(it) }!!
    }

    fun offset(facing: Enum<*>, i: Int): BlockPos {
        return invoke(offset_I, facing, i)?.let { BlockPos(it) }!!
    }

    fun down(): BlockPos {
        return invoke(down)?.let { BlockPos(it) }!!
    }

    fun up(): BlockPos {
        return invoke(up)?.let { BlockPos(it) }!!
    }

    fun south(i: Int): BlockPos {
        return invoke(south_I, i)?.let { BlockPos(it) }!!
    }

    fun south(): BlockPos {
        return invoke(south)?.let { BlockPos(it) }!!
    }

    fun north(): BlockPos {
        return invoke(north)?.let { BlockPos(it) }!!
    }

    fun east(): BlockPos {
        return invoke(east)?.let { BlockPos(it) }!!
    }

    fun west(): BlockPos {
        return invoke(west)?.let { BlockPos(it) }!!
    }

    fun down(i: Int): BlockPos {
        return invoke(down_I, i)?.let { BlockPos(it) }!!
    }

    fun north(i: Int): BlockPos {
        return invoke(north_I, i)?.let { BlockPos(it) }!!
    }

    fun west(i: Int): BlockPos {
        return invoke(west_I, i)?.let { BlockPos(it) }!!
    }

    fun add(x: Int, y: Int, z: Int): BlockPos {
        return invoke(add_III, x, y, z)?.let { BlockPos(it) }!!
    }
}