package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.Gui", targetMap = MapEnum.VANILLA189)

public class Gui extends IWrapper {
    @WrapMethod(mcpName = "drawModalRectWithCustomSizedTexture", targetMap = MapEnum.VANILLA189)
    public static Method drawModalRectWithCustomSizedTexture;
    @WrapMethod(mcpName = "drawRect", targetMap = MapEnum.VANILLA189)
    public static Method drawRect;

    public Gui(Object obj) {
        super(obj);
    }

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        ReflectUtil.invoke(drawModalRectWithCustomSizedTexture, null, x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public static void drawRect(int left, int top, int right, int bottom, int color) {
        ReflectUtil.invoke(drawRect, null, left, top, right, bottom, color);
    }
}