package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.ResourceLocation
import java.io.IOException
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.resources.IResourceManager", targetMap = MapEnum.VANILLA189)
open class IResourceManager(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "getResource", targetMap = MapEnum.VANILLA189)
        lateinit var getResource: Method
    }
    @Throws(IOException::class)
    fun getResource(resourceLocation: ResourceLocation): IResource {
        return invoke(getResource, resourceLocation) as IResource
    }
}