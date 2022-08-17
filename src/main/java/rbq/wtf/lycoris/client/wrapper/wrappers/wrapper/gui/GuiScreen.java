package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = MapEnum.VANILLA189)
public class GuiScreen extends Gui {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = MapEnum.VANILLA189)
    public static Class GuiScreenClass;
    @WrapMethod(mcpName = "drawScreen",targetMap = MapEnum.VANILLA189)
    public static Method drawScreen;
    @WrapMethod(mcpName = "initGui",targetMap = MapEnum.VANILLA189)
    public static Method initGui;
    @WrapMethod(mcpName = "onGuiClosed",targetMap = MapEnum.VANILLA189)
    public static Method onGuiClosed;
    @WrapField(mcpName = "mc",targetMap = MapEnum.VANILLA189)
    public static Field mc;
    @WrapField(mcpName = "width",targetMap = MapEnum.VANILLA189)
    public static Field width;
    @WrapField(mcpName = "height",targetMap = MapEnum.VANILLA189)
    public static Field height;
    @WrapMethod(mcpName = "keyTyped",targetMap = MapEnum.VANILLA189)
    public static Method keyTyped;
    @WrapMethod(mcpName = "sendChatMessage",targetMap = MapEnum.VANILLA189, signature = "(Ljava/lang/String;Z)V")
    public static Method sendChatMessage_SZ_V;
    @WrapMethod(mcpName = "handleComponentHover",targetMap = MapEnum.VANILLA189)
    public static Method handleComponentHover;
    @WrapMethod(mcpName = "updateScreen",targetMap = MapEnum.VANILLA189)
    public static Method updateScreen;
    @WrapMethod(mcpName = "mouseClicked",targetMap = MapEnum.VANILLA189)
    public static Method mouseClicked;
    @WrapMethod(mcpName = "mouseReleased",targetMap = MapEnum.VANILLA189)
    public static Method mouseReleased;
    public GuiScreen(Object obj) {
        super(obj);
    }

    public void setMc(Minecraft mcIn) {
        ReflectUtil.setField(mc,mcIn.getWrapObject(),getWrapObject());
    }

    public void setHeight(int heightIn) {
        ReflectUtil.setField(height,heightIn,getWrapObject());
    }

    public void setWidth(int widthIn) {
        ReflectUtil.setField(width,widthIn,getWrapObject());
    }
    public void initGui(){
        ReflectUtil.invoke(initGui,getWrapObject());
    }
    public void keyTyped(char c,int i){

    }
    public int getHeight(){
        return (int) getField(height);
    }
    public int getWidth(){
        return (int) getField(width);
    }
    public void drawScreen(int var1, int var2, float var3){

    }
    public void handleComponentHover(IChatComponent chatComponent, int i1, int i2){
        invoke(handleComponentHover,chatComponent.getWrapObject(),i1,i2);
    }
    public void sendChatMessage(String s){
        invoke(sendChatMessage_SZ_V,s,true);
    }
}