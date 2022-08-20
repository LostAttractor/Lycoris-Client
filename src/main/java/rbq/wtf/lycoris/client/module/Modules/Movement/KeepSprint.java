package rbq.wtf.lycoris.client.module.Modules.Movement;


import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventMotionUpdate;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.MovementUtils;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.potion.Potion;

public class KeepSprint extends Module {

    public final BooleanValue noDelay = new BooleanValue("NoDelay", true, this);
    public final BooleanValue allDirections = new BooleanValue("All Directions", false, this);
    public final BooleanValue blindness = new BooleanValue("Blindness Check", true, this);
    public final BooleanValue food = new BooleanValue("FoodStats Check", true, this);

    public KeepSprint() {
        super("KeepSprint", ModuleCategory.Movement, 0);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }


    // 应当使用EventMove，但是暂时还没想好该事件如何实现
    @EventTarget
    public void EventMotionUpdate(EventMotionUpdate e) {
        if (e.isPre()) {
            if (!MovementUtils.isMoving() || mc.getPlayer().isSneaking() ||
                    (blindness.get() && mc.getPlayer().getEntityPlayerInstance().getEntityLivingBaseInstance().isPotionActive(Potion.getBlindness())) ||
                    (food.get() && !(mc.getPlayer().getEntityPlayerInstance().getFoodStats().getFoodLevel() > 6.0F || mc.getPlayer().getEntityPlayerInstance().getCapabilities().isAllowFlying()))
                    /*|| (checkServerSide.get() && (mc.thePlayer.onGround || !checkServerSideGround.get())
                    && !allDirectionsValue.get() && RotationUtils.targetRotation != null &&
                    RotationUtils.getRotationDifference(new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch)) > 30)*/) {
                mc.getPlayer().setSprinting(false);
                //Logger.debug("Set Sprint false", "SprintModule");
            } else if (allDirections.get() || mc.getPlayer().getMovementInput().getMoveForward() >= 0.8F) {
                mc.getPlayer().setSprinting(true);
                //Logger.debug("Set Sprint", "SprintModule");
            }
            if (noDelay.get()) {
                mc.getPlayer().setSprintingTicksLeft(0);
            }
        }
    }
}
