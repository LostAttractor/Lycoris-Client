package rbq.wtf.lycoris.client.utils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.Modules.Combat.AntiBot;
import rbq.wtf.lycoris.client.module.Modules.World.Teams;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.Objects;

public class ValidUtils {

    public static boolean isLowHealth(EntityLivingBase entity, EntityLivingBase entityPriority) {
        return entityPriority == null || entity.getHealth() < entityPriority.getHealth();
    }

    public static boolean isClosest(EntityLivingBase entity, EntityLivingBase entityPriority) {
        return entityPriority == null || Wrapper.INSTANCE.player().getDistance(entity) < Wrapper.INSTANCE.player().getDistance(entityPriority);
    }

    public static boolean isInAttackFOV(EntityLivingBase entity, int fov) {
        return Utils.getDistanceFromMouse(entity) <= fov;
    }

    public static boolean isInAttackRange(EntityLivingBase entity, float range) {
        return entity.getDistance(Wrapper.INSTANCE.player()) <= range;
    }


    public static boolean pingCheck(EntityLivingBase entity) {
        Module antibot = LycorisClient.instance.getModuleManager().getModuleByName("AntiBot");
        if(antibot.isState() && AntiBot.ifPing.getValue() && entity instanceof EntityPlayer) {
            Objects.requireNonNull(Wrapper.INSTANCE.mc().getConnection()).getPlayerInfo(entity.getUniqueID());
            if (Objects.requireNonNull(Wrapper.INSTANCE.mc().getConnection()).getPlayerInfo(entity.getUniqueID()).getResponseTime() > 5) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean isBot(EntityLivingBase entity) {
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            Module module = LycorisClient.instance.getModuleManager().getModuleByName("AntiBot");
            return module.isState() && AntiBot.isBot(player);
        }
        return false;
    }
    public static boolean isPlayer(EntityLivingBase e) {
        if( e instanceof EntityPlayer) {
            return true;
        }
        return false;
    }
    public static boolean isMob(EntityLivingBase e) {
        if(e instanceof EntityPlayer) {
            return false;
        }
        return true;
    }
    public static boolean isInvisible(EntityLivingBase entity) {
        if(entity.isInvisible()) {
            return true;
        }
        return false;
    }
    public static boolean isTeam(EntityLivingBase entity) {
        Module module = LycorisClient.instance.getModuleManager().getModuleByName("Teams");
        if(module.isState()) {
            if(entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if(Teams.mode.getCurrentSelectionName().equals("Base")) {
                    if(player.getTeam() != null && Wrapper.INSTANCE.player().getTeam() != null) {
                        if(player.getTeam().isSameTeam(Wrapper.INSTANCE.player().getTeam())){
                            return false;
                        }
                    }
                }
                if(Teams.mode.getCurrentSelectionName().equals("ArmorColor")) {
                    if(!Utils.checkEnemyColor(player)) {
                        return false;
                    }
                }
                if(Teams.mode.getCurrentSelectionName().equals("NameColor")) {
                    if(!Utils.checkEnemyNameColor(player)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
