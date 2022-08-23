package rbq.wtf.lycoris.client.wrapper.wrappers.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapObjects

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapObjects::class
)
/**
 * Auto setup field
 */
annotation class WrapObject(val mcpName: String, val targetMap: MapEnum, val customSrgName: String = "none")