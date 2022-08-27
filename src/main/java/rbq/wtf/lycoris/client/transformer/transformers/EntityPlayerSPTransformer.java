package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.*;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.EntityPlayerSP;

public class EntityPlayerSPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayerSP.wrapClass;
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
    MethodVisitor methodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "moveEntity", "(DDD)V", null, null);
    methodVisitor.visitCode();
    methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    methodVisitor.visitLdcInsn("MoveEntity_SP");
    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    Label label0 = new Label();
    methodVisitor.visitLabel(label0);
    methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(MoveEvent.class));
    methodVisitor.visitInsn(Opcodes.DUP);
    methodVisitor.visitVarInsn(Opcodes.DLOAD, 1);
    methodVisitor.visitVarInsn(Opcodes.DLOAD, 3);
    methodVisitor.visitVarInsn(Opcodes.DLOAD, 5);
    methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(MoveEvent.class), "<init>", "(DDD)V", false);
    methodVisitor.visitVarInsn(Opcodes.ASTORE, 7);
    Label label1 = new Label();
    methodVisitor.visitLabel(label1);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 7);
    methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EventManager.class), "call", "(L" + Type.getInternalName(Event.class) + ";)L" + Type.getInternalName(Event.class) + ";", false);
    methodVisitor.visitInsn(Opcodes.POP);
    Label label2 = new Label();
    methodVisitor.visitLabel(label2);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 7);
    methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(MoveEvent.class), "x", "D");
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 7);
    methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(MoveEvent.class), "y", "D");
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 7);
    methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(MoveEvent.class), "z", "D");
    methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Entity.EntityClass), "moveEntity", "(DDD)V", false);
    Label label3 = new Label();
    methodVisitor.visitLabel(label3);
    methodVisitor.visitInsn(Opcodes.RETURN);
    Label label4 = new Label();
    methodVisitor.visitLabel(label4);
    methodVisitor.visitLocalVariable("this", "L" + Type.getInternalName(EntityPlayerSP.class) + ";", null, label0, label4, 0);
    methodVisitor.visitLocalVariable("x", "D", null, label0, label4, 1);
    methodVisitor.visitLocalVariable("y", "D", null, label0, label4, 3);
    methodVisitor.visitLocalVariable("z", "D", null, label0, label4, 5);
    methodVisitor.visitLocalVariable("event", "L" + Type.getInternalName(MoveEvent.class) + ";", null, label1, label4, 7);
    methodVisitor.visitMaxs(8, 8);
    methodVisitor.visitEnd();
     */
}
