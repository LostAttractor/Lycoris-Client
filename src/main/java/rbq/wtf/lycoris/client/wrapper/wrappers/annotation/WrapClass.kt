package rbq.wtf.lycoris.client.wrapper.wrappers.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapClasses

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapClasses::class
)
annotation class WrapClass(val mcpName: String, val targetMap: MapEnum)