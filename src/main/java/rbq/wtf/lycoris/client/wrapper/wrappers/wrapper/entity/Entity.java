package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.AxisAlignedBB;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.Vec3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.Entity", targetMap = MapEnum.VANILLA189)
public class Entity extends IWrapper {
    @WrapField(mcpName = "rotationYaw", targetMap = MapEnum.VANILLA189)
    public static Field rotationYaw;
    @WrapField(mcpName = "rotationPitch", targetMap = MapEnum.VANILLA189)
    public static Field rotationPitch;
    @WrapMethod(mcpName = "onUpdate", targetMap = MapEnum.VANILLA189)
    public static Method onUpdate;
    @WrapField(mcpName = "ticksExisted", targetMap = MapEnum.VANILLA189)
    public static Field ticksExisted;
    @WrapMethod(mcpName = "playSound", targetMap = MapEnum.VANILLA189)
    public static Method playSound;
    @WrapField(mcpName = "posX", targetMap = MapEnum.VANILLA189)
    public static Field posX;
    @WrapField(mcpName = "posY", targetMap = MapEnum.VANILLA189)
    public static Field posY;
    @WrapField(mcpName = "posZ", targetMap = MapEnum.VANILLA189)
    public static Field posZ;
    @WrapField(mcpName = "lastTickPosX", targetMap = MapEnum.VANILLA189)
    public static Field lastTickPosX;
    @WrapField(mcpName = "lastTickPosY", targetMap = MapEnum.VANILLA189)
    public static Field lastTickPosY;
    @WrapField(mcpName = "lastTickPosZ", targetMap = MapEnum.VANILLA189)
    public static Field lastTickPosZ;
    @WrapField(mcpName = "boundingBox", targetMap = MapEnum.VANILLA189)
    public static Field boundingBox;
    @WrapClass(mcpName = "net.minecraft.entity.Entity", targetMap = MapEnum.VANILLA189)
    public static Class EntityClass;
    @WrapField(mcpName = "onGround", targetMap = MapEnum.VANILLA189)
    public static Field onGround;
    @WrapMethod(mcpName = "getDisplayName", targetMap = MapEnum.VANILLA189)
    public static Method getDisplayName;
    @WrapField(mcpName = "isDead", targetMap = MapEnum.VANILLA189)
    public static Field isDead;
    @WrapMethod(mcpName = "getName", targetMap = MapEnum.VANILLA189)
    public static Method getName;
    @WrapMethod(mcpName = "getDistanceToEntity", targetMap = MapEnum.VANILLA189)
    public static Method getDistanceToEntity;
    @WrapMethod(mcpName = "getDistance", targetMap = MapEnum.VANILLA189, signature = "(DDD)D")
    public static Method getDistance_DDD;
    @WrapField(mcpName = "motionX", targetMap = MapEnum.VANILLA189)
    public static Field motionX;
    @WrapField(mcpName = "motionY", targetMap = MapEnum.VANILLA189)
    public static Field motionY;
    @WrapField(mcpName = "motionZ", targetMap = MapEnum.VANILLA189)
    public static Field motionZ;
    @WrapMethod(mcpName = "isSprinting", targetMap = MapEnum.VANILLA189)
    public static Method isSprinting;
    @WrapField(mcpName = "prevPosX", targetMap = MapEnum.VANILLA189)
    public static Field prevPosX;
    @WrapField(mcpName = "prevPosY", targetMap = MapEnum.VANILLA189)
    public static Field prevPosY;
    @WrapField(mcpName = "prevPosZ", targetMap = MapEnum.VANILLA189)
    public static Field prevPosZ;
    @WrapField(mcpName = "height", targetMap = MapEnum.VANILLA189)
    public static Field height;
    @WrapMethod(mcpName = "getEyeHeight", targetMap = MapEnum.VANILLA189)
    public static Method getEyeHeight;
    @WrapMethod(mcpName = "moveEntity", targetMap = MapEnum.VANILLA189)
    public static Method moveEntity;
    @WrapMethod(mcpName = "isRiding", targetMap = MapEnum.VANILLA189)
    public static Method isRiding;
    @WrapField(mcpName = "stepHeight", targetMap = MapEnum.VANILLA189)
    public static Field stepHeight;
    @WrapField(mcpName = "isCollidedVertically", targetMap = MapEnum.VANILLA189)
    public static Field isCollidedVertically;
    @WrapMethod(mcpName = "canBeCollidedWith", targetMap = MapEnum.VANILLA189)
    public static Method canBeCollidedWith;
    @WrapMethod(mcpName = "setPositionAndRotation", targetMap = MapEnum.VANILLA189)
    public static Method setPositionAndRotation;
    @WrapField(mcpName = "width", targetMap = MapEnum.VANILLA189)
    public static Field width;
    @WrapField(mcpName = "isInWeb", targetMap = MapEnum.VANILLA189)
    public static Field isInWeb;
    @WrapField(mcpName = "fallDistance", targetMap = MapEnum.VANILLA189)
    public static Field fallDistance;
    @WrapMethod(mcpName = "getHorizontalFacing", targetMap = MapEnum.VANILLA189)
    public static Method getHorizontalFacing;
    @WrapMethod(mcpName = "setPosition", targetMap = MapEnum.VANILLA189)
    public static Method setPosition;
    @WrapMethod(mcpName = "setPositionAndUpdate", targetMap = MapEnum.VANILLA189)
    public static Method setPositionAndUpdate;
    @WrapField(mcpName = "worldObj", targetMap = MapEnum.VANILLA189)
    public static Field worldObj;
    @WrapMethod(mcpName = "copyDataFromOld", targetMap = MapEnum.VANILLA189)
    public static Method copyDataFromOld;
    @WrapMethod(mcpName = "copyLocationAndAnglesFrom", targetMap = MapEnum.VANILLA189)
    public static Method copyLocationAndAnglesFrom;
    @WrapMethod(mcpName = "rayTrace", targetMap = MapEnum.VANILLA189)
    public static Method rayTrace;
    @WrapMethod(mcpName = "getPositionEyes", targetMap = MapEnum.VANILLA189)
    public static Method getPositionEyes;
    @WrapMethod(mcpName = "getLook", targetMap = MapEnum.VANILLA189)
    public static Method getLook;
    @WrapMethod(mcpName = "getCollisionBorderSize", targetMap = MapEnum.VANILLA189)
    public static Method getCollisionBorderSize;
    @WrapField(mcpName = "ridingEntity", targetMap = MapEnum.VANILLA189)
    public static Field ridingEntity;
    @WrapMethod(mcpName = "getDistanceSqToEntity", targetMap = MapEnum.VANILLA189)
    public static Method getDistanceSqToEntity;
    @WrapMethod(mcpName = "getDistanceSq", targetMap = MapEnum.VANILLA189, signature = "(DDD)D")
    public static Method getDistanceSq_D;
    @WrapMethod(mcpName = "isInvisible", targetMap = MapEnum.VANILLA189)
    public static Method isInvisible;
    @WrapMethod(mcpName = "isSneaking", targetMap = MapEnum.VANILLA189)
    public static Method isSneaking;
    @WrapField(mcpName = "isCollidedHorizontally", targetMap = MapEnum.VANILLA189)
    public static Field isCollidedHorizontally;
    @WrapMethod(mcpName = "getEntityBoundingBox", targetMap = MapEnum.VANILLA189)
    public static Method getEntityBoundingBox;

