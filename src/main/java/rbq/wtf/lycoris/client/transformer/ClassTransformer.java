package rbq.wtf.lycoris.client.transformer;

public abstract class ClassTransformer {
    public abstract Class<?> getTargetClass();

    public abstract byte[] transform(byte[] bytes);
}
