package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapConstructor
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import java.lang.reflect.Constructor

@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation", targetMap = MapEnum.VANILLA189)
class ResourceLocation(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapConstructor(targetMap = MapEnum.VANILLA189, signature = [String::class])
        lateinit var ResourceLocation_STRING: Constructor<*>
    }

    constructor(path: String?) : this(ReflectUtil.construction(ResourceLocation_STRING, path))
}