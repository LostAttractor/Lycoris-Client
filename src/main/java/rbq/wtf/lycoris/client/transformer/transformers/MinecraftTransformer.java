package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.events.EventLoop;
import rbq.wtf.lycoris.client.event.events.EventTick;


public class MinecraftTransformer {
    public static void transform(ClassNode clazz, MethodNode method) {
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
    public static void Test(){
        EventManager.call(new EventTick());
    }
}
