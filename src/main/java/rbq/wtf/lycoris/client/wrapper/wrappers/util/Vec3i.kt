package rbq.wtf.lycoris.client.wrapper.wrappers.util

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapConstructor
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.IWrapper
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.util.Vec3i", targetMap = MapEnum.VANILLA189)
open class Vec3i(obj: Any) : IWrapper(obj) {
    constructor(xIn: Double, yIn: Double, zIn: Double) : this(ReflectUtil.construction(Vec3i_DDD, xIn, yIn, zIn))

    companion object {
        @WrapField(mcpName = "x", targetMap = MapEnum.VANILLA189)
        lateinit var x: Field

        @WrapField(mcpName = "y", targetMap = MapEnum.VANILLA189)
        lateinit var y: Field

        @WrapField(mcpName = "z", targetMap = MapEnum.VANILLA189)
        lateinit var z: Field

        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [Double::class, Double::class, Double::class])
        lateinit var Vec3i_DDD: Constructor<*>

        @WrapMethod(mcpName = "distanceSq", targetMap = MapEnum.VANILLA1122, signature = "(DDD)D")
        lateinit var distanceSq: Method
    }
    
    val x: Int
        get() = getField(Companion.x) as Int
    val y: Int
        get() = getField(Companion.y) as Int
    val z: Int
        get() = getField(Companion.z) as Int

    fun distanceTo(posX: Double, posY: Double, posZ: Double): Double {
        return invoke(distanceSq, posX, posY, posZ) as Double
    }
}