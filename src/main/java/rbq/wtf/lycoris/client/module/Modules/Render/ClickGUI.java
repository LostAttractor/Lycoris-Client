package rbq.wtf.lycoris.client.module.Modules.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainScreen;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.gui.ClickGUI.NMSL;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Connection;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import javax.swing.*;

public class ClickGUI extends Module {

    public ClickGUI (){
        super("ClickGUI", ModuleCategory.Render,210);

    }
    public static boolean initialized;
    @Override
    public void onEnable() {
        if (!initialized) {
            new Connection();
            initialized = true;
        }
        Wrapper.displayGuiScreen(LycorisClient.instance.getNewClickGUI());
        this.toggle();
    }
}
