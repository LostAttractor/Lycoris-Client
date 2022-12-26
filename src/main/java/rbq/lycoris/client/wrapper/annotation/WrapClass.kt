package rbq.lycoris.client.wrapper.annotation

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.repeat.WrapClasses

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapClasses::class
)
annotation class WrapClass(val mcpName: String, val targetMap: MapEnum)

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapClassAuto