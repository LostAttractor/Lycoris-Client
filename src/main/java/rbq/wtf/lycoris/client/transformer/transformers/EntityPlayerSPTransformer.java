package rbq.wtf.lycoris.client.transformer.transformers;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.*;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.EntityPlayerSP;

public class EntityPlayerSPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayerSP.EntityPlayerSPClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityPlayerSP.onUpdateWalkingPlayer.getName())) {
                InsnList insnList = new InsnList();
                // {this} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                // {this} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(MotionEvent.class)));
                // {this} | {eventManager, uninitialized_MotionEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this} | {eventManager, uninitialized_MotionEvent, uninitialized_MotionEvent}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(EventState.class), "PRE", "L" + Type.getInternalName(EventState.class) + ";"));
                // {this} | {eventManager, uninitialized_MotionEvent, uninitialized_MotionEvent, EventState.PRE}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(MotionEvent.class), "<init>", "(L" + Type.getInternalName(EventState.class) + ";)V", false));
                // {this} | {eventManager, MotionEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // {this} | {}
                method.instructions.insert(insnList);
            }
            if (method.name.equals(EntityPlayerSP.onUpdateWalkingPlayer.getName())) {
                InsnList insnList = new InsnList();
                // {this} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                // {this} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(MotionEvent.class)));
                // {this} | {eventManager, uninitialized_MotionEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this} | {eventManager, uninitialized_MotionEvent, uninitialized_MotionEvent}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(EventState.class), "POST", "L" + Type.getInternalName(EventState.class) + ";"));
                // {this} | {eventManager, uninitialized_MotionEvent, uninitialized_MotionEvent, EventState.PRE}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(MotionEvent.class), "<init>", "(L" + Type.getInternalName(EventState.class) + ";)V", false));
                // {this} | {eventManager, MotionEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // {this} | {}
                method.instructions.add(insnList);
            }
            if (method.name.equalsIgnoreCase(EntityPlayerSP.onLivingUpdate.getName())) {
                InsnList insnList = new InsnList();
                // {this} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                // {this} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(UpdateEvent.class)));
                // {this} | {eventManager, uninitialized_UpdateEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this} | {eventManager, uninitialized_UpdateEvent, uninitialized_UpdateEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(UpdateEvent.class), "<init>", "()V", false));
                // {this} | {eventManager, UpdateEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // {this} | {}
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

    /* 用于在EntityPlayerSP中加入moveEntity方法的asm transform
    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        cr.accept(cw, 0);
        MethodVisitor methodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "moveEntity", "(DDD)V", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(EventMove.class));
        methodVisitor.visitInsn(Opcodes.DUP);
        insnList.add(new VarInsnNode(Opcodes.DLOAD, 1);
        insnList.add(new VarInsnNode(Opcodes.DLOAD, 3);
        insnList.add(new VarInsnNode(Opcodes.DLOAD, 5);
        insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventMove.class), "<init>", "(DDD)V", false);
        insnList.add(new VarInsnNode(Opcodes.ASTORE, 7);
        Label label1 = new Label();
        methodVisitor.visitLabel(label1);
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 7);
        insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventManager.class), "call", "(L" + Type.getInternalName(Event.class) + ";)L" + Type.getInternalName(Event.class) + ";", false);
        methodVisitor.visitInsn(Opcodes.POP);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0);
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 7);
        insnList.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EventMove.class), "x", "D");
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 7);
        insnList.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EventMove.class), "y", "D");
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 7);
        insnList.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EventMove.class), "z", "D");
        insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(Entity.EntityClass), "moveEntity", "(DDD)V", false);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitInsn(Opcodes.RETURN);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLocalVariable("this", "L" + Type.getInternalName(EntityPlayerSP.class) + ";", null, label0, label4, 0);
        methodVisitor.visitLocalVariable("x", "D", null, label0, label4, 1);
        methodVisitor.visitLocalVariable("y", "D", null, label0, label4, 3);
        methodVisitor.visitLocalVariable("z", "D", null, label0, label4, 5);
        methodVisitor.visitLocalVariable("event", "L" + Type.getInternalName(EventMove.class) + ";", null, label1, label4, 7);
        methodVisitor.visitMaxs(8, 8);
        methodVisitor.visitEnd();
        return cw.toByteArray();
    }
     */
}
