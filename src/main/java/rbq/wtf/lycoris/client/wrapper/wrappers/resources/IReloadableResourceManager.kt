package rbq.wtf.lycoris.client.wrapper.wrappers.resources

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.client.resources.IReloadableResourceManager", targetMap = MapEnum.VANILLA189)
class IReloadableResourceManager(obj: Any) : IResourceManager(obj)