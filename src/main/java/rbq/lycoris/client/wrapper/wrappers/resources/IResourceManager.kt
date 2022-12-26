package rbq.lycoris.client.wrapper.wrappers.resources

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.wrappers.util.ResourceLocation
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