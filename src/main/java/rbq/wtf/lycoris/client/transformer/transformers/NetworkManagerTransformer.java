package rbq.wtf.lycoris.client.transformer.transformers;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.Event;
import rbq.wtf.lycoris.client.event.EventManager;
import rbq.wtf.lycoris.client.event.PacketReceiveEvent;
import rbq.wtf.lycoris.client.event.PacketSendEvent;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network.NetworkManager;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network.Packet;

public class NetworkManagerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return NetworkManager.NetworkManagerClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equalsIgnoreCase(NetworkManager.sendPacket.getName())) {
                InsnList insnList = new InsnList();
                // {this, Packet} | {}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(PacketSendEvent.class)));
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(Packet.class)));
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper}
                insnList.add(new VarInsnNode(Opcodes.ALOAD, 1));
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper, Packet}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(Packet.class), "<init>", "(Ljava/lang/Object;)V", false));
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent, PacketWrapper}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(PacketSendEvent.class), "<init>", "(L" + Type.getInternalName(Packet.class) +";)V", false));
                // {this, Packet} | {PacketSendEvent}
                insnList.add(new VarInsnNode(Opcodes.ASTORE, 2));
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                // {this, Packet, PacketSendEvent} | {eventManager}
                insnList.add(new VarInsnNode(Opcodes.ALOAD, 2));
                // {this, Packet, PacketSendEvent} | {eventManager, PacketSendEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(new VarInsnNode(Opcodes.ALOAD, 2));
                // {this, Packet, PacketSendEvent} | {PacketSendEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(PacketSendEvent.class), "isCancelled", "()Z", false));
                // {this, Packet, PacketSendEvent} | {Boolean.isCancelled}
                LabelNode continueLabel = new LabelNode();
                insnList.add(new JumpInsnNode(Opcodes.IFEQ, continueLabel));
                //如果没有被取消，即isCancelled == 0(false) jump到continueLabel
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(new InsnNode(Opcodes.RETURN));
                //结束代码
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(continueLabel);
                //继续
                method.instructions.insert(insnList);
            }
            if (method.name.equalsIgnoreCase(NetworkManager.channelRead0.getName())) {
                InsnList insnList = new InsnList();
                // {this, ChannelHandlerContext, Packet} | {}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(PacketReceiveEvent.class)));
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(Packet.class)));
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper}
                insnList.add(new VarInsnNode(Opcodes.ALOAD, 2));
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper, Packet}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(Packet.class), "<init>", "(Ljava/lang/Object;)V", false));
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent, PacketWrapper}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(PacketReceiveEvent.class), "<init>", "(L" + Type.getInternalName(Packet.class) +";)V", false));
                // {this, ChannelHandlerContext, Packet} | {PacketReceiveEvent}
                insnList.add(new VarInsnNode(Opcodes.ASTORE, 3));
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {eventManager}
                insnList.add(new VarInsnNode(Opcodes.ALOAD, 3));
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {eventManager, PacketReceiveEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {}
                insnList.add(new VarInsnNode(Opcodes.ALOAD, 3));
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {PacketReceiveEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(PacketReceiveEvent.class), "isCancelled", "()Z", false));
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {Boolean.isCancelled}
                LabelNode continueLabel = new LabelNode();
                insnList.add(new JumpInsnNode(Opcodes.IFEQ, continueLabel));
                //如果没有被取消，即isCancelled == 0(false) jump到continueLabel
                // {this, Packet, PacketReceiveEvent} | {}
                insnList.add(new InsnNode(Opcodes.RETURN));
                //结束代码
                // {this, Packet, PacketReceiveEvent} | {}
                insnList.add(continueLabel);
                //继续
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

}
