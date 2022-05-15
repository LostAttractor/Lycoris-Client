package rbq.wtf.lycoris.client.utils;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FreeCamPlayer extends EntityPlayer {
    private final static Minecraft minecraft = Minecraft.getMinecraft();
    public final MovementInput movementInput;

    public FreeCamPlayer(final World worldIn, final GameProfile gameProfile, MovementInput movementInput) {
        super(worldIn, gameProfile);
        this.movementInput = movementInput;
    }

    @Override
    public void onLivingUpdate() {
        final EntityPlayerSP player = FreeCamPlayer.minecraft.player;
        if(player != null) {
            // 刷新键盘输入
            this.movementInput.updatePlayerMoveState();

            // 防止原 Player 移动
            player.motionX = 0.0D;
            player.motionY = 0.0D;
            player.motionZ = 0.0D;

            // 刷新角度 物品栏
            this.setHealth(player.getHealth());
            this.foodStats = player.getFoodStats();
            this.rotationPitch = player.rotationPitch;
            this.rotationYaw = player.rotationYaw;
            this.rotationYawHead = player.rotationYawHead;
            this.inventory = player.inventory;


            // 刷新速度
            float flySpeed = this.capabilities.getFlySpeed();
            if(FreeCamPlayer.minecraft.gameSettings.keyBindSprint.isKeyDown()) {
                flySpeed *= 1.8;
            }

            final float strafe = this.movementInput.moveStrafe * flySpeed * 6.0F;
            final float forward = this.movementInput.moveForward * flySpeed * 6.0F;
            final float sin = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
            final float cos = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);

            // 运动量计算
            final double motionX = strafe * cos - forward * sin;
            double motionY = 0.0D;
            final double motionZ = forward * cos + strafe * sin;
            if(this.movementInput.jump) {
                motionY = flySpeed * 5.0F;
            } else if(this.movementInput.sneak) {
                motionY = flySpeed * -5.0F;
            }

            // 移动实体
            this.posX += motionX;
            this.posY += motionY;
            this.posZ += motionZ;
        }
    }

    @Override
    public boolean isSpectator() {
        return true;
    }

    @Override
    public boolean isCreative() {
        return false;
    }
}