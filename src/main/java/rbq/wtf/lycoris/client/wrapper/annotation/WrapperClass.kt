package rbq.wtf.lycoris.client.wrapper.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.repeat.WrapperClasses

@MustBeDocumented
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapperClasses::class
)
annotation class WrapperClass(val mcpName: String, val targetMap: MapEnum)