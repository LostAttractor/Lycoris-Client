package rbq.wtf.lycoris.client.module.Modules.Render;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRender2D;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HUD extends Module {
    private static BooleanValue waterMark;
    private static BooleanValue arrayList;
    private static ModeValue rainbowMode;
    private static NumberValue rainbowSpeed;
    public HUD() {
        super("HUD", ModuleCategory.Render,0);
        rainbowMode = new ModeValue("Rainbow Mode",new String[]{"Rainbow", "Astolfo", "Static","StaticRainbow"}, 0, 3);
        this.addModeValue(rainbowMode);
        waterMark = new BooleanValue("WaterMark",true,this);
        this.addBooleanValue(waterMark);
        arrayList = new BooleanValue("ArrayList",true,this);
        this.addBooleanValue(arrayList);
        rainbowSpeed = new NumberValue("RainbowSpeed",100.0F,0.0F,2000.0F,0.1F);
        this.addNumberValue(rainbowSpeed);
    }

    @Override
    public void onEnable() {
        System.out.println("Enable");
    }

    @EventTarget
    public void onRender2D(EventRender2D e){
        if (waterMark.getValue()){
            FontLoaders.default25.drawStringWithShadow("Dimples.love",
                    5,
                    5,
                    new Color(0,200,100).getRGB());
        }
        if (arrayList.getValue()) {
            ArrayList<Module> sorted = new ArrayList<>();
            for (Module m : LycorisClient.instance.getModuleManager().getModules()) {
                if (!m.isState()) continue;
                sorted.add(m);
            }
            sorted.sort(Comparator.comparingInt(mm -> -(FontLoaders.default16.getStringWidth(mm.getName()))));
            float posY = 0;
            for (Module m : sorted) {
//                System.out.println(e.getScaledResolution().getScaledHeight());
                FontLoaders.default20.drawStringWithShadow(m.getName(),
                        e.getScaledResolution().getScaledWidth() - FontLoaders.default20.getStringWidth(m.getName()) - 5 ,
                        posY + 5,
                        new Color(0,200,100).getRGB()
                );
                posY = posY + FontLoaders.default16.getStringHeight(m.getName()) + 4;
            }

        }
    }

}
