package rbq.lycoris.client.wrapper.wrappers.util

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapConstructor
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Constructor

@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation", targetMap = MapEnum.VANILLA189)
class ResourceLocation(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [String::class])
        lateinit var ResourceLocation_STRING: Constructor<*>
    }

    constructor(path: String?) : this(ReflectUtil.construction(ResourceLocation_STRING, path))
}