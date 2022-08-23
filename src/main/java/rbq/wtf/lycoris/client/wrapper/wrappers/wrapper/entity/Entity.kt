package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.AxisAlignedBB
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.Vec3
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.entity.Entity", targetMap = MapEnum.VANILLA189)
open class Entity(obj: Any) : IWrapper(obj) {
    var rotationPitch: Float
        get() = getField(Companion.rotationPitch) as Float
        set(pitch) {
            setField(Companion.rotationPitch, pitch)
        }
    var rotationYaw: Float
        get() = getField(Companion.rotationYaw) as Float
        set(yaw) {
            setField(Companion.rotationYaw, yaw)
        }
    val ticksExisted: Int
        get() = getField(Companion.ticksExisted) as Int

    fun playSound(name: String?, volume: Int, pitch: Int) {
        invoke(playSound, name, volume, pitch)
    }

    fun getDistanceSq(x: Double, y: Double, z: Double): Double {
        return invoke(getDistanceSq_D, x, y, z) as Double
    }

    //    public World getWorld(){
    //        return new World(getField(worldObj));
    //    }
    //    public MovingObjectPosition rayTrace(double d,float f){
    //        return new MovingObjectPosition(invoke(rayTrace,d,f));
    //    }
    fun getPositionEyes(partialTicks: Float): Vec3 {
        return Vec3(invoke(getPositionEyes, partialTicks)!!)
    }

    fun getLook(partialTicks: Float): Vec3 {
        return Vec3(invoke(getLook, partialTicks)!!)
    }

    val posX: Double
        get() = getField(Companion.posX) as Double
    val posY: Double
        get() = getField(Companion.posY) as Double
    val posZ: Double
        get() = getField(Companion.posZ) as Double
    val lastTickPosX: Double
        get() = getField(Companion.lastTickPosX) as Double
    val lastTickPosY: Double
        get() = getField(Companion.lastTickPosY) as Double
    val lastTickPosZ: Double
        get() = getField(Companion.lastTickPosZ) as Double
    val entityBoundingBox: AxisAlignedBB
        get() = AxisAlignedBB(getField(boundingBox)!!)

    fun copyLocationAndAnglesFrom(entity: Entity) {
        invoke(copyLocationAndAnglesFrom, entity.wrapObject)
    }

    var isOnGround: Boolean
        get() = getField(onGround) as Boolean
        set(isOnGround) {
            setField(onGround, isOnGround)
        }

    //    public IChatComponent getDisplayName(){
    //        return new IChatComponent(ReflectUtil.invoke(getDisplayName,getWrapObject()));
    //    }

    val isDead: Boolean
        get() = getField(Companion.isDead) as Boolean
    val name: String?
        get() = invoke(getName) as String?

    fun getDistanceToEntity(entity: Entity): Float {
        return invoke(getDistanceToEntity, entity.wrapObject) as Float
    }

    fun copyDataFromOld(entity: Entity) {
        invoke(copyDataFromOld, entity.wrapObject)
    }

    var motionX: Double
        get() = getField(Companion.motionX) as Double
        set(d) {
            setField(Companion.motionX, d)
        }
    var motionY: Double
        get() = getField(Companion.motionY) as Double
        set(d) {
            setField(Companion.motionY, d)
        }
    var motionZ: Double
        get() = getField(Companion.motionZ) as Double
        set(d) {
            setField(Companion.motionZ, d)
        }
    var isInWeb: Boolean
        get() = getField(Companion.isInWeb) as Boolean
        set(isIn) {
            setField(Companion.isInWeb, isIn)
        }
    val isSprinting: Boolean
        get() = invoke(Companion.isSprinting) as Boolean
    var prevPosX: Double
        get() = getField(Companion.prevPosX) as Double
        set(d) {
            setField(Companion.prevPosX, d)
        }
    var prevPosY: Double
        get() = getField(Companion.prevPosY) as Double
        set(d) {
            setField(Companion.prevPosY, d)
        }
    var prevPosZ: Double
        get() = getField(Companion.prevPosZ) as Double
        set(d) {
            setField(Companion.prevPosZ, d)
        }
    val height: Float
        get() = getField(Companion.height) as Float
    val eyeHeight: Float
        get() = invoke(getEyeHeight) as Float
    val isRiding: Boolean
        get() = invoke(Companion.isRiding) as Boolean
    var stepHeight: Float
        get() = getField(Companion.stepHeight) as Float
        set(f) {
            setField(Companion.stepHeight, f)
        }
    val isCollidedVertically: Boolean
        get() = getField(Companion.isCollidedVertically) as Boolean
    val isCollidedHorizontally: Boolean
        get() = getField(Companion.isCollidedHorizontally) as Boolean

