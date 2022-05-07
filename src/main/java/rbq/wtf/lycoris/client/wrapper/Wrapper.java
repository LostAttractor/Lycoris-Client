package rbq.wtf.lycoris.client.wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.Packet;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Wrapper {

    public static boolean canSendMotionPacket = true;

    public static volatile Wrapper INSTANCE = new Wrapper();

    public Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    public EntityPlayerSP player() {
        return Wrapper.INSTANCE.mc().player;
    }

    public WorldClient world() {
        return Wrapper.INSTANCE.mc().world;
    }

    public GameSettings mcSettings() {
        return Wrapper.INSTANCE.mc().gameSettings;
    }

    public FontRenderer fontRenderer() {
        return Wrapper.INSTANCE.mc().fontRenderer;
    }

    public void sendPacket(Packet packet) {
        this.player().connection.sendPacket(packet);
    }

    public InventoryPlayer inventory() {
        return this.player().inventory;
    }

    public PlayerControllerMP controller() {
        return Wrapper.INSTANCE.mc().playerController;
    }

    public static void setTickLength(final Minecraft mc, float tickLength) {
        try {
            Field fTimer = mc.getClass().getDeclaredField(isNotObfuscated() ? "field_71428_T" : "timer");
            fTimer.setAccessible(true);
            Field fTickLength = Timer.class.getDeclaredField(isNotObfuscated() ? "field_194149_e" : "tickLength");
            fTickLength.setAccessible(true);
            fTickLength.setFloat(fTimer.get(mc),50.0f / tickLength);
            fTickLength.setAccessible(false);
            fTimer.setAccessible(false);
        }
        catch (ReflectiveOperationException e) {
            //  throw new RuntimeException(e);
        }
    }

    public static double getRenderPosX() {
        Field field = ReflectionHelper.findField(Minecraft.class, new String[]{"renderManager", "field_175616_W"});
        field.setAccessible(true);

        Field F_renderPosX = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosX", "field_78725_b"});
        F_renderPosX.setAccessible(true);
        double renderPosX = 0;
        try {
            renderPosX = F_renderPosX.getDouble(field.get(Minecraft.getMinecraft()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return renderPosX;
    }

    public static double getRenderPosY() {
        Field field = ReflectionHelper.findField(Minecraft.class, new String[]{"renderManager", "field_175616_W"});
        field.setAccessible(true);

        Field F_renderPosY = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosY", "field_78726_c"});
        F_renderPosY.setAccessible(true);
        double renderPosY = 0;
        try {
            renderPosY = F_renderPosY.getDouble(field.get(Minecraft.getMinecraft()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return renderPosY;
    }

    public static double getRenderPosZ() {
        Field field = ReflectionHelper.findField(Minecraft.class, new String[]{"renderManager", "field_175616_W"});
        field.setAccessible(true);
        Field F_renderPosZ = ReflectionHelper.findField(RenderManager.class, new String[]{"renderPosZ", "field_78723_d"});
        F_renderPosZ.setAccessible(true);
        double renderPosZ = 0;
        try {
            renderPosZ = F_renderPosZ.getDouble(field.get(Minecraft.getMinecraft()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return renderPosZ;
    }


    public static boolean getKeyPressed(KeyBinding key) {
        Boolean Status = false;
        Field F_Key = ObfuscationReflectionHelper.findField(key.getClass(),"field_74513_e");
        F_Key.setAccessible(true);
        try {
            Status = (Boolean) F_Key.get(key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Status;
    }


    public static double getlastReportedPosX(){
        try {
            double curv = 0;
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175172_bI":"lastReportedPosX");m.setAccessible(true);
            curv= (double) m.get(Minecraft.getMinecraft().player);m.setAccessible(false);return curv;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void setlastReportedPosX(double state){
        try {
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175172_bI":"lastReportedPosX");m.setAccessible(true);
            m.set(Minecraft.getMinecraft().player,state);m.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static double getlastReportedPosY(){
        try {
            double curv = 0;
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175166_bJ":"lastReportedPosY");m.setAccessible(true);
            curv= (double) m.get(Minecraft.getMinecraft().player);m.setAccessible(false);return curv;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void setlastReportedPosY(double state){
        try {
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175166_bJ":"lastReportedPosY");m.setAccessible(true);
            m.set(Minecraft.getMinecraft().player,state);m.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static Timer getTimer(final Minecraft mc) {
        return (Timer)ReflectUtil.getField("timer", "field_71428_T", mc);
    }

    public static void setupCameraTransform(final Minecraft mc,int pass) {
        ReflectUtil.invoke(mc.entityRenderer, "setupCameraTransform", "func_78479_a", new Class[]{float.class,int.class}, new Object[] {Wrapper.getTimer(mc).renderPartialTicks,pass});
    }
    public static double getlastReportedPosZ(){
        try {
            double curv = 0;
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175167_bK":"lastReportedPosZ");m.setAccessible(true);
            curv= (double) m.get(Minecraft.getMinecraft().player);m.setAccessible(false);return curv;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void setlastReportedPosZ(double state){
        try {
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175167_bK":"lastReportedPosZ");m.setAccessible(true);
            m.set(Minecraft.getMinecraft().player,state);m.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static double getlastReportedYaw(){
        try {
            double curv = 0;
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175164_bL":"lastReportedYaw");m.setAccessible(true);
            curv= (double) m.get(Minecraft.getMinecraft().player);m.setAccessible(false);return curv;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void setlastReportedYaw(double state){
        try {
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175164_bL":"lastReportedYaw");m.setAccessible(true);
            m.set(Minecraft.getMinecraft().player,state);m.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    public static double getlastReportedPitch(){
        try {
            double curv = 0;
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175165_bM":"lastReportedPitch");m.setAccessible(true);
            curv= (double) m.get(Minecraft.getMinecraft().player);m.setAccessible(false);return curv;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void setlastReportedPitch(double state){
        try {
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175165_bM":"lastReportedPitch");m.setAccessible(true);
            m.set(Minecraft.getMinecraft().player,state);m.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    public static int getpositionUpdateTicks(){
        try {
            int curv = 0;
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175168_bP":"positionUpdateTicks");m.setAccessible(true);
            curv= (int) m.get(Minecraft.getMinecraft().player);m.setAccessible(false);return curv;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static boolean isCurrentViewEntity(){
        try {
            boolean curv = false;
            Method m = EntityPlayerSP.class.getDeclaredMethod(isNotObfuscated()?"func_175160_A":"isCurrentViewEntity");
            m.setAccessible(true);
            curv= (boolean) m.invoke(Minecraft.getMinecraft().player);
            m.setAccessible(false);
            return curv;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setpositionUpdateTicks(int state){
        try {
            Field m = EntityPlayerSP.class.getDeclaredField(isNotObfuscated()?"field_175168_bP":"positionUpdateTicks");m.setAccessible(true);
            m.set(Minecraft.getMinecraft().player,state);m.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static boolean isNotObfuscated() {
        try { return Minecraft.class.getDeclaredField("instance") != null;
        } catch (Exception ex) { return false; }
    }

    public static void displayGuiScreen(GuiScreen screen) {
        Minecraft mc = Minecraft.getMinecraft();
//        JOptionPane.showConfirmDialog(null,"10","DXPSL",JOptionPane.PLAIN_MESSAGE);
        if (screen == null && mc.currentScreen == null) {
            return;
        }
        if (screen == null && mc.currentScreen != null && mc.currentScreen.getClass().getName().toLowerCase().startsWith("net.minecraft")) {
            mc.player.closeScreen();
        } else {
            mc.getMinecraft().currentScreen = screen;
            mc.getMinecraft().setIngameNotInFocus();
            ScaledResolution scaledresolution = new ScaledResolution(mc.getMinecraft());
            int i = scaledresolution.getScaledWidth();
            int j = scaledresolution.getScaledHeight();
            mc.getMinecraft().currentScreen.mc = mc.getMinecraft();
            mc.getMinecraft().currentScreen.width = i;
            mc.getMinecraft().currentScreen.height = j;
            mc.getMinecraft().currentScreen.initGui();
        }
    }
}
