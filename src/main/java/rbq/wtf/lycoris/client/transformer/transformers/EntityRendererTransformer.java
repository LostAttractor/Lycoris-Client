package rbq.wtf.lycoris.client.transformer.transformers;


import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.event.events.EventRender3D;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.Entity;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.EntityRenderer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.GlStateManager;

public class EntityRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityRenderer.EntityRendererClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityRenderer.renderWorldPass.getName()) && method.desc.equals("(IFJ)V")){
                System.out.println("transform renderWorldPass");
                InsnList render3D = new InsnList();
                InsnList insnList = new InsnList();
                //GETSTATIC al/nya/reflect/Reflect.Instance : Lal/nya/reflect/Reflect;
                //GETFIELD al/nya/reflect/Reflect.eventBus : Lal/nya/reflect/events/EventBus;
                //NEW al/nya/reflect/events/events/EventRender3D
                //DUP
                //FLOAD 1
                //INVOKESPECIAL al/nya/reflect/events/events/EventRender3D.<init> (F)V
                //INVOKEVIRTUAL al/nya/reflect/events/EventBus.callEvent (Lal/nya/reflect/events/events/Event;)V
                rbq.wtf.lycoris.agent.asm.tree.InsnList insns = new rbq.wtf.lycoris.agent.asm.tree.InsnList();
                insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventRender3D.class)));
                insns.add(new VarInsnNode(Opcodes.FLOAD,0));
                insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventRender3D.class), "<init>", "(F)V", false));
                method.instructions.insert(insns);
                boolean transformed = false;
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    insnList.add(instruction);
                    if (instruction instanceof MethodInsnNode && (!transformed)){
                        if (((MethodInsnNode) instruction).owner.equals(Type.getInternalName(GlStateManager.GlStateManagerClass))
                                && ((MethodInsnNode) instruction).name.equals(GlStateManager.alphaFunc.getName())&&((MethodInsnNode) instruction).desc.equals("(IF)V")){
                            insnList.add(render3D);
                            transformed = true;
                        }
                    }
                }
                method.instructions = insnList;
                method.maxLocals++;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