    fun canBeCollidedWith(): Boolean {
        return invoke(canBeCollidedWith) as Boolean
    }

    var width: Float
        get() = getField(Companion.width) as Float
        set(f) {
            setField(Companion.width, f)
        }

    fun getDistance(x: Double, y: Double, z: Double): Double {
        return invoke(getDistance_DDD, x, y, z) as Double
    }

    val fallDistance: Float
        get() = getField(Companion.fallDistance) as Float

    fun setPositionAndRotation(x: Double, y: Double, z: Double, rotationYaw: Float, rotationPitch: Float) {
        invoke(setPositionAndRotation, x, y, z, rotationYaw, rotationPitch)
    }

    val horizontalFacing: Enum<*>?
        get() = invoke(getHorizontalFacing) as Enum<*>?

    fun setPosition(x: Double, y: Double, z: Double) {
        invoke(setPosition, x, y, z)
    }

    fun setPositionAndUpdate(x: Double, y: Double, z: Double) {
        invoke(setPositionAndUpdate, x, y, z)
    }

    val collisionBorderSize: Float
        get() = invoke(getCollisionBorderSize) as Float
    val ridingEntity: Entity?
        get() = getField(Companion.ridingEntity)?.let { Entity(it) }

    fun getDistanceSqToEntity(entity: Entity): Double {
        return invoke(getDistanceSqToEntity, entity.wrapObject) as Double
    }

    val isInvisible: Boolean
        get() = invoke(Companion.isInvisible) as Boolean
    open val isSneaking: Boolean
        get() = invoke(Companion.isSneaking) as Boolean

