package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper", targetMap = MapEnum.VANILLA189)
public class OpenGlHelper extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper", targetMap = MapEnum.VANILLA189)
    public static Class OpenGlHelperClass;
    @WrapMethod(mcpName = "glBlendFunc", targetMap = MapEnum.VANILLA189, signature = "(IIII)V")
    public static Method glBlendFunc;

    public OpenGlHelper(Object obj) {
        super(obj);
    }

    public static void glBlendFunc(int i1, int i2, int i3, int i4) {
        ReflectUtil.invoke(glBlendFunc, null, i1, i2, i3, i4);
    }
}