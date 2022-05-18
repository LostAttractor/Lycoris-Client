package rbq.wtf.lycoris.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.events.*;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

public class EventHandler {
    public static boolean typed = false;
    private boolean initialized = false;
    @SubscribeEvent
    public void onGuiContainer(GuiContainerEvent event) {
        EventManager.call(new EventGuiContainer(event));
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        EventManager.call(new EventGuiOpen(event));
    }

    @SubscribeEvent
    public void onMouse(MouseEvent event) {
        EventManager.call(new EventMouse(event));
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {

        if (Keyboard.getEventKey() == 0) {
            return;
        }
        if (!Keyboard.getEventKeyState()) {
            return;
        }
//        if (!typed) {
//            typed = true;
//            return;
//        }

        EventManager.call(new EventKey(Keyboard.getEventKey()));

        //typed = false;
    }


    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        EventManager.call(new EventCameraSetup(event));
    }

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EventManager.call(new EventItemPickup(event));
    }

    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        EventManager.call(new EventProjectileImpact(event));
    }


    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        EventManager.call(new EventAttackEntity(event));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        EventManager.call(new EventPlayerTick(e));
        if(mc.player==null){
            return;
        }

        EventPreUpdate pre = new EventPreUpdate(e.player.posX, e.player.posY, e.player.posZ, e.player.rotationYaw, e.player.rotationPitch, e.player.onGround);
        EventManager.call(pre);

        EventPostUpdate post2 = new EventPostUpdate(pre.pitch);
        EventManager.call(post2);



    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        EventManager.call(new EventClientTick(event));
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        EventManager.call(new EventLivingUpdate(event));
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        EventManager.call(new EventRenderWorldLast(event));
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        EventManager.call(new EventRenderGameOverlay(event));
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event){
        EventManager.call(new EventLeftClickBlock(event));
    }

    
}
