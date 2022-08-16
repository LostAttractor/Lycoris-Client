package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.AbstractTexture",targetMap = MapEnum.Srg1_8_9)
public class AbstractTexture extends IWrapper {
    @WrapMethod(mcpName = "getGlTextureId",targetMap = MapEnum.Srg1_8_9)
    public static Method getGlTextureId;
    public AbstractTexture(Object obj) {
        super(obj);
    }
    public int getGlTextureId()
    {
        return (int) ReflectUtil.invoke(getGlTextureId,getWrapObject());
    }
}