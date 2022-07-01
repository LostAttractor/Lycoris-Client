package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;


public class KeyBindingTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return null;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method: classNode.methods){
            if (method.name.equals("onTick") && method.desc.equalsIgnoreCase("(I)V")) {
                InsnList insns = new InsnList();
                insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventKey.class)));
                insns.add(new VarInsnNode(Opcodes.ILOAD,0));
                insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventKey.class), "<init>", "(I)V", false));
                method.instructions.insert(insns);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
