package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.ResourceLocation;

import java.io.IOException;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.resources.IResourceManager", targetMap = MapEnum.VANILLA189)
public class IResourceManager extends IWrapper {

    @WrapMethod(mcpName = "getResource", targetMap = MapEnum.VANILLA189)
    public static Method getResource;
    public IResourceManager(Object obj) {
        super(obj);
    }

    public IResource getResource(ResourceLocation resourceLocation) throws IOException {
        return (IResource) invoke(getResource, resourceLocation);
    }
}
