package rbq.wtf.lycoris.client.module.Modules.Movement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.MovementInput;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.FreeCamPlayer;

public class FreeCam extends Module {
    private EntityPlayerSP player = null;
    private FreeCamPlayer ghostPlayer = null;
    public FreeCam(){
        super("FreeCam", ModuleCategory.Movement,0);
    }
    @Override
    public void onEnable() {
        this.player = Minecraft.getMinecraft().player;
        final WorldClient world = Minecraft.getMinecraft().world;

        this.ghostPlayer = new FreeCamPlayer(world, this.player.getGameProfile(), this.player.movementInput);
        this.ghostPlayer.copyLocationAndAnglesFrom(this.player);

        MovementInput movementInput = new MovementInput();
        movementInput.jump = false;
        movementInput.sneak = false;
        movementInput.moveStrafe = 0.0F;
        movementInput.moveForward = 0.0F;
        this.player.movementInput = movementInput;

        world.spawnEntity(this.ghostPlayer);
        Minecraft.getMinecraft().setRenderViewEntity(this.ghostPlayer);
    }

    @Override
    public void onDisable() {
        if(this.player == Minecraft.getMinecraft().player) {
            this.player.movementInput = this.ghostPlayer.movementInput;
        } else {
            this.player = Minecraft.getMinecraft().player;
        }

        final WorldClient world = Minecraft.getMinecraft().world;

        world.removeEntity(this.ghostPlayer);
        Minecraft.getMinecraft().setRenderViewEntity(this.player);
        Minecraft.getMinecraft().renderGlobal.loadRenderers();

        this.player = null;
        this.ghostPlayer = null;
    }
}
