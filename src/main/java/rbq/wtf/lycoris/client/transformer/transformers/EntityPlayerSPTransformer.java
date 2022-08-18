package rbq.wtf.lycoris.client.transformer.transformers;

import rbq.wtf.lycoris.agent.asm.*;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.events.Event;
import rbq.wtf.lycoris.client.event.events.EventMotionUpdate;
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
                InsnList insnListPre = new InsnList();
                insnListPre.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventMotionUpdate.class)));
                insnListPre.add(new InsnNode(Opcodes.DUP));
                insnListPre.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPre.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "posX", "D"));
                insnListPre.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPre.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "posY", "D"));
                insnListPre.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPre.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "posZ", "D"));
                insnListPre.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPre.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "rotationYaw", "F"));
                insnListPre.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPre.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "rotationPitch", "F"));
                insnListPre.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPre.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "onGround", "Z"));
                insnListPre.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(EventMotionUpdate.class) + "$Type", "PRE", "L" + Type.getInternalName(EventMotionUpdate.class) + "$Type;"));
                insnListPre.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventMotionUpdate.class), "<init>", "(DDDFFZL" + Type.getInternalName(EventMotionUpdate.class) + "$Type;)V", false));
                insnListPre.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventManager.class), "call", "(L" + Type.getInternalName(Event.class) + ";)L" + Type.getInternalName(Event.class) + ";", false));
                insnListPre.add(new InsnNode(Opcodes.POP));
                method.instructions.insert(insnListPre);

                InsnList insnListPost = new InsnList();
                insnListPost.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventMotionUpdate.class)));
                insnListPost.add(new InsnNode(Opcodes.DUP));
                insnListPost.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPost.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "posX", "D"));
                insnListPost.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPost.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "posY", "D"));
                insnListPost.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPost.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "posZ", "D"));
                insnListPost.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPost.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "rotationYaw", "F"));
                insnListPost.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPost.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "rotationPitch", "F"));
                insnListPost.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insnListPost.add(new FieldInsnNode(Opcodes.GETFIELD, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass), "onGround", "Z"));
                insnListPost.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(EventMotionUpdate.class) + "$Type", "POST", "L" + Type.getInternalName(EventMotionUpdate.class) + "$Type;"));
                insnListPost.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventMotionUpdate.class), "<init>", "(DDDFFZL" + Type.getInternalName(EventMotionUpdate.class) + "$Type;)V", false));
                insnListPost.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventManager.class), "call", "(L" + Type.getInternalName(Event.class) + ";)L" + Type.getInternalName(Event.class) + ";", false));
                insnListPost.add(new InsnNode(Opcodes.POP));
                method.instructions.add(insnListPost);
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
