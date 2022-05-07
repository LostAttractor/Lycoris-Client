/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.EnumConnectionState
 *  net.minecraft.potion.Potion
 */
package rbq.wtf.lycoris.client.module.Modules.Movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventClientTick;
import rbq.wtf.lycoris.client.event.events.EventPlayerTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Utils;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

public class Speed extends Module {
    public Speed() {
        super("Speed",ModuleCategory.Movement,0);
    }

    @EventTarget
    public void onClientTick(EventClientTick event) {
        boolean boost = Math.abs(Wrapper.INSTANCE.player().rotationYawHead - Wrapper.INSTANCE.player().rotationYaw) < 90;

        if (Wrapper.INSTANCE.player().moveForward > 0 && Wrapper.INSTANCE.player().hurtTime < 5) {
            if (Wrapper.INSTANCE.player().onGround) {
            Wrapper.INSTANCE.player().jump();
                Wrapper.INSTANCE.player().motionY = 0.405;
                float f = Utils.getDirection();
                Wrapper.INSTANCE.player().motionX -= (double)(MathHelper.sin(f) * 0.2F);
                Wrapper.INSTANCE.player().motionZ += (double)(MathHelper.cos(f) * 0.2F);
            } else {
                double currentSpeed = Math.sqrt(Wrapper.INSTANCE.player().motionX * Wrapper.INSTANCE.player().motionX + Wrapper.INSTANCE.player().motionZ * Wrapper.INSTANCE.player().motionZ);
                double speed = boost ? 1.0064 : 1.001;

                double direction = Utils.getDirection();

                Wrapper.INSTANCE.player().motionX = -Math.sin(direction) * speed * currentSpeed;
                Wrapper.INSTANCE.player().motionZ = Math.cos(direction) * speed * currentSpeed;
            }
        }

    }
}

