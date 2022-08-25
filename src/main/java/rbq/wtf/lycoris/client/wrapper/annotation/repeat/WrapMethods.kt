package rbq.wtf.lycoris.client.wrapper.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapMethods(vararg val value: WrapMethod)