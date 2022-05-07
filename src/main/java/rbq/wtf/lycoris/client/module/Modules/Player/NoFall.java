package rbq.wtf.lycoris.client.module.Modules.Player;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.BlockUtils;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.Objects;

public class NoFall extends Module {
    public static ModeValue mode = new ModeValue("Mode",new String[]{"AAC5"}, 0, 0);
    public static NumberValue fallDistance = new NumberValue("FallDistance",3,1,6,1);
    public static NumberValue setFallDistance = new NumberValue("SetFallDistance",2,0,6,1);
    Minecraft mc = Minecraft.getMinecraft();
    boolean aac5Check;
    boolean aac5doFlag;
    int aac5Timer = 0;
    public NoFall(){
        super("NoFall", ModuleCategory.Player,0);
		this.addModeValue(mode);
        this.addNumberValue(fallDistance);
        this.addNumberValue(setFallDistance);
    }

    @Override
    public void onEnable() {
        this.aac5Check = false;
        this.aac5doFlag = false;
        this.aac5Timer = 0;
    }

    @EventTarget
    public void onUpdate(EventClientTick event) {
        if (mode.isCurrentMode("AAC5")){
            if (mc.player.fallDistance > fallDistance.getValue()) {
                mc.player.fallDistance = setFallDistance.getValue();
                Wrapper.setTickLength(mc, (float) (Wrapper.getTimer(mc).elapsedPartialTicks * 0.6));
                Objects.requireNonNull(this.mc.getConnection()).sendPacket(new CPacketPlayer(true));
            }
            if (mc.player.motionY == 0) {
                Wrapper.setTickLength(mc,1);
            }
//            Wrapper.setTickLength(Minecraft.getMinecraft(),aac5Timer);
//            Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.5, mc.player.posZ, true));

        }
    }
}
