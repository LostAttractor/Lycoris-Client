package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.Event;
import rbq.wtf.lycoris.client.event.EventManager;
import rbq.wtf.lycoris.client.event.KeyEvent;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.KeyBinding;


public class KeyBindingTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return KeyBinding.wrapClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(KeyBinding.onTick.getName()) && method.desc.equalsIgnoreCase("(I)V")) {
                InsnList insnList = new InsnList();
                // Static Method //
                // {int} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                //获取Client.eventManager
                // {int} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(KeyEvent.class)));
                //新建对象但未初始化
                // {int} | {eventManager, uninitialized_KeyEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                //入栈
                // {int} | {eventManager, uninitialized_KeyEvent, uninitialized_KeyEvent}
                insnList.add(new VarInsnNode(Opcodes.ILOAD, 0));
                // {int} | {eventManager, uninitialized_KeyEvent, uninitialized_KeyEvent, int}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(KeyEvent.class), "<init>", "(I)V", false));
                //初始化对象
                // {int} | {eventManager, KeyEvent, ini}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // 执行方法, 所有元素出栈
                // {int} | {}
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
