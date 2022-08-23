package rbq.wtf.lycoris.client.wrapper.wrappers.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapEnums

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