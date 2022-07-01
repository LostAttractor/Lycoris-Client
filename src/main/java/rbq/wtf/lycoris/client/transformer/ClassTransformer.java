package rbq.wtf.lycoris.client.transformer;

import rbq.wtf.lycoris.agent.asm.tree.ClassNode;
import rbq.wtf.lycoris.agent.asm.tree.MethodNode;

public abstract class ClassTransformer {
    public abstract Class<?> getTargetClass();
    public abstract byte[] transform(byte[] bytes);
}
