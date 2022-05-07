package rbq.wtf.lycoris.client.module.Modules.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventPacket;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;

public class Velocity extends Module {
    Minecraft mc = Minecraft.getMinecraft();
    public Velocity (){
        super("Velocity", ModuleCategory.Player,0);
    }
    @EventTarget
    public void onPacket(EventPacket event){

        if (0 < mc.player.hurtTime) {
            if (event.getPacket() instanceof SPacketEntityVelocity) {
                if (0 < mc.player.hurtTime) {
                    EntityPlayerSP player = mc.player;
                    player.posY += 0.11451419198;
                    player.motionX = 0;
                    player.motionY = 0;
                    player.motionZ = 0;
                }
            }
        }
    }
}
