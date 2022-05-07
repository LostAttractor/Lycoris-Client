/*
 * Decompiled with CFR 0_132.
 */
package rbq.wtf.lycoris.client.utils;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Field;
import java.util.Random;

public class RotationUtil {
    static Minecraft mc = Minecraft.getMinecraft();
    public static float pitch() {
        return mc.player.rotationPitch;
    }
    public static float a(double motionX, double motionZ) {
        double v1 = motionX - Minecraft.getMinecraft().player.posX;
        double v32 = motionZ - Minecraft.getMinecraft().player.posZ;
        double v5 = MathHelper.ceil((float) (v1 * v1 + v32 * v32));
        return (float)(Math.atan2(v32, v1) * 180.0 / 3.141592653589793) - 90.0f;
    }
    public static void pitch(float pitch) {
        mc.player.rotationPitch = pitch;
    }

    public static float yaw() {
        return mc.player.rotationYaw;
    }

    public static void yaw(float yaw) {
        mc.player.rotationYaw = yaw;
    }

    public static float[] faceTarget(Entity target, float p_706252, float p_706253, boolean miss) {
        double var6;
        double var4 = target.posX - mc.player.posX;
        double var8 = target.posZ - mc.player.posZ;
        if (target instanceof EntityLivingBase) {
            EntityLivingBase var10 = (EntityLivingBase)target;
            var6 = var10.posY + (double)var10.getEyeHeight() - (mc.player.posY + (double)mc.player.getEyeHeight());
        } else {
            var6 = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0 - (mc.player.posY + (double)mc.player.getEyeHeight());
        }
        Random rnd = new Random();
        double var14 = MathHelper.sqrt(var4 * var4 + var8 * var8);
        float var12 = (float)(Math.atan2(var8, var4) * 180.0 / 3.141592653589793) - 90.0f;
        float var13 = (float)(- Math.atan2(var6 - (target instanceof EntityPlayer ? 0.25 : 0.0), var14) * 180.0 / 3.141592653589793);
        float pitch = RotationUtil.changeRotation(mc.player.rotationPitch, var13, p_706253);
        float yaw = RotationUtil.changeRotation(mc.player.rotationYaw, var12, p_706252);
        return new float[]{yaw, pitch};
    }

    public static float[] getNeededFacing(Vec3d target, Vec3d from) {
        double diffX = target.x - from.x;
        double diffY = target.y - from.y;
        double diffZ = target.z - from.z;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[]{MathHelper.wrapDegrees(yaw), MathHelper.wrapDegrees(pitch)};
    }

    public static float changeRotation(float p_706631, float p_706632, float p_706633) {
        float var4 = MathHelper.wrapDegrees(p_706632 - p_706631);
        if (var4 > p_706633) {
            var4 = p_706633;
        }
        if (var4 < - p_706633) {
            var4 = - p_706633;
        }
        return p_706631 + var4;
    }
    public static float[] getNeededRotations(Vec3d vec) {
        Vec3d playerVector = new Vec3d(mc.player.posX, mc.player.posY + (double)mc.player.getEyeHeight(), mc.player.posZ);
        double y = vec.y - playerVector.y;
        double x = vec.x - playerVector.x;
        double z = vec.z - playerVector.z;
        double dff = Math.sqrt(x * x + z * z);
        float yaw = (float)Math.toDegrees(Math.atan2(z, x)) - 90.0F;
        float pitch = (float)(-Math.toDegrees(Math.atan2(y, dff)));
        return new float[]{MathHelper.wrapDegrees(yaw), MathHelper.wrapDegrees(pitch)};
    }

