package rbq.lycoris.client.wrapper.annotation.repeat

import rbq.lycoris.client.wrapper.annotation.WrapEnum

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * Auto setup field
 */
annotation class WrapEnums(vararg val value: WrapEnum)