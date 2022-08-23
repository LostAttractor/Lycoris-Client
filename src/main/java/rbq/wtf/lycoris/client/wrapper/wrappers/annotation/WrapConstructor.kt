package rbq.wtf.lycoris.client.wrapper.wrappers.annotation

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapConstructors
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@JvmRepeatable(
    WrapConstructors::class
)
annotation class WrapConstructor(val targetMap: MapEnum, val signature: Array<KClass<*>> = [])