    companion object {
        @WrapField(mcpName = "rotationYaw", targetMap = MapEnum.VANILLA189)
        lateinit var rotationYaw: Field

        @WrapField(mcpName = "rotationPitch", targetMap = MapEnum.VANILLA189)
        lateinit var rotationPitch: Field

        @WrapMethod(mcpName = "onUpdate", targetMap = MapEnum.VANILLA189)
        lateinit var onUpdate: Method

        @WrapField(mcpName = "ticksExisted", targetMap = MapEnum.VANILLA189)
        lateinit var ticksExisted: Field

        @WrapMethod(mcpName = "playSound", targetMap = MapEnum.VANILLA189)
        lateinit var playSound: Method

        @WrapField(mcpName = "posX", targetMap = MapEnum.VANILLA189)
        lateinit var posX: Field

        @WrapField(mcpName = "posY", targetMap = MapEnum.VANILLA189)
        lateinit var posY: Field

        @WrapField(mcpName = "posZ", targetMap = MapEnum.VANILLA189)
        lateinit var posZ: Field

        @WrapField(mcpName = "lastTickPosX", targetMap = MapEnum.VANILLA189)
        lateinit var lastTickPosX: Field

        @WrapField(mcpName = "lastTickPosY", targetMap = MapEnum.VANILLA189)
        lateinit var lastTickPosY: Field

        @WrapField(mcpName = "lastTickPosZ", targetMap = MapEnum.VANILLA189)
        lateinit var lastTickPosZ: Field

        @WrapField(mcpName = "boundingBox", targetMap = MapEnum.VANILLA189)
        lateinit var boundingBox: Field

        @WrapClass(mcpName = "net.minecraft.entity.Entity", targetMap = MapEnum.VANILLA189)
        lateinit var EntityClass: Class<*>

        @WrapField(mcpName = "onGround", targetMap = MapEnum.VANILLA189)
        lateinit var onGround: Field

        @WrapMethod(mcpName = "getDisplayName", targetMap = MapEnum.VANILLA189)
        lateinit var getDisplayName: Method

        @WrapField(mcpName = "isDead", targetMap = MapEnum.VANILLA189)
        lateinit var isDead: Field

        @WrapMethod(mcpName = "getName", targetMap = MapEnum.VANILLA189)
        lateinit var getName: Method

        @WrapMethod(mcpName = "getDistanceToEntity", targetMap = MapEnum.VANILLA189)
        lateinit var getDistanceToEntity: Method

        @WrapMethod(mcpName = "getDistance", targetMap = MapEnum.VANILLA189, signature = "(DDD)D")
        lateinit var getDistance_DDD: Method

        @WrapField(mcpName = "motionX", targetMap = MapEnum.VANILLA189)
        lateinit var motionX: Field

        @WrapField(mcpName = "motionY", targetMap = MapEnum.VANILLA189)
        lateinit var motionY: Field

        @WrapField(mcpName = "motionZ", targetMap = MapEnum.VANILLA189)
        lateinit var motionZ: Field

        @WrapMethod(mcpName = "isSprinting", targetMap = MapEnum.VANILLA189)
        lateinit var isSprinting: Method

        @WrapField(mcpName = "prevPosX", targetMap = MapEnum.VANILLA189)
        lateinit var prevPosX: Field

        @WrapField(mcpName = "prevPosY", targetMap = MapEnum.VANILLA189)
        lateinit var prevPosY: Field

        @WrapField(mcpName = "prevPosZ", targetMap = MapEnum.VANILLA189)
        lateinit var prevPosZ: Field

        @WrapField(mcpName = "height", targetMap = MapEnum.VANILLA189)
        lateinit var height: Field

        @WrapMethod(mcpName = "getEyeHeight", targetMap = MapEnum.VANILLA189)
        lateinit var getEyeHeight: Method

        @WrapMethod(mcpName = "moveEntity", targetMap = MapEnum.VANILLA189)
        lateinit var moveEntity: Method

        @WrapMethod(mcpName = "isRiding", targetMap = MapEnum.VANILLA189)
        lateinit var isRiding: Method

        @WrapField(mcpName = "stepHeight", targetMap = MapEnum.VANILLA189)
        lateinit var stepHeight: Field

        @WrapField(mcpName = "isCollidedVertically", targetMap = MapEnum.VANILLA189)
        lateinit var isCollidedVertically: Field

        @WrapMethod(mcpName = "canBeCollidedWith", targetMap = MapEnum.VANILLA189)
        lateinit var canBeCollidedWith: Method

        @WrapMethod(mcpName = "setPositionAndRotation", targetMap = MapEnum.VANILLA189)
        lateinit var setPositionAndRotation: Method

        @WrapField(mcpName = "width", targetMap = MapEnum.VANILLA189)
        lateinit var width: Field

        @WrapField(mcpName = "isInWeb", targetMap = MapEnum.VANILLA189)
        lateinit var isInWeb: Field

        @WrapField(mcpName = "fallDistance", targetMap = MapEnum.VANILLA189)
        lateinit var fallDistance: Field

        @WrapMethod(mcpName = "getHorizontalFacing", targetMap = MapEnum.VANILLA189)
        lateinit var getHorizontalFacing: Method

        @WrapMethod(mcpName = "setPosition", targetMap = MapEnum.VANILLA189)
        lateinit var setPosition: Method

        @WrapMethod(mcpName = "setPositionAndUpdate", targetMap = MapEnum.VANILLA189)
        lateinit var setPositionAndUpdate: Method

        @WrapField(mcpName = "worldObj", targetMap = MapEnum.VANILLA189)
        lateinit var worldObj: Field

        @WrapMethod(mcpName = "copyDataFromOld", targetMap = MapEnum.VANILLA189)
        lateinit var copyDataFromOld: Method

        @WrapMethod(mcpName = "copyLocationAndAnglesFrom", targetMap = MapEnum.VANILLA189)
        lateinit var copyLocationAndAnglesFrom: Method

        @WrapMethod(mcpName = "rayTrace", targetMap = MapEnum.VANILLA189)
        lateinit var rayTrace: Method

        @WrapMethod(mcpName = "getPositionEyes", targetMap = MapEnum.VANILLA189)
        lateinit var getPositionEyes: Method

        @WrapMethod(mcpName = "getLook", targetMap = MapEnum.VANILLA189)
        lateinit var getLook: Method

        @WrapMethod(mcpName = "getCollisionBorderSize", targetMap = MapEnum.VANILLA189)
        lateinit var getCollisionBorderSize: Method

        @WrapField(mcpName = "ridingEntity", targetMap = MapEnum.VANILLA189)
        lateinit var ridingEntity: Field

        @WrapMethod(mcpName = "getDistanceSqToEntity", targetMap = MapEnum.VANILLA189)
        lateinit var getDistanceSqToEntity: Method

        @WrapMethod(mcpName = "getDistanceSq", targetMap = MapEnum.VANILLA189, signature = "(DDD)D")
        lateinit var getDistanceSq_D: Method

        @WrapMethod(mcpName = "isInvisible", targetMap = MapEnum.VANILLA189)
        lateinit var isInvisible: Method

        @WrapMethod(mcpName = "isSneaking", targetMap = MapEnum.VANILLA189)
        lateinit var isSneaking: Method

        @WrapField(mcpName = "isCollidedHorizontally", targetMap = MapEnum.VANILLA189)
        lateinit var isCollidedHorizontally: Field

        @WrapMethod(mcpName = "getEntityBoundingBox", targetMap = MapEnum.VANILLA189)
        lateinit var getEntityBoundingBox: Method
    }
}