package rbq.wtf.lycoris.client.module.Modules.Movement;

import net.minecraft.client.Minecraft;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventTick;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;

public class KeepSprint extends Module {

    public KeepSprint() {
        super("KeepSprint",ModuleCategory.Movement,0);
    }
    @Override
    public void onEnable(){
        Minecraft.getMinecraft().thePlayer.sendChatMessage("Enable");
    }
    @Override
    public void onDisable(){
        Minecraft.getMinecraft().thePlayer.sendChatMessage("Disable");
    }
}
