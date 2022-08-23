package rbq.wtf.lycoris.client.wrapper.wrappers.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapFields

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention
@JvmRepeatable(
    WrapFields::class
)
/**
 * Auto setup field
 */
annotation class WrapField(val mcpName: String, val targetMap: MapEnum, val customSrgName: String = "none")