    public Entity(Object obj) {
        super(obj);
    }

    public float getRotationPitch() {
        return (float) getField(rotationPitch);
    }

    public float getRotationYaw() {
        return (float) getField(rotationYaw);
    }

    public int getTicksExisted() {
        return (int) getField(ticksExisted);
    }

    public void playSound(String name, int volume, int pitch) {
        invoke(playSound, name, volume, pitch);
    }

    public double getDistanceSq(double x, double y, double z) {
        return (double) invoke(getDistanceSq_D, x, y, z);
    }

    //    public World getWorld(){
//        return new World(getField(worldObj));
//    }
//    public MovingObjectPosition rayTrace(double d,float f){
//        return new MovingObjectPosition(invoke(rayTrace,d,f));
//    }
    public Vec3 getPositionEyes(float partialTicks) {
        return new Vec3(invoke(getPositionEyes, partialTicks));
    }

    public Vec3 getLook(float partialTicks) {
        return new Vec3(invoke(getLook, partialTicks));
    }

    public double getPosX() {
        return (double) getField(posX);
    }

    public double getPosY() {
        return (double) getField(posY);
    }

    public double getPosZ() {
        return (double) getField(posZ);
    }

    public double getLastTickPosX() {
        return (double) getField(lastTickPosX);
    }

    public double getLastTickPosY() {
        return (double) getField(lastTickPosY);
    }

    public double getLastTickPosZ() {
        return (double) getField(lastTickPosZ);
    }

