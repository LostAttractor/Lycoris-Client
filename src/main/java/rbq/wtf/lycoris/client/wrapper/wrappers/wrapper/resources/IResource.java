package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.io.InputStream;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.resources.IResource", targetMap = MapEnum.VANILLA189)
public class IResource extends IWrapper{
    @WrapMethod(mcpName = "getInputStream", targetMap = MapEnum.VANILLA189)
    public static Method getInputStream;

    public IResource(Object obj) {
        super(obj);
    }

    public InputStream getInputStream() {
        return (InputStream) invoke(getInputStream);
    }
}
