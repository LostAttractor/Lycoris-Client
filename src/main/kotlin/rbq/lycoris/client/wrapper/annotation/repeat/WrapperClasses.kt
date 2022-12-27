package rbq.lycoris.client.wrapper.annotation.repeat

import rbq.lycoris.client.wrapper.annotation.WrapperClass

@MustBeDocumented
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapperClasses(vararg val value: WrapperClass)