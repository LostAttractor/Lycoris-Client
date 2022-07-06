package rbq.wtf.lycoris.client.transformer.transformers;


import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;

import rbq.wtf.lycoris.client.event.events.EventRender2D;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;

public class GuiIngameTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiIngame.class;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method: classNode.methods){
            if (method.name.equals("renderTooltip") && method.desc.equalsIgnoreCase("(L"+Type.getInternalName(ScaledResolution.class)+";F)V")) {
                InsnList insns = new InsnList();
                insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventRender2D.class)));
                insns.add(new VarInsnNode(Opcodes.ALOAD,1));
                insns.add(new VarInsnNode(Opcodes.FLOAD,2));
                insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(Lavr;F)V", false));
                method.instructions.insert(insns);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
