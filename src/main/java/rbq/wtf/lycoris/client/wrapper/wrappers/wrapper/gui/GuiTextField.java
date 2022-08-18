package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.FontRenderer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA189)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA1122)
public class GuiTextField extends Gui {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA189)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiTextField", targetMap = MapEnum.VANILLA1122)
    public static Class GuiTextFieldClass;
    @WrapField(mcpName = "text", targetMap = MapEnum.VANILLA189)
    @WrapField(mcpName = "text", targetMap = MapEnum.VANILLA1122)
    public static Field text;
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {int.class, FontRenderer.class, int.class, int.class, int.class, int.class})
    @WrapConstructor(targetMap = MapEnum.VANILLA1122, signature = {int.class, FontRenderer.class, int.class, int.class, int.class, int.class})
    public static Constructor GuiTextField_I_FontRenderer_IIII;
    @WrapField(mcpName = "maxStringLength", targetMap = MapEnum.VANILLA189)
    @WrapField(mcpName = "maxStringLength", targetMap = MapEnum.VANILLA1122)
    public static Field maxStringLength;
    @WrapField(mcpName = "enableBackgroundDrawing", targetMap = MapEnum.VANILLA189)
    @WrapField(mcpName = "enableBackgroundDrawing", targetMap = MapEnum.VANILLA1122)
    public static Field enableBackgroundDrawing;
    @WrapMethod(mcpName = "setFocused", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "setFocused", targetMap = MapEnum.VANILLA1122)
    public static Method setFocused;
    @WrapField(mcpName = "canLoseFocus", targetMap = MapEnum.VANILLA189)
    @WrapField(mcpName = "canLoseFocus", targetMap = MapEnum.VANILLA1122)
    public static Field canLoseFocus;
    @WrapMethod(mcpName = "drawTextBox", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "drawTextBox", targetMap = MapEnum.VANILLA1122)
    public static Method drawTextBox;
    @WrapMethod(mcpName = "func_146197_a", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "getNthWordFromPosWS", targetMap = MapEnum.VANILLA1122)
    public static Method func_146197_a;
    @WrapMethod(mcpName = "deleteFromCursor", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "deleteFromCursor", targetMap = MapEnum.VANILLA1122)
    public static Method deleteFromCursor;
    @WrapField(mcpName = "cursorPosition", targetMap = MapEnum.VANILLA189)
    @WrapField(mcpName = "cursorPosition", targetMap = MapEnum.VANILLA1122)
    public static Field cursorPosition;
    @WrapMethod(mcpName = "writeText", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "writeText", targetMap = MapEnum.VANILLA1122)
    public static Method writeText;
    @WrapMethod(mcpName = "setText", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "setText", targetMap = MapEnum.VANILLA1122)
    public static Method setText;
    @WrapMethod(mcpName = "textboxKeyTyped", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "textboxKeyTyped", targetMap = MapEnum.VANILLA1122)
    public static Method textboxKeyTyped;
    @WrapMethod(mcpName = "updateCursorCounter", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "updateCursorCounter", targetMap = MapEnum.VANILLA1122)
    public static Method updateCursorCounter;
    @WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA189)
    @WrapMethod(mcpName = "mouseClicked", targetMap = MapEnum.VANILLA1122)
    public static Method mouseClicked;

    public GuiTextField(Object obj) {
        super(obj);
    }

    public GuiTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        super(ReflectUtil.construction(GuiTextField_I_FontRenderer_IIII, componentId, fontrendererObj.getWrapObject(), x, y, par5Width, par6Height));
    }

    public String getText() {
        return (String) getField(text);
    }

    public void setText(String s) {
        invoke(setText, s);
    }

    public void setMaxStringLength(int max) {
        setField(maxStringLength, max);
    }

    public void setEnableBackgroundDrawing(boolean max) {
        setField(enableBackgroundDrawing, max);
    }

    public void setFocused(boolean b) {
        invoke(setFocused, b);
    }

    public void setCanLoseFocus(boolean b) {
        setField(canLoseFocus, b);
    }

    public void drawTextBox() {
        invoke(drawTextBox);
    }

    public void deleteFromCursor(int length) {
        invoke(deleteFromCursor, length);
    }

    public int func_146197_a(int p_146197_1_, int p_146197_2_, boolean p_146197_3_) {
        return (int) invoke(func_146197_a, p_146197_1_, p_146197_2_, p_146197_3_);
    }

    public int getCursorPosition() {
        return (int) getField(cursorPosition);
    }

    public void writeText(String s) {
        invoke(writeText, s);
    }

    public boolean textboxKeyTyped(char c, int i) {
        return (boolean) invoke(textboxKeyTyped, c, i);
    }

    public void updateCursorCounter() {
        invoke(updateCursorCounter);
    }

    public void mouseClicked(int i1, int i2, int i3) {
        invoke(mouseClicked, i1, i2, i3);
    }
}