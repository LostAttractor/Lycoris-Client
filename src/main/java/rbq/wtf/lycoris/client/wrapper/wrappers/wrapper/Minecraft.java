package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.GuiScreen;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.Minecraft", targetMap = MapEnum.VANILLA189)
public class Minecraft extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.Minecraft", targetMap = MapEnum.VANILLA189)
    public static Class MinecraftClass;
    @WrapField(mcpName = "theMinecraft", targetMap = MapEnum.VANILLA189)
    public static Field theMinecraft;
    @WrapMethod(mcpName = "getMinecraft", targetMap = MapEnum.VANILLA189)
    public static Method getMinecraft;
    @WrapField(mcpName = "timer", targetMap = MapEnum.VANILLA189)
    public static Field timer;
    @WrapMethod(mcpName = "displayGuiScreen", targetMap = MapEnum.VANILLA189)
    public static Method displayGuiScreen;
    @WrapField(mcpName = "theWorld", targetMap = MapEnum.VANILLA189)
    public static Field theWorld;
    @WrapField(mcpName = "thePlayer", targetMap = MapEnum.VANILLA189)
    public static Field thePlayer;
    @WrapField(mcpName = "currentScreen", targetMap = MapEnum.VANILLA189)
    public static Field currentScreen;
    @WrapField(mcpName = "ingameGUI", targetMap = MapEnum.VANILLA189)
    public static Field ingameGUI;
    @WrapField(mcpName = "serverName", targetMap = MapEnum.VANILLA189)
    public static Field serverName;
    @WrapField(mcpName = "serverPort", targetMap = MapEnum.VANILLA189)
    public static Field serverPort;
    @WrapField(mcpName = "gameSettings", targetMap = MapEnum.VANILLA189)
    public static Field gameSettings;
    @WrapField(mcpName = "playerController", targetMap = MapEnum.VANILLA189)
    public static Field playerController;
    @WrapField(mcpName = "leftClickCounter", targetMap = MapEnum.VANILLA189)
    public static Field leftClickCounter;
    @WrapMethod(mcpName = "runTick", targetMap = MapEnum.VANILLA189)
    public static Method runTick;
    @WrapMethod(mcpName = "dispatchKeypresses", targetMap = MapEnum.VANILLA189)
    public static Method dispatchKeypresses;
    @WrapField(mcpName = "debugFPS", targetMap = MapEnum.VANILLA189)
    public static Field debugFPS;
    @WrapField(mcpName = "renderManager", targetMap = MapEnum.VANILLA189)
    public static Field renderManager;
    @WrapField(mcpName = "renderEngine", targetMap = MapEnum.VANILLA189)
    public static Field renderEngine;
    @WrapField(mcpName = "displayHeight", targetMap = MapEnum.VANILLA189)
    public static Field displayHeight;
    @WrapField(mcpName = "session", targetMap = MapEnum.VANILLA189)
    public static Field session;
    @WrapMethod(mcpName = "setIngameNotInFocus", targetMap = MapEnum.VANILLA189)
    public static Method setIngameNotInFocus;
    @WrapMethod(mcpName = "runGameLoop", targetMap = MapEnum.VANILLA189)
    public static Method runGameLoop;
    @WrapMethod(mcpName = "getNetHandler", targetMap = MapEnum.VANILLA189)
    public static Method getNetHandler;
    @WrapField(mcpName = "currentServerData", targetMap = MapEnum.VANILLA189)
    public static Field currentServerData;
    @WrapField(mcpName = "objectMouseOver", targetMap = MapEnum.VANILLA189)
    public static Field objectMouseOver;
    @WrapField(mcpName = "fontRendererObj", targetMap = MapEnum.VANILLA189)
    public static Field fontRendererObj;
    @WrapField(mcpName = "pointedEntity", targetMap = MapEnum.VANILLA189)
    public static Field pointedEntity;
    @WrapField(mcpName = "renderViewEntity", targetMap = MapEnum.VANILLA189)
    public static Field renderViewEntity;
    @WrapMethod(mcpName = "loadWorld", targetMap = MapEnum.VANILLA189)
    public static Method loadWorld;
    @WrapField(mcpName = "entityRenderer", targetMap = MapEnum.VANILLA189)
    public static Field entityRenderer;
    @WrapField(mcpName = "renderGlobal", targetMap = MapEnum.VANILLA189)
    public static Field renderGlobal;
    @WrapMethod(mcpName = "addScheduledTask", targetMap = MapEnum.VANILLA189, signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;")
    public static Method addScheduledTask;
    @WrapMethod(mcpName = "displayWidth", targetMap = MapEnum.VANILLA189)
    public static Field displayWidth;

    public Minecraft(Object obj) {
        super(obj);
    }

    public static int getDebugFPS() {
        return (int) ReflectUtil.getField(debugFPS, null);
    }

    public int getDisplayHeight() {
        return (int) ReflectUtil.getField(displayHeight, getWrapObject());
    }

    public int getDisplayWidth() {
        return (int) getField(displayWidth);
    }

    public static Minecraft getMinecraft() {
        return new Minecraft(ReflectUtil.getField(theMinecraft, null));
    }

    public void addScheduledTask(Object o) {
    }

    public GameSettings getGameSettings() {
        return new GameSettings(ReflectUtil.getField(gameSettings, getWrapObject()));
    }

    public void displayGuiScreenBypass(GuiScreen screen) {
        ReflectUtil.setField(currentScreen, screen.getWrapObject(), getWrapObject());
        ReflectUtil.invoke(setIngameNotInFocus, getWrapObject());
        ScaledResolution scaledresolution = new ScaledResolution(this);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        getCurrentScreen().setMc(this);
        getCurrentScreen().setHeight(j);
        getCurrentScreen().setWidth(i);
        getCurrentScreen().initGui();
    }

    public GuiScreen getCurrentScreen() {
        return new GuiScreen(ReflectUtil.getField(currentScreen, getWrapObject()));
    }
}