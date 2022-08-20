import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

interface Event {

}

public class EntityPlayerSPTest2 extends Entity {
    public EntityPlayerSPTest2(World p_i1582_1_) {
        super(p_i1582_1_);
    }

    public void onUpdateWalkingPlayer() {
        EventManager.call(new EventMotionUpdate(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround, EventMotionUpdate.Type.PRE));
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }
}

class EventMotionUpdate implements Event {
    private double posX;
    private double lastPosX;
    private double posY;
    private double lastPosY;
    private double posZ;
    private double lastPosZ;
    private float yaw;
    private float lastYaw;
    private float pitch;
    private float lastPitch;
    private boolean onGround;
    private Type type;

    public EventMotionUpdate(double posX, double posY, double posZ, float yaw, float pitch, boolean onGround, Type type) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.type = type;
    }

    public boolean isPre() {
        return this.type == Type.PRE;
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getLastPosX() {
        return this.lastPosX;
    }

    public void setLastPosX(double lastPosX) {
        this.lastPosX = lastPosX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getLastPosY() {
        return this.lastPosY;
    }

    public void setLastPosY(double lastPosY) {
        this.lastPosY = lastPosY;
    }

    public double getPosZ() {
        return this.posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public double getLastPosZ() {
        return this.lastPosZ;
    }

    public void setLastPosZ(double lastPosZ) {
        this.lastPosZ = lastPosZ;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getLastYaw() {
        return this.lastYaw;
    }

    public void setLastYaw(float lastYaw) {
        this.lastYaw = lastYaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getLastPitch() {
        return this.lastPitch;
    }

    public void setLastPitch(float lastPitch) {
        this.lastPitch = lastPitch;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static enum Type {
        PRE,
        POST;

    }
}

final class EventManager {
    public static Event call(Event event) {
        return event;
    }
}