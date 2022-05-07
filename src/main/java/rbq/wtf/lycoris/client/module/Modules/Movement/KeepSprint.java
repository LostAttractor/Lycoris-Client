package rbq.wtf.lycoris.client.module.Modules.Movement;

import net.minecraft.client.Minecraft;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;

public class KeepSprint extends Module {

    public KeepSprint() {
        super("KeepSprint",ModuleCategory.Movement,0);
    }
    @Override
    public void onEnable(){
        Minecraft.getMinecraft().player.sendChatMessage("Enable");
    }
    @Override
    public void onDisable(){
        Minecraft.getMinecraft().player.sendChatMessage("Disable");
    }
}
