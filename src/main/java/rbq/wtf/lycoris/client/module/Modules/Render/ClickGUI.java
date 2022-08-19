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
    public final BooleanValue waterMark = new BooleanValue("WaterMark", true, this);
    public final BooleanValue arrayList = new BooleanValue("ArrayList", true, this);
    public final ModeValue rainbowMode = new ModeValue("Rainbow Mode", new String[]{"Rainbow", "Astolfo", "Static", "StaticRainbow"}, 0, 3, this);

    public ClickGUI() {
        super("ClickGUI", ModuleCategory.Render, 210); // Insert Key
        this.addModeValue(rainbowMode);
        this.addBooleanValue(waterMark);
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
            Minecraft.getMinecraft().displayGuiScreenBypass(BridgeUtil.createGuiScreen(client.clickGUI));
        }
        this.toggle();
    }
}
