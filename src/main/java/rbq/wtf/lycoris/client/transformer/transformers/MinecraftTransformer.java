package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.events.EventLoop;
import rbq.wtf.lycoris.client.event.events.EventTick;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;


public class MinecraftTransformer extends ClassTransformer {
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
            if (method.name.equalsIgnoreCase("runTick")){
                InsnList insnList = new InsnList();
                insnList.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventTick.class)));
                insnList.add(new InsnNode(Opcodes.DUP));
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventTick.class),"<init>","()V",false));
                insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventManager.class),"call","(Lrbq/wtf/lycoris/client/event/api/events/Event;)Lrbq/wtf/lycoris/client/event/api/events/Event;", false));
                insnList.add(new InsnNode(Opcodes.POP));
                method.instructions.insert(insnList);
            }
            if (method.name.equalsIgnoreCase("runGameLoop")){
                InsnList insnList = new InsnList();
                insnList.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventLoop.class)));
                insnList.add(new InsnNode(Opcodes.DUP));
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventLoop.class),"<init>","()V",false));
                insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventManager.class),"call","(Lrbq/wtf/lycoris/client/event/api/events/Event;)Lrbq/wtf/lycoris/client/event/api/events/Event;", false));
                insnList.add(new InsnNode(Opcodes.POP));
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

}
