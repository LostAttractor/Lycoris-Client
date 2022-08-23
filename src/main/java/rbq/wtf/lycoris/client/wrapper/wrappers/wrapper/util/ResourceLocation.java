package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapConstructor;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation", targetMap = MapEnum.VANILLA189)
public class ResourceLocation extends IWrapper {
    @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = {String.class})
    public static Constructor<?> ResourceLocation_STRING;

    public ResourceLocation(Object obj) {
        super(obj);
    }

    public ResourceLocation(String path) {
        super(ReflectUtil.construction(ResourceLocation_STRING, path));
    }
}
