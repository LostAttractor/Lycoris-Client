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
    private val noDelay = BooleanValue("NoDelay", true)

    //private val allDirections = BooleanValue("All Directions", false)
    private val blindness = BooleanValue("Blindness Check", true)
    private val food = BooleanValue("FoodStats Check", true)

    private val checkServerSide = BooleanValue("CheckServerSide", false)
    private val checkServerSideGround = BooleanValue("CheckServerSideOnlyGround", false)

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        if (!MovementUtils.isMoving || mc.player.isSneaking || blindness.get() && mc.player.isPotionActive(Potion.blindness) || food.get() && !(mc.player.foodStats.foodLevel > 6.0f
                    || mc.player.capabilities.isAllowFlying) || (checkServerSide.get() && (mc.player.isOnGround || !checkServerSideGround.get())
                    /*&& !allDirections.get()*/) /*&& RotationUtils.targetRotation != null && RotationUtils.getRotationDifference(Rotation(mc.player.rotationYaw, mc.player.rotationPitch)) > 30 */
        ) {
            mc.player.setSprinting(false)
        } else if (/*allDirections.get() || */mc.player.movementInput.moveForward >= 0.8f) mc.player.setSprinting(true)
        if (noDelay.get()) mc.player.setSprintingTicksLeft(0)
    }
}