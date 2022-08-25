package rbq.wtf.lycoris.client.wrapper.annotation.repeat

import rbq.wtf.lycoris.client.wrapper.annotation.WrapClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapClasses(vararg val value: WrapClass)