    public AxisAlignedBB getEntityBoundingBox() {
        return new AxisAlignedBB(getField(boundingBox));
    }

    public void copyLocationAndAnglesFrom(Entity entity) {
        invoke(copyLocationAndAnglesFrom, entity.getWrapObject());
    }

    public void setRotationYaw(float yaw) {
        setField(rotationYaw, yaw);
    }

    public void setRotationPitch(float pitch) {
        setField(rotationPitch, pitch);
    }

    public void setOnGround(boolean isOnGround) {
        setField(onGround, isOnGround);
    }

    public boolean isOnGround() {
        return (boolean) getField(onGround);
    }

    //    public IChatComponent getDisplayName(){
//        return new IChatComponent(ReflectUtil.invoke(getDisplayName,getWrapObject()));
//    }
    public boolean isDead() {
        return (boolean) getField(isDead);
    }

    public String getName() {
        return (String) invoke(getName);
    }

    public float getDistanceToEntity(Entity entity) {
        return (float) invoke(getDistanceToEntity, entity.getWrapObject());
    }

    public void copyDataFromOld(Entity entity) {
        invoke(copyDataFromOld, entity.getWrapObject());
    }

    public double getMotionX() {
        return (double) getField(motionX);
    }

    public double getMotionY() {
        return (double) getField(motionY);
    }

    public double getMotionZ() {
        return (double) getField(motionZ);
    }

    public void setPrevPosX(double d) {
        setField(prevPosX, d);
    }

    public void setPrevPosY(double d) {
        setField(prevPosY, d);
    }

    public void setPrevPosZ(double d) {
        setField(prevPosZ, d);
    }

    public boolean getIsInWeb() {
        return (boolean) getField(isInWeb);
    }

    public void setIsInWeb(boolean isIn) {
        setField(isInWeb, isIn);
    }

    public boolean isSprinting() {
        return (boolean) invoke(isSprinting);
    }

    public double getPrevPosX() {
        return (double) getField(prevPosX);
    }

    public double getPrevPosY() {
        return (double) getField(prevPosY);
    }

    public double getPrevPosZ() {
        return (double) getField(prevPosZ);
    }

    public float getHeight() {
        return (float) getField(height);
    }

    public float getEyeHeight() {
        return (float) invoke(getEyeHeight);
    }

    public void setMotionX(double d) {
        setField(motionX, d);
    }

    public void setMotionY(double d) {
        setField(motionY, d);
    }

    public void setMotionZ(double d) {
        setField(motionZ, d);
    }

    public boolean isRiding() {
        return (boolean) invoke(isRiding);
    }

    public void setStepHeight(float f) {
        setField(stepHeight, f);
    }

    public float getStepHeight() {
        return (float) getField(stepHeight);
    }

    public boolean isCollidedVertically() {
        return (boolean) getField(isCollidedVertically);
    }

    public boolean isCollidedHorizontally() {
        return (boolean) getField(isCollidedHorizontally);
    }

    public boolean canBeCollidedWith() {
        return (boolean) invoke(canBeCollidedWith);
    }

    public void setWidth(float f) {
        setField(width, f);
    }

    public float getWidth() {
        return (float) getField(width);
    }

    public double getDistance(double x, double y, double z) {
        return (double) invoke(getDistance_DDD, x, y, z);
    }

    public float getFallDistance() {
        return (float) getField(fallDistance);
    }

    public void setPositionAndRotation(double x, double y, double z, float rotationYaw, float rotationPitch) {
        invoke(setPositionAndRotation, x, y, z, rotationYaw, rotationPitch);
    }

    public Enum getHorizontalFacing() {
        return (Enum) invoke(getHorizontalFacing);
    }

    public void setPosition(double x, double y, double z) {
        invoke(setPosition, x, y, z);
    }

    public void setPositionAndUpdate(double x, double y, double z) {
        invoke(setPositionAndUpdate, x, y, z);
    }

    public float getCollisionBorderSize() {
        return (float) invoke(getCollisionBorderSize);
    }

    public Entity getRidingEntity() {
        return new Entity(getField(ridingEntity));
    }

    public double getDistanceSqToEntity(Entity entity) {
        return (double) invoke(getDistanceSqToEntity, entity.getWrapObject());
    }

    public boolean isInvisible() {
        return (boolean) invoke(isInvisible);
    }

    public boolean isSneaking() {
        return (boolean) invoke(isSneaking);
    }

}