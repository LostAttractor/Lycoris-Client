package rbq.wtf.lycoris.client.transformer.transformers;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.Event;
import rbq.wtf.lycoris.client.event.EventManager;
import rbq.wtf.lycoris.client.event.LoopEvent;
import rbq.wtf.lycoris.client.event.TickEvent;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;


public class MinecraftTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Minecraft.MinecraftClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equalsIgnoreCase(Minecraft.runTick.getName())) {
                InsnList insnList = new InsnList();
                // {this} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                //获取Client.eventManager
                // {this} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(TickEvent.class)));
                //新建对象但未初始化
                // {this} | {eventManager, uninitialized_TickEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                //入栈
                // {this} | {eventManager, uninitialized_TickEvent, uninitialized_TickEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(TickEvent.class), "<init>", "()V", false));
                //初始化对象
                // {this} | {eventManager, TickEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // 执行方法, 所有元素出栈
                // {this} | {}
                method.instructions.insert(insnList);
            }
            if (method.name.equalsIgnoreCase(Minecraft.runGameLoop.getName())) {
                InsnList insnList = new InsnList();
                // {this} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                //获取Client.eventManager
                // {this} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(LoopEvent.class)));
                //新建对象但未初始化
                // {this} | {eventManager, uninitialized_TickEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                //入栈
                // {this} | {eventManager, uninitialized_TickEvent, uninitialized_TickEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(LoopEvent.class), "<init>", "()V", false));
                //初始化对象
                // {this} | {eventManager, TickEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // 执行方法, 所有元素出栈
                // {this} | {}
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

}
