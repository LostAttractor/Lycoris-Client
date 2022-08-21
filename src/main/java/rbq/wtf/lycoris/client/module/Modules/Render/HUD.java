package rbq.wtf.lycoris.client.module.Modules.Render;

import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.EventTarget;
import rbq.wtf.lycoris.client.event.Render2DEvent;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HUD extends Module {
    public final BooleanValue waterMark = new BooleanValue("WaterMark", true, this);
    public final BooleanValue arrayList = new BooleanValue("ArrayList", true, this);
    public final ModeValue rainbowMode = new ModeValue("Rainbow Mode", new String[]{"Rainbow", "Astolfo", "Static", "StaticRainbow"}, 0, this);
    public final NumberValue rainbowSpeed = new NumberValue("RainbowSpeed", 100.0F, 0.0F, 2000.0F, 0.1F, this);

    public HUD() {
        super("HUD", ModuleCategory.Render, 0);
    }

    @Override
    public void onEnable() {
    }

    @EventTarget
    public void onRender2D(Render2DEvent e) {
        ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
        if (waterMark.get()) {
            FontLoaders.default25.drawStringWithShadow("Dimples.love",
                    5,
                    5,
                    new Color(0, 200, 100).getRGB());
        }
        if (arrayList.get()) {
            ArrayList<Module> sorted = new ArrayList<>();
            for (Module m : Client.moduleManager.getModules()) {
                if (!m.isState()) continue;
                sorted.add(m);
            }
            sorted.sort(Comparator.comparingInt(mm -> -(FontLoaders.default16.getStringWidth(mm.getName()))));
            float posY = 0;
            for (Module m : sorted) {
                FontLoaders.default20.drawStringWithShadow(m.getName(),
                        sc.getScaledWidth() - FontLoaders.default20.getStringWidth(m.getName()) - 5,
                        posY + 5,
                        new Color(0, 200, 100).getRGB()
                );
                posY = posY + FontLoaders.default16.getStringHeight(m.getName()) + 4;
            }

        }
    }

}
