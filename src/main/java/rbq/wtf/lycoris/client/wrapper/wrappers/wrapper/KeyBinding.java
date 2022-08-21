package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding", targetMap = MapEnum.VANILLA189)
public class KeyBinding extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.settings.KeyBinding", targetMap = MapEnum.VANILLA189)
    public static Class KeyBindingClass;
    @WrapField(mcpName = "pressed", targetMap = MapEnum.VANILLA189)
    public static Field pressed;
    @WrapMethod(mcpName = "onTick", targetMap = MapEnum.VANILLA189)
    public static Method onTick;
    @WrapField(mcpName = "keyCode", targetMap = MapEnum.VANILLA189)
    public static Field keyCode;

    public KeyBinding(Object obj) {
        super(obj);
    }

    public static void onTick(int keyCode) {
        ReflectUtil.invokeStatic(onTick, keyCode);
    }

    public boolean isKeyDown() {
        return (boolean) getField(pressed);
    }

    public boolean isPressed() {
        return (boolean) getField(pressed);
    }

    public void setPressed(boolean v) {
        setField(pressed, v);
    }

    public int getKeyCode() {
        return (int) getField(keyCode);
    }
}
