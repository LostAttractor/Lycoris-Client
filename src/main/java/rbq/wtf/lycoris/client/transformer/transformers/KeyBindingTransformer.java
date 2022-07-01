package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.events.EventKey;


public class KeyBindingTransformer {
    public static void transform(ClassNode clazz, MethodNode method) {
        if (method.name.equals("onTick") && method.desc.equalsIgnoreCase("(I)V")) {
            InsnList insns = new InsnList();
            insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventKey.class)));
            insns.add(new VarInsnNode(Opcodes.ILOAD,0));
            insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventKey.class), "<init>", "(I)V", false));
            method.instructions.insert(insns);
        }
    }
}
