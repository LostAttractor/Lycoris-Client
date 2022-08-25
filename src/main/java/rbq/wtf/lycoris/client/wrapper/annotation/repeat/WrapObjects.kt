package rbq.wtf.lycoris.client.wrapper.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.annotation.WrapObject

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * Auto setup field
 */
annotation class WrapObjects(vararg val value: WrapObject)