package rbq.wtf.lycoris.client.module.Modules.Render;

import net.minecraft.client.Minecraft;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;

public class ClickGUI extends Module {
    private static BooleanValue waterMark;
    private static BooleanValue arrayList;
    private static ModeValue rainbowMode;
    public ClickGUI (){
        super("ClickGUI", ModuleCategory.Render,54);
        rainbowMode = new ModeValue("Rainbow Mode",new String[]{"Rainbow", "Astolfo", "Static","StaticRainbow"}, 0, 3);
        this.addModeValue(rainbowMode);
        waterMark = new BooleanValue("WaterMark",true,this);
        this.addBooleanValue(waterMark);
        arrayList = new BooleanValue("ArrayList",true,this);
        this.addBooleanValue(arrayList);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen(LycorisClient.instance.getClickGUI());
        this.toggle();
    }
}
