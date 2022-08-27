package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.*
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.math.max
import kotlin.math.min

@WrapperClass(mcpName = "net.minecraft.util.AxisAlignedBB", targetMap = MapEnum.VANILLA189)
class AxisAlignedBB(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapConstructor(
            targetMap = MapEnum.VANILLA189,
            signature = [Double::class, Double::class, Double::class, Double::class, Double::class, Double::class]
        )
        lateinit var AxisAlignedBB_Constructor_DDDDDD: Constructor<*>

        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [BlockPos::class, BlockPos::class])
        lateinit var AxisAlignedBB_Constructor_BlockPos: Constructor<*>

        @WrapField(mcpName = "minX", targetMap = MapEnum.VANILLA189)
        lateinit var minX: Field

        @WrapField(mcpName = "minY", targetMap = MapEnum.VANILLA189)
        lateinit var minY: Field

        @WrapField(mcpName = "minZ", targetMap = MapEnum.VANILLA189)
        lateinit var minZ: Field

        @WrapField(mcpName = "maxX", targetMap = MapEnum.VANILLA189)
        lateinit var maxX: Field

        @WrapField(mcpName = "maxY", targetMap = MapEnum.VANILLA189)
        lateinit var maxY: Field

        @WrapField(mcpName = "maxZ", targetMap = MapEnum.VANILLA189)
        lateinit var maxZ: Field

        @WrapMethod(mcpName = "offset", targetMap = MapEnum.VANILLA189)
        lateinit var offset_3d: Method

        @WrapMethod(mcpName = "expand", targetMap = MapEnum.VANILLA189)
        lateinit var expand: Method

        @WrapMethod(mcpName = "addCoord", targetMap = MapEnum.VANILLA189)
        lateinit var addCoord: Method

        @WrapMethod(mcpName = "calculateIntercept", targetMap = MapEnum.VANILLA189)
        lateinit var calculateIntercept: Method

        @WrapMethod(mcpName = "isVecInside", targetMap = MapEnum.VANILLA189)
        lateinit var isVecInside: Method

        fun fromBounds(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double): AxisAlignedBB {
            val d0 = min(x1, x2)
            val d1 = min(y1, y2)
            val d2 = min(z1, z2)
            val d3 = max(x1, x2)
            val d4 = max(y1, y2)
            val d5 = max(z1, z2)
            return AxisAlignedBB(d0, d1, d2, d3, d4, d5)
        }
    }

    constructor(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double) : this(
        ReflectUtil.construction(
            AxisAlignedBB_Constructor_DDDDDD, x1, y1, z1, x2, y2, z2
        )
    )

    constructor(pos1: BlockPos, pos2: BlockPos) : this(
        ReflectUtil.construction(
            AxisAlignedBB_Constructor_BlockPos,
            pos1.wrapObject,
            pos2.wrapObject
        )
    )

    val minZ: Double
        get() = getField(Companion.minZ) as Double
    val minY: Double
        get() = getField(Companion.minY) as Double
    val maxZ: Double
        get() = getField(Companion.maxZ) as Double
    val maxY: Double
        get() = getField(Companion.maxY) as Double
    val maxX: Double
        get() = getField(Companion.maxX) as Double
    val minX: Double
        get() = getField(Companion.minX) as Double

    fun offset(x: Double, y: Double, z: Double): AxisAlignedBB {
        return invoke(offset_3d, x, y, z)?.let { AxisAlignedBB(it) }!!
    }

    fun expand(x: Double, y: Double, z: Double): AxisAlignedBB {
        return invoke(expand, x, y, z)?.let { AxisAlignedBB(it) }!!
    }

    fun addCoord(x: Double, y: Double, z: Double): AxisAlignedBB {
        return invoke(addCoord, x, y, z)?.let { AxisAlignedBB(it) }!!
    }

    fun isVecInside(vec3: Vec3): Boolean {
        return invoke(isVecInside, vec3.wrapObject) as Boolean
    }
}