package rbq.wtf.lycoris.client.module.Modules.Render;

import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventRenderGameOverlay;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.utils.Render.Palette;
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
        rainbowMode = new ModeValue("Rainbow Mode",new String[]{"Glow", "RainBow", "None","StaticRainbow"}, 0, 3);
        this.addModeValue(rainbowMode);
        waterMark = new BooleanValue("WaterMark",true,this);
        this.addBooleanValue(waterMark);
        arrayList = new BooleanValue("ArrayList",true,this);
        this.addBooleanValue(arrayList);
        rainbowSpeed = new NumberValue("RainbowSpeed",100.0F,0.0F,2000.0F,0.1F);
        this.addNumberValue(rainbowSpeed);
        colorValue = new ColorValue("Theme Color",new Color(255, 139, 214));
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
        long rnbw = 0L;
        if (waterMark.getValue()){
            switch (rainbowMode.getCurrentSelectionName()){
                case "Glow" :
                    FontLoaders.default25.drawStringWithShadow(hudText.getValue(),
                            5,
                            5,
                            Palette.fade(colorValue.getValue()).getRGB());
                    break;
                case "RainBow":
                    FontLoaders.default25.drawStringWithShadow(hudText.getValue(),
                            5,
                            5,
                            Color.HSBtoRGB((float)((System.currentTimeMillis() + rnbw) % 5000L) / 4750.0f, 0.8f, 0.8f));
                    rnbw -= 100L;
                    break;
                default :
                    FontLoaders.default25.drawStringWithShadow(hudText.getValue(),
                            5,
                            5,
                            colorValue.getValue().getRGB());
                    break;
            }
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
                switch (rainbowMode.getCurrentSelectionName()){
                    case "Glow" :
                        FontLoaders.default20.drawStringWithShadow(m.getName(),
                                e.getEvent().getResolution().getScaledWidth() - FontLoaders.default20.getStringWidth(m.getName()) - 5 ,
                                posY + 5,
                                Palette.fade(colorValue.getValue(),100,sorted.indexOf(m)  * 2 + 10).getRGB()
                        );
                        break;
                    case "RainBow" :
                        FontLoaders.default20.drawStringWithShadow(m.getName(),
                                e.getEvent().getResolution().getScaledWidth() - FontLoaders.default20.getStringWidth(m.getName()) - 5 ,
                                posY + 5,
                                Color.HSBtoRGB((float)((System.currentTimeMillis() + rnbw) % 5000L) / 4750.0f, 0.8f, 0.8f));
                        rnbw -= 100L;
                        break;
                    default :
                        FontLoaders.default20.drawStringWithShadow(m.getName(),
                                e.getEvent().getResolution().getScaledWidth() - FontLoaders.default20.getStringWidth(m.getName()) - 5 ,
                                posY + 5,
                                colorValue.getValue().getRGB()
                        );
                        break;
                }

                posY = posY + FontLoaders.default16.getStringHeight(m.getName()) + 4;
            }

        }
    }
    public static int Rainbow(int i, int tick) {
        return HUD.RainbowCalc((long)tick % 110000000000L, 1.0f,i).getRGB();
    }
    private static Color RainbowCalc(long tick, float bright,int i) {
        float hue = (float)(System.nanoTime() + tick) / 3.0000001E10f % 2.0f;
        long rgb = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue-i, 0.9f, 0.9f)), 16);
        Color col = new Color((int)rgb);
        return new Color((float)col.getRed() / 255.0f * bright, (float)col.getGreen() / 255.0f * bright, (float)col.getBlue() / 255.0f * bright, (float)col.getAlpha() / 255.0f);
    }
}
