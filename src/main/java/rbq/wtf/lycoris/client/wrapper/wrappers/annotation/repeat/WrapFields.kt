package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
/**
 * Auto setup field
 */
annotation class WrapFields(vararg val value: WrapField)