    public static double[] getRotationToEntity(Entity entity) {
        double pX = mc.player.posX;
        double pY = mc.player.posY + (double)mc.player.getEyeHeight();
        double pZ = mc.player.posZ;
        double eX = entity.posX;
        double eY = entity.posY + (double)(entity.height / 2.0f);
        double eZ = entity.posZ;
        double dX = pX - eX;
        double dY = pY - eY;
        double dZ = pZ - eZ;
        double dH = Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0));
        double yaw = Math.toDegrees(Math.atan2(dZ, dX)) + 90.0;
        double pitch = Math.toDegrees(Math.atan2(dH, dY));
        return new double[]{yaw, 90.0 - pitch};
    }

    public static float[] getRotationToEntityF(Entity entity) {
        double pX = Minecraft.getMinecraft().player.posX;
        double pY = Minecraft.getMinecraft().player.posY + (double) Minecraft.getMinecraft().player.getEyeHeight();
        double pZ = Minecraft.getMinecraft().player.posZ;
        double eX = entity.posX;
        double eY = entity.posY + (double) entity.getEyeHeight();
        double eZ = entity.posZ;
        double dX = pX - eX;
        double dY = pY - eY;
        double dZ = pZ - eZ;
        double dH = Math.sqrt(Math.pow(dX, 2.0D) + Math.pow(dZ, 2.0D));
        float yaw = 0.0F;
        float pitch = 0.0F;
        yaw = (float) (Math.toDegrees(Math.atan2(dZ, dX)) + 90.0D);
        pitch = (float) Math.toDegrees(Math.atan2(dH, dY));
        return new float[]{yaw, 90.0F - pitch};
    }

    public static float[] getRotations(Entity entity) {
        double diffY;
        if (entity == null) {
            return null;
        }
        double diffX = entity.posX - mc.player.posX;
        double diffZ = entity.posZ - mc.player.posZ;
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase elb = (EntityLivingBase)entity;
            diffY = elb.posY + ((double)elb.getEyeHeight() - 0.4) - (mc.player.posY + (double)mc.player.getEyeHeight());
        } else {
            diffY=0;

            Field fBB = null;
            try {
                fBB = Entity.class.getDeclaredField("boundingBox");
                fBB.setAccessible(true);
                AxisAlignedBB ebb= ((AxisAlignedBB)fBB.get(entity));
                diffY = (ebb.minY + ebb.maxY) / 2.0 - (mc.player.posY + (double)mc.player.getEyeHeight());

                fBB.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        float pitch = (float)(- Math.atan2(diffY, dist) * 180.0 / 3.141592653589793);
        return new float[]{yaw, pitch};
    }


/*
    public static float[] getRotations(Entity entity) {
        double diffY;
        if (entity == null) {
            return null;
        }
        double diffX = entity.posX - mc.player.posX;
        double diffZ = entity.posZ - mc.player.posZ;
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase elb = (EntityLivingBase)entity;
            diffY = elb.posY + ((double)elb.getEyeHeight() - 0.4) - (mc.player.posY + (double)mc.player.getEyeHeight());
        } else {
            diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0 - (mc.player.posY + (double)mc.player.getEyeHeight());
        }
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        float pitch = (float)(- Math.atan2(diffY, dist) * 180.0 / 3.141592653589793);
        return new float[]{yaw, pitch};
    }*/

    public static float getDistanceBetweenAngles(float angle1, float angle2) {
        float angle3 = Math.abs(angle1 - angle2) % 360.0f;
        if (angle3 > 180.0f) {
            angle3 = 0.0f;
        }
        return angle3;
    }
/*
    public static float[] grabBlockRotations(BlockPos pos) {
        return RotationUtil.getVecRotation(mc.player.getPositionVector().addVector(0.0, mc.player.getEyeHeight(), 0.0), new Vec3d((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5));
    }

    public static float[] getVecRotation(Vec3d position) {
        return RotationUtil.getVecRotation(mc.player.getPositionVector().addVector(0.0, mc.player.getEyeHeight(), 0.0), position);
    }

    public static float[] getVecRotation(Vec3d origin, Vec3d position) {
        Vec3d difference = position.subtract(origin);
        double distance = difference.flat().lengthVector();
        float yaw = (float)Math.toDegrees(Math.atan2(difference.z, difference.x)) - 90.0f;
        float pitch = (float)(- Math.toDegrees(Math.atan2(difference.y, distance)));
        return new float[]{yaw, pitch};
    }
*/
    public static int wrapAngleToDirection(float yaw, int zones) {
        int angle = (int)((double)(yaw + (float)(360 / (2 * zones))) + 0.5) % 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle / (360 / zones);
    }

	public static float getYawChangeGiven(double posX, double posZ, double posX2) {
		// TODO Auto-generated method stub
		return 0;
	}

    public static float[] getRotationFromPosition(double x, double z, double y) {
        Minecraft.getMinecraft();
        double xDiff = x - mc.player.posX;
        Minecraft.getMinecraft();
        double zDiff = z - mc.player.posZ;
        Minecraft.getMinecraft();
        double yDiff = y - mc.player.posY - 1.2D;
        double dist = (double)MathHelper.sqrt(xDiff * xDiff + zDiff * zDiff);
        float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float)(-(Math.atan2(yDiff, dist) * 180.0D / 3.141592653589793D));
        return new float[]{yaw, pitch};
    }

    static float cacheyaw;
    static float cachepitch;

    public static void setYaw(float yaw){
        cacheyaw =mc.player.rotationYaw;
        mc.player.rotationYaw=yaw;
    }

    public static void setCYaw(){
        mc.player.rotationYaw=cacheyaw;
    }
    public static void setPitch(float pitch){
        cachepitch =mc.player.rotationPitch;
        mc.player.rotationPitch=pitch;

    }
    public static void setCPitch(){
        mc.player.rotationPitch=cachepitch;
    }
    public static float[] getRotation(Entity entity) {
        double xPos = entity.posX - mc.player.posX;
        double zPos = entity.posZ - mc.player.posZ;
        double yPos = entity.posY - mc.player.posY;
        double dist = MathHelper.sqrt(xPos * xPos + zPos * zPos);
        float yaw = (float) (Math.atan2(zPos, xPos) * 180D / 3.1415926535897932384626433832795028841971693993751058209749445923078164D) - 90.0F;
        float pitch = (float) -(Math.atan2(yPos, dist) * 180D/ 3.1415926535897932384626433832795028841971693993751058209749445923078164D) ;
        return new float[]{yaw, pitch};
	}


}

