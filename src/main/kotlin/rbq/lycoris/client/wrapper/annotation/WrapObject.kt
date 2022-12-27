package rbq.lycoris.client.wrapper.annotation

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.repeat.WrapObjects

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