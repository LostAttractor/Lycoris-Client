package rbq.wtf.lycoris.client.utils;

import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

public class MovementUtils {

    private static final Minecraft mc = Client.instance.mc;

    public static boolean isMoving() {
        return mc.getPlayer().getMovementInput().getMoveForward() != 0 || mc.getPlayer().getMovementInput().getMoveStrafe() != 0;
    }
}
