package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.*
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.util.Vec3", targetMap = MapEnum.VANILLA189)
class Vec3(obj: Any) : IWrapper(obj) {
    constructor(vec3i: Vec3i) : this(ReflectUtil.construction(Vec3_Vec3i, vec3i.wrapObject)) {}
    constructor(x: Double, y: Double, z: Double) : this(ReflectUtil.construction(Vec3_DDD, x, y, z)) {}

    companion object {
        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [Double::class, Double::class, Double::class])
        lateinit var Vec3_DDD: Constructor<*>

        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [Vec3i::class])
        lateinit var Vec3_Vec3i: Constructor<*>

        @WrapMethod(mcpName = "addVector", targetMap = MapEnum.VANILLA189)
        lateinit var addVector: Method

        @WrapField(mcpName = "xCoord", targetMap = MapEnum.VANILLA189)
        lateinit var xCoord: Field

        @WrapField(mcpName = "yCoord", targetMap = MapEnum.VANILLA189)
        lateinit var yCoord: Field

        @WrapField(mcpName = "zCoord", targetMap = MapEnum.VANILLA189)
        lateinit var zCoord: Field

        @WrapMethod(mcpName = "add", targetMap = MapEnum.VANILLA189)
        lateinit var add: Method

        @WrapMethod(mcpName = "squareDistanceTo", targetMap = MapEnum.VANILLA189)
        lateinit var squareDistanceTo: Method

        @WrapMethod(mcpName = "distanceTo", targetMap = MapEnum.VANILLA189)
        lateinit var distanceTo: Method

        @WrapMethod(
            mcpName = "subtract",
            targetMap = MapEnum.VANILLA189,
            signature = "(Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/Vec3;"
        )
        lateinit var subtract_Vec: Method

        @WrapMethod(mcpName = "lengthVector", targetMap = MapEnum.VANILLA189)
        lateinit var lengthVector: Method
    }

    fun addVector(x: Double, y: Double, z: Double): Vec3 {
        return invoke(addVector, x, y, z)?.let { Vec3(it) }!!
    }

    val xCoord: Double
        get() = getField(Companion.xCoord) as Double
    val yCoord: Double
        get() = getField(Companion.yCoord) as Double
    val zCoord: Double
        get() = getField(Companion.zCoord) as Double

    fun add(vec3: Vec3): Vec3 {
        return invoke(add, vec3.wrapObject)?.let { Vec3(it) }!!
    }

    fun squareDistanceTo(vec3: Vec3): Double {
        return invoke(squareDistanceTo, vec3.wrapObject) as Double
    }

    fun distanceTo(vec3: Vec3): Double {
        return invoke(distanceTo!!, vec3.wrapObject) as Double
    }

    fun subtract(vec3: Vec3): Vec3 {
        return invoke(subtract_Vec, vec3.wrapObject)?.let { Vec3(it) }!!
    }

    fun lengthVector(): Double {
        return invoke(lengthVector!!) as Double
    }
}