package rbq.wtf.lycoris.client.module.Modules.Render;

import rbq.wtf.lycoris.client.detector.MargeleAntiCheatDetector;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.GuiScreen;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.IGuiScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ClickGUI extends Module {
    private static BooleanValue waterMark;
    private static BooleanValue arrayList;
    private static ModeValue rainbowMode;

    public ClickGUI() {
        super("ClickGUI", ModuleCategory.Render, 210);
        rainbowMode = new ModeValue("Rainbow Mode", new String[]{"Rainbow", "Astolfo", "Static", "StaticRainbow"}, 0, 3);
        this.addModeValue(rainbowMode);
        waterMark = new BooleanValue("WaterMark", true, this);
        this.addBooleanValue(waterMark);
        arrayList = new BooleanValue("ArrayList", true, this);
        this.addBooleanValue(arrayList);
    }

    @Override
    public void onEnable() {
        if (MargeleAntiCheatDetector.getHyGui() != null) {
            Class<?> hyGui = MargeleAntiCheatDetector.getHyGui();
            try {
                Constructor<?> constructor = hyGui.getConstructor(int.class, MargeleAntiCheatDetector.getGuiTab());
                constructor.setAccessible(true);
                Object gui = constructor.newInstance(0, MargeleAntiCheatDetector.getHyTab());
                Minecraft.getMinecraft().displayGuiScreenBypass(new IGuiScreen(gui));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
            return;
        }
        GuiScreen guiScreenWrapper = Minecraft.getMinecraft().getCurrentScreen();
        if (Objects.isNull(guiScreenWrapper.getWrapObject())) {
            Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiScreen(new rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI()));
        }
        this.toggle();
    }
}
