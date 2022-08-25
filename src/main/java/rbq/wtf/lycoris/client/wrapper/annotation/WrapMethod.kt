package rbq.wtf.lycoris.client.wrapper.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.repeat.WrapMethods

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapMethods::class
)
/**
 * Auto setup method
 */
annotation class WrapMethod(
    val mcpName: String,
    val targetMap: MapEnum,
    val customSrgName: String = "none",
    val signature: String = "none"
)