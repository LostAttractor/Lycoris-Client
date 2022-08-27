package rbq.wtf.lycoris.client.transformer.transformers

import rbq.wtf.lycoris.client.transformer.ClassTransformer

class BackTransformer(override val targetClass: Class<*>, private val bytes: ByteArray) : ClassTransformer() {
    override fun transform(bytes: ByteArray): ByteArray {
        return this.bytes
    }
}