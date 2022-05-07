package rbq.wtf.lycoris.client.module.Modules.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventLivingUpdate;
import rbq.wtf.lycoris.client.event.events.EventPlayerTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

public class NoSlow extends Module {
    Minecraft mc = Minecraft.getMinecraft();
    public NoSlow(){
        super("NoSlow", ModuleCategory.Player,0);
    }
    @EventTarget
    public void onUpdate(EventLivingUpdate e){
        if (isBlocking()){
            Wrapper.INSTANCE.sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(-1,-1,-1), EnumFacing.UP, EnumHand.MAIN_HAND,0,0,0));
        }
    }
    public boolean isBlocking(){
        return mc.player.isActiveItemStackBlocking();
    }
}
