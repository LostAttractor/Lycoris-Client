package rbq.wtf.lycoris.client.transformer.transformers;


import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.events.EventRender2D;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.GuiIngame;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution;

public class GuiIngameTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiIngame.GuiIngameClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(GuiIngame.renderTooltip.getName()) && method.desc.equalsIgnoreCase("(L" + Type.getInternalName(ScaledResolution.ScaledResolution) + ";F)V")) {
                InsnList insns = new InsnList();
                insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventRender2D.class)));
                insns.add(new InsnNode(Opcodes.DUP));
                insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(ScaledResolution.class)));
                insns.add(new InsnNode(Opcodes.DUP));
                insns.add(new VarInsnNode(Opcodes.ALOAD, 1));
                insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(ScaledResolution.class), "<init>", "(Ljava/lang/Object;)V", false));
                insns.add(new VarInsnNode(Opcodes.FLOAD, 2));
                insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(L" + Type.getInternalName(ScaledResolution.class) + ";F)V", false));
                insns.add(new InsnNode(Opcodes.POP));
                method.instructions.insert(insns);
                break;
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

}
