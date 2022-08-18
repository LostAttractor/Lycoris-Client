package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiChat", targetMap = MapEnum.VANILLA189)
public class GuiChat extends GuiScreen {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiChat", targetMap = MapEnum.VANILLA189)
    public static Class GuiChatClass;
    @WrapField(mcpName = "inputField", targetMap = MapEnum.VANILLA189)
    public static Field inputField;
    @WrapField(mcpName = "sentHistoryCursor", targetMap = MapEnum.VANILLA189)
    public static Field sentHistoryCursor;

    public GuiChat(Object obj) {
        super(obj);
    }

    public static boolean isGuiChat(GuiScreen guiScreen) {
        return GuiChatClass.isInstance(guiScreen.getWrapObject());
    }

    public GuiTextField getInputField() {
        return new GuiTextField(getField(inputField));
    }

    public void setInputField(GuiTextField textField) {
        setField(inputField, textField.getWrapObject());
    }

    public void setSentHistoryCursor(int i) {
        setField(sentHistoryCursor, i);
    }
}