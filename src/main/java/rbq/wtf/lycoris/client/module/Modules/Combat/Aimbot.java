package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.common.gameevent.TickEvent;
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

import java.util.Objects;

public class Aimbot extends Module {
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

    public EntityLivingBase target;
    public Aimbot() {
        super("Aimbot", ModuleCategory.Combat, 0);
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
    @Override
    public void onDisable() {
        this.target = null;
        super.onDisable();
    }

    @EventTarget
    public void onClientTick(EventClientTick event) {
        updateTarget();
        if(onclick.getValue()) {
            if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
                Utils.assistFaceEntity(
                        this.target,
                        this.yaw.getValue(),
                        this.pitch.getValue());
            }
        } else {
            Utils.assistFaceEntity(
                    this.target,
                    this.yaw.getValue(),
                    this.pitch.getValue());
        }
        this.target = null;
    }

    void updateTarget(){
        for (Object object : Utils.getEntityList()) {
            if(!(object instanceof EntityLivingBase)) continue;
            EntityLivingBase entity = (EntityLivingBase) object;
            if(!check(entity)) continue;
            this.target = entity;
        }
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
        if(!isPriority(entity)) { return false; }
        if(!this.walls.getValue()) { if(!Wrapper.INSTANCE.player().canEntityBeSeen(entity)) { return false; } }
        return true;
    }

    boolean isPriority(EntityLivingBase entity) {
        return Objects.equals(priority.getCurrentSelectionName(), "Closest") && ValidUtils.isClosest(entity, target)
                || Objects.equals(priority.getCurrentSelectionName(), "Health") && ValidUtils.isLowHealth(entity, target);
    }

}
