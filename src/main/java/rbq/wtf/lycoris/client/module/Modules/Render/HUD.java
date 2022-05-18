package rbq.wtf.lycoris.client.module.Modules.Render;

import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRenderGameOverlay;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HUD extends Module {
    private static BooleanValue waterMark;
    private static BooleanValue arrayList;
    private static ModeValue rainbowMode;
    private static NumberValue rainbowSpeed;
    private static ColorValue colorValue;
    private static TextValue hudText;
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
        colorValue = new ColorValue("Theme Color",new Color(0,200,100));
        this.addColorValue(colorValue);
        hudText = new TextValue("HUD Title","Dimples.Love",this);
        this.addTextValue(hudText);
    }

    @Override
    public void onEnable() {
        System.out.println("Enable");
    }

    @EventTarget
    public void onRender2D(EventRenderGameOverlay e){
        if (waterMark.getValue()){
            FontLoaders.default25.drawStringWithShadow(hudText.getValue(),
                    5,
                    5,
                    colorValue.getValue().getRGB());
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
                        e.getEvent().getResolution().getScaledWidth() - FontLoaders.default20.getStringWidth(m.getName()) - 5 ,
                        posY + 5,
                        colorValue.getValue().getRGB()
                );
                posY = posY + FontLoaders.default16.getStringHeight(m.getName()) + 4;
            }

        }
    }

}
