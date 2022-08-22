package rbq.wtf.lycoris.client.utils;

public class MovementUtils extends MinecraftInstance {

    public static boolean isMoving() {
        return mc.getPlayer().getMovementInput().getMoveForward() != 0 || mc.getPlayer().getMovementInput().getMoveStrafe() != 0;
    }
}
