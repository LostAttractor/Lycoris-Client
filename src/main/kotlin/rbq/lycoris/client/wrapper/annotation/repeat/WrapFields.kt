package rbq.lycoris.client.wrapper.annotation.repeat

import rbq.lycoris.client.wrapper.annotation.WrapField

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * Auto setup field
 */
annotation class WrapFields(vararg val value: WrapField)