package rbq.wtf.lycoris.client.module.Modules.Movement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.event.events.EventPlayerTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Timer;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.Objects;

public class Fly extends Module {
    Timer kickTimer;
    Minecraft mc = Minecraft.getMinecraft();

    public static  ModeValue mode;
    private  double moveSpeed;

    public static NumberValue speed;
    public static BooleanValue endStop;

    Timer timer;

    public Fly() {
        super("Fly",ModuleCategory.Movement,0 );

        speed = new NumberValue("Speed", 1.0, 1.0, 100.0, 1.0);
        this.addNumberValue(speed);
        mode = new ModeValue("Mode",new String[]{"Vanilla", "Packet", "Motion","TEST"}, 0, 3);
        this.addModeValue(mode);
        endStop=new BooleanValue("Stop When Disabled",true,this);
        this.addBooleanValue(endStop);
        this.timer = new Timer();
        this.kickTimer = new Timer();
    
    }

    @EventTarget
    public void onUpdate(EventPlayerTick event) {
        this.mc.player.motionY = 0.0;
        if (Objects.equals(mode.getCurrentSelectionName(), "Vanilla")) {

            this.moveSpeed = this.getBaseMoveSpeed();
            double forward = this.mc.player.moveForward;
            double strafing = this.mc.player.moveStrafing;
            float yaw = this.mc.player.rotationYaw;

            if (forward == 0.0 && strafing == 0.0) {
                this.mc.player.motionX = 0.0;
                this.mc.player.motionZ = 0.0;
            } else {
                if (forward != 0.0) {
                    if (strafing > 0.0) {
                        yaw += (float)(forward > 0.0 ? -45 : 45);
                    } else if (strafing < 0.0) {
                        yaw += (float)(forward > 0.0 ? 45 : -45);
                    }
                    strafing = 0.0;
                    if (forward > 0.0) {
                        forward = 1.0;
                    } else if (forward < 0.0) {
                        forward = -1.0;
                    }
                }
                this.mc.player.motionX = forward * (this.moveSpeed * (speed.getValue() / 2.0)) * Math.cos(Math.toRadians(yaw + 90.0f)) + strafing * this.moveSpeed * Math.sin(Math.toRadians(yaw + 90.0f));
                this.mc.player.motionZ = forward * (this.moveSpeed * (speed.getValue() / 2.0)) * Math.sin(Math.toRadians(yaw + 90.0f)) - strafing * this.moveSpeed * Math.cos(Math.toRadians(yaw + 90.0f));
            }

            if (Keyboard.getEventKey() ==  mc.gameSettings.keyBindSneak.getKeyCode() && Keyboard.getEventKeyState()) {
                mc.player.motionY -= 1;
            } else if (Keyboard.getEventKey() == mc.gameSettings.keyBindJump.getKeyCode()&& Keyboard.getEventKeyState()) {
                mc.player.motionY += 1;
            }


        }
    }


    private double getBaseMoveSpeed() {
        double v = 0.2873;
        if (this.mc.world != null && this.mc.player.isPotionActive(Potion.getPotionById(1))) {
            int lllIIlIllllIlll = this.mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier();
            v *= 1.0 + 0.2 * (double)(lllIIlIllllIlll + 1);
        }
        if(mode.getCurrentSelectionName().equals("TEST")){
            return v * 0.99*speed.getValue();
        }
        return v * 0.99;
    }


    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.mc.player.motionX = 0;
        this.mc.player.motionZ = 0;
    }
}
