package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
@WrapperClass(mcpName = "net.minecraft.client.settings.KeyBinding",targetMap = MapEnum.Srg1_8_9)
public class KeyBinding extends IWrapper{
    @WrapClass(mcpName = "net.minecraft.client.settings.KeyBinding",targetMap = MapEnum.Srg1_8_9)
    public static Class KeyBindingClass;
    @WrapField(mcpName = "pressed",targetMap = MapEnum.Srg1_8_9)
    public static Field pressed;
    @WrapMethod(mcpName = "onTick",targetMap = MapEnum.Srg1_8_9)
    public static Method onTick;
    @WrapField(mcpName = "keyCode",targetMap = MapEnum.Srg1_8_9)
    public static Field keyCode;

    public KeyBinding(Object obj) {
        super(obj);
    }
    public boolean isKeyDown(){
        return (boolean) ReflectUtil.getField(pressed,getWrapObject());
    }
    public void setPressed(boolean v){
        setField(pressed,v);
    }
    public boolean isPressed(){
        return (boolean) getField(pressed);
    }
    public static void onTick(int keyCode) {
        ReflectUtil.invoke(onTick,null,keyCode);
    }

    public int getKeyCode() {
        return (int) ReflectUtil.getField(keyCode,getWrapObject());
    }
}
