package rbq.lycoris.client.wrapper.wrappers.resources

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.client.resources.IReloadableResourceManager", targetMap = MapEnum.VANILLA189)
class IReloadableResourceManager(obj: Any) : IResourceManager(obj)