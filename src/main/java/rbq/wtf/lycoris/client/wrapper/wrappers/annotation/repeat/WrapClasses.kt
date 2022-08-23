package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapClasses(vararg val value: WrapClass)