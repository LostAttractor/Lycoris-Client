package rbq.wtf.lycoris.client.transformer

abstract class ClassTransformer {
    abstract val targetClass: Class<*>
    abstract fun transform(bytes: ByteArray): ByteArray
}