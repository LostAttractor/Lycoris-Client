package rbq.wtf.lycoris.client.module.Modules.Movement;

import net.minecraft.client.Minecraft;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventPlayerTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;

public class KeepSprint extends Module {

    public KeepSprint() {
        super("KeepSprint",ModuleCategory.Movement,0);
    }

    @EventTarget
    public void onUpdate(EventPlayerTick eventPlayerTick) {
        if(!Minecraft.getMinecraft().player.isSprinting()) Minecraft.getMinecraft().player.setSprinting(true);

    }
}
