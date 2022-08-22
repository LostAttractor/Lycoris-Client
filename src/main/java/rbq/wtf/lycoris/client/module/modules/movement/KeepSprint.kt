package rbq.wtf.lycoris.client.module.modules.movement

import rbq.wtf.lycoris.client.event.EventTarget
import rbq.wtf.lycoris.client.event.UpdateEvent
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.utils.MovementUtils
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.potion.Potion

@ModuleInfo(
    name = "KeepSprint",
    description = "Automatically sprints all the time.",
    category = ModuleCategory.Movement
)
class KeepSprint : Module() {
    val noDelay = BooleanValue("NoDelay", true)
    val allDirections = BooleanValue("All Directions", false)
    val blindness = BooleanValue("Blindness Check", true)
    val food = BooleanValue("FoodStats Check", true)

    @EventTarget
    fun onUpdate(e: UpdateEvent) {
        if (!MovementUtils.isMoving() || mc.player.isSneaking || blindness.get() && mc.player.isPotionActive(
                Potion.getBlindness()
            ) || food.get() && !(mc.player.foodStats.foodLevel > 6.0f || mc.player.capabilities.isAllowFlying) /*|| (checkServerSide.get() && (mc.thePlayer.onGround || !checkServerSideGround.get())
                && !allDirectionsValue.get() && RotationUtils.targetRotation != null &&
                RotationUtils.getRotationDifference(new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch)) > 30)*/) {
            mc.player.isSprinting = false
            //Logger.debug("Set Sprint false", "SprintModule");
        } else if (allDirections.get() || mc.player.movementInput.moveForward >= 0.8f) {
            mc.player.isSprinting = true
            //Logger.debug("Set Sprint", "SprintModule");
        }
        if (noDelay.get()) {
            mc.player.setSprintingTicksLeft(0)
        }
    }
}