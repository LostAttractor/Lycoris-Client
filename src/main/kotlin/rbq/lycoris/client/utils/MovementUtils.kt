package rbq.lycoris.client.utils

object MovementUtils : MinecraftInstance() {
    val isMoving: Boolean
        get() = mc.player.movementInput.moveForward != 0f || mc.player.movementInput.moveStrafe != 0f
}