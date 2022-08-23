package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapEnum

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * Auto setup field
 */
annotation class WrapEnums(vararg val value: WrapEnum)