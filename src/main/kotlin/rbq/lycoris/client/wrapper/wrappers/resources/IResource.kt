package rbq.lycoris.client.wrapper.wrappers.resources

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapMethod
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import java.io.InputStream
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.resources.IResource", targetMap = MapEnum.VANILLA189)
class IResource(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapMethod(mcpName = "getInputStream", targetMap = MapEnum.VANILLA189)
        lateinit var getInputStream: Method
    }

    val inputStream: InputStream
        get() = invoke(getInputStream) as InputStream
}