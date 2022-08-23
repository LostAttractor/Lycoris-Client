package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.client.resources.IReloadableResourceManager", targetMap = MapEnum.VANILLA189)
public class IReloadableResourceManager extends IResourceManager {
    public IReloadableResourceManager(Object obj) {
        super(obj);
    }
}
