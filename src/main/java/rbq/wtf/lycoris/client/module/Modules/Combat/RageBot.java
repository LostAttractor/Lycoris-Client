package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Utils;
import rbq.wtf.lycoris.client.utils.ValidUtils;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.lang.reflect.Method;
import java.util.Objects;

public class RageBot extends Module {

    public ModeValue priority;
    public BooleanValue walls;
    public BooleanValue invisibles;
    public BooleanValue mobs;
    public BooleanValue players;
    public BooleanValue onclick;

    public NumberValue yaw;
    public NumberValue pitch;
    public NumberValue range;
    public NumberValue FOV;

    public static Minecraft mc = Minecraft.getMinecraft();
    public RageBot() {
        super("RageBot",ModuleCategory.Combat, 0);
        priority = new ModeValue("Priority", new String[]{"Closest","Health"},0,1);
        walls = new BooleanValue("ThroughWalls", false,this);
        onclick = new BooleanValue("Click", true,this);
        invisibles = new BooleanValue("Invisibles", false,this);
        players = new BooleanValue("Players", false,this);
        mobs = new BooleanValue("Mobs", false,this);
        yaw = new NumberValue("Yaw", 15.0D, 0D, 50D,0.1D, this);
        pitch = new NumberValue("Pitch", 15.0D, 0D, 50D,1D,this);
        range = new NumberValue("Range", 4.7D, 0.1D, 50D,1D,this);
        FOV = new NumberValue("FOV", 90D, 1D, 180D,1D,this);
        this.addModeValue(priority);
        this.addBooleanValue(onclick);
        this.addBooleanValue(walls);
        this.addBooleanValue(invisibles);
        this.addBooleanValue(players);
        this.addBooleanValue(mobs);
        this.addNumberValue(yaw);
        this.addNumberValue(pitch);
        this.addNumberValue(range);
        this.addNumberValue(FOV);
    }

    public boolean check(EntityLivingBase entity) {
        if(entity instanceof EntityArmorStand) { return false; }
        if(entity == Wrapper.INSTANCE.player()) { return false; }
        if(entity.isDead) { return false; }
        if(invisibles.getValue()) {
            if (!ValidUtils.isInvisible(entity)) {
                return false;
            }
        }
        if(mobs.getValue()) {
            if (!ValidUtils.isMob(entity)) {
                return false;
            }
        }
        if(players.getValue()) {
            if (!ValidUtils.isPlayer(entity)) {
                return false;
            }
        }
        if(ValidUtils.isBot(entity)) { return false; }
        if(!ValidUtils.isInAttackFOV(entity, FOV.getValue().intValue())) { return false; }
        if(!ValidUtils.isInAttackRange(entity, range.getValue().floatValue())) { return false; }
        if(!ValidUtils.isTeam(entity)) { return false; }
        if(!ValidUtils.pingCheck(entity)) { return false; }
        if(!this.walls.getValue()) { if(!Wrapper.INSTANCE.player().canEntityBeSeen(entity)) { return false; } }
        return true;
    }



    @EventTarget
    public void onClientTick(EventClientTick event) {
        for (Object object : Utils.getEntityList()) {
            if(!(object instanceof EntityLivingBase)) continue;
            EntityLivingBase entity = (EntityLivingBase) object;
            if(!check(entity)) continue;
            try {
                Class clazz = Class.forName("com.trychen.clay.core.weapon.player.ShootHelper");
                Method cs = clazz.getMethod("cs",float.class,float.class);
                cs.invoke(null , getPitchChangeToEntity(entity),getYawChangeToEntity(entity));
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }
    }

    public static float getYawChangeToEntity(Entity entity) {
        double deltaX = entity.posX - mc.player.posX;
        double deltaZ = entity.posZ - mc.player.posZ;
        double yawToEntity;
        if ((deltaZ < 0.0D) && (deltaX < 0.0D)) {
            yawToEntity = 90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
        } else {
            if ((deltaZ < 0.0D) && (deltaX > 0.0D)) {
                yawToEntity = -90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
            } else {
                yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
            }
        }
        return (float) MathHelper.wrapDegrees(yawToEntity);
    }
    public static float getPitchChangeToEntity(Entity entity) {
        double deltaX = entity.posX - mc.player.posX;
        double deltaZ = entity.posZ - mc.player.posZ;
        double deltaY = entity.posY - 1.6D + entity.getEyeHeight() - 0.4D - mc.player.posY;
        double distanceXZ = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));

        return MathHelper.wrapDegrees((float) pitchToEntity);
    }
}
