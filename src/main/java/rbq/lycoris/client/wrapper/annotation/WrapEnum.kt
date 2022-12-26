package rbq.lycoris.client.wrapper.annotation

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.repeat.WrapEnums

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapEnums::class
)
/**
 * Auto setup Enum
 */
annotation class WrapEnum(val mcpName: String, val targetMap: MapEnum, val customSrgName: String = "none")