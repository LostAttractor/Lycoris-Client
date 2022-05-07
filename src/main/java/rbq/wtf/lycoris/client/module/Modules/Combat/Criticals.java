package rbq.wtf.lycoris.client.module.Modules.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventPacket;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.utils.Render.ChatUtils;
import rbq.wtf.lycoris.client.utils.TimerUtils;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.Objects;

public class Criticals extends Module {
    public ModeValue mode = new ModeValue("Mode",new String[]{"Packet","Jump","HYT"},0,2);
    TimerUtils timer;
    Minecraft mc = Minecraft.getMinecraft();
    int index = 0;
    boolean cancelSomePackets;
    public Criticals() {
        super("Criticals",ModuleCategory.Combat,0);
        this.addModeValue(mode);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        index = 0;
    }

    @EventTarget
    public boolean onPacket(EventPacket event) {

        if(Wrapper.INSTANCE.player().onGround) {
            if(event.getSide() == Connection.Side.OUT) {
                if(event.getPacket() instanceof CPacketUseEntity) {
                    CPacketUseEntity attack = (CPacketUseEntity) event.getPacket();
                    if(attack.getAction() == CPacketUseEntity.Action.ATTACK) {
                        if(mode.isCurrentMode("Packet")) {
                            if(Wrapper.INSTANCE.player().collidedVertically && this.timer.isDelay(500)) {
                                Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Position(
                                        Wrapper.INSTANCE.player().posX,
                                        Wrapper.INSTANCE.player().posY + 0.0627,
                                        Wrapper.INSTANCE.player().posZ, false));
                                Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Position(
                                        Wrapper.INSTANCE.player().posX,
                                        Wrapper.INSTANCE.player().posY,
                                        Wrapper.INSTANCE.player().posZ, false));
                                Entity entity = attack.getEntityFromWorld(Wrapper.INSTANCE.world());
                                if(entity != null) {
                                    Wrapper.INSTANCE.player().onCriticalHit(entity);
                                }
                                this.timer.setLastMS();
                                this.cancelSomePackets = true;
                            }
                        }
                        if (mode.isCurrentMode("Jump"))
                        {
                            if(canJump()) {
                                Wrapper.INSTANCE.player().jump();
                            }
                        }
                        if (mode.isCurrentMode("HYT")  && this.timer.isDelay(500)) {
                            Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.065600045, mc.player.posZ, true));
                            Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));

                            if (!Objects.requireNonNull(((CPacketUseEntity) event.getPacket()).getEntityFromWorld(mc.world)).isDead || Objects.requireNonNull(((CPacketUseEntity) event.getPacket()).getEntityFromWorld(mc.world)).getDistance(mc.player)> 5.0  ) {
                                mc.player.motionY = 0.0000000001;
                            }
                            Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Position(mc.player.posX,
                                    mc.player.posY + 0.001, mc.player.posZ, true));
                        }
                    }
                } else if (mode.isCurrentMode("Packet") && event.getPacket() instanceof CPacketPlayer) {
                    if (cancelSomePackets) {
                        cancelSomePackets = false;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    boolean canJump() {
        if(Wrapper.INSTANCE.player().isOnLadder()) {
            return false;
        }
        if(Wrapper.INSTANCE.player().isInWater()) {
            return false;
        }
        if(Wrapper.INSTANCE.player().isInLava()) {
            return false;
        }
        if(Wrapper.INSTANCE.player().isSneaking()) {
            return false;
        }
        if(Wrapper.INSTANCE.player().isRiding()) {
            return false;
        }
        if(Wrapper.INSTANCE.player().isPotionActive(MobEffects.BLINDNESS)) {
            return false;
        }
        return true;
    }
}
