package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.client.transformer.ClassTransformer;

public class BackTransformer extends ClassTransformer {
    private final Class<?> targetClass;
    private final byte[] bytes;

    public BackTransformer(Class<?> targetClass, byte[] bytes) {
        this.targetClass = targetClass;
        this.bytes = bytes;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        return this.bytes;
    }
}
