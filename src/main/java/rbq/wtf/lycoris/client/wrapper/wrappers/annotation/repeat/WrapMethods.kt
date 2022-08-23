package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapMethods(vararg val value: WrapMethod)