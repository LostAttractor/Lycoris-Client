package rbq.lycoris.client.transformer.transformers

import rbq.lycoris.agent.asm.ClassReader
import rbq.lycoris.agent.asm.ClassWriter
import rbq.lycoris.agent.asm.Opcodes
import rbq.lycoris.agent.asm.Type
import rbq.lycoris.agent.asm.tree.*
import rbq.lycoris.client.Client
import rbq.lycoris.client.event.Event
import rbq.lycoris.client.event.EventManager
import rbq.lycoris.client.event.PacketReceiveEvent
import rbq.lycoris.client.event.PacketSendEvent
import rbq.lycoris.client.transformer.ClassTransformer
import rbq.lycoris.client.wrapper.wrappers.network.NetworkManager
import rbq.lycoris.client.wrapper.wrappers.network.Packet

class NetworkManagerTransformer : ClassTransformer() {

    override val targetClass: Class<*>
        get() = NetworkManager.wrapClass

    override fun transform(bytes: ByteArray): ByteArray {
        val cr = ClassReader(bytes)
        val classNode = ClassNode()
        cr.accept(classNode, 0)
        for (method in classNode.methods) {
            if (method.name == NetworkManager.sendPacket.name) {
                val insnList = InsnList()
                // {this, Packet} | {}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(PacketSendEvent::class.java)))
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(Packet::class.java)))
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper}
                insnList.add(VarInsnNode(Opcodes.ALOAD, 1))
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper, Packet}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(Packet::class.java),
                        "<init>",
                        "(Ljava/lang/Object;)V",
                        false
                    )
                )
                // {this, Packet} | {uninitialized_PacketSendEvent, uninitialized_PacketSendEvent, PacketWrapper}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(PacketSendEvent::class.java),
                        "<init>",
                        "(L${Type.getInternalName(Packet::class.java)};)V",
                        false
                    )
                )
                // {this, Packet} | {PacketSendEvent}
                insnList.add(VarInsnNode(Opcodes.ASTORE, 2))
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(
                    FieldInsnNode(
                        Opcodes.GETSTATIC,
                        Type.getInternalName(Client::class.java),
                        "eventManager",
                        "L${Type.getInternalName(EventManager::class.java)};"
                    )
                )
                // {this, Packet, PacketSendEvent} | {eventManager}
                insnList.add(VarInsnNode(Opcodes.ALOAD, 2))
                // {this, Packet, PacketSendEvent} | {eventManager, PacketSendEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(EventManager::class.java),
                        "callEvent",
                        "(L${Type.getInternalName(Event::class.java)};)V",
                        false
                    )
                )
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(VarInsnNode(Opcodes.ALOAD, 2))
                // {this, Packet, PacketSendEvent} | {PacketSendEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(PacketSendEvent::class.java),
                        "isCancelled",
                        "()Z",
                        false
                    )
                )
                // {this, Packet, PacketSendEvent} | {Boolean.isCancelled}
                val continueLabel = LabelNode()
                insnList.add(JumpInsnNode(Opcodes.IFEQ, continueLabel))
                //如果没有被取消，即isCancelled == 0(false) jump到continueLabel
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(InsnNode(Opcodes.RETURN))
                //结束代码
                // {this, Packet, PacketSendEvent} | {}
                insnList.add(continueLabel)
                //继续
                method.instructions.insert(insnList)
            }
            if (method.name == NetworkManager.channelRead0.name) {
                val insnList = InsnList()
                // {this, ChannelHandlerContext, Packet} | {}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(PacketReceiveEvent::class.java)))
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(Packet::class.java)))
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper}
                insnList.add(VarInsnNode(Opcodes.ALOAD, 2))
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent, uninitialized_PacketWrapper, uninitialized_PacketWrapper, Packet}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(Packet::class.java),
                        "<init>",
                        "(Ljava/lang/Object;)V",
                        false
                    )
                )
                // {this, ChannelHandlerContext, Packet} | {uninitialized_PacketReceiveEvent, uninitialized_PacketReceiveEvent, PacketWrapper}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(PacketReceiveEvent::class.java),
                        "<init>",
                        "(L${Type.getInternalName(Packet::class.java)};)V",
                        false
                    )
                )
                // {this, ChannelHandlerContext, Packet} | {PacketReceiveEvent}
                insnList.add(VarInsnNode(Opcodes.ASTORE, 3))
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {}
                insnList.add(
                    FieldInsnNode(
                        Opcodes.GETSTATIC,
                        Type.getInternalName(Client::class.java),
                        "eventManager",
                        "L${Type.getInternalName(EventManager::class.java)};"
                    )
                )
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {eventManager}
                insnList.add(VarInsnNode(Opcodes.ALOAD, 3))
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {eventManager, PacketReceiveEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(EventManager::class.java),
                        "callEvent",
                        "(L${Type.getInternalName(Event::class.java)};)V",
                        false
                    )
                )
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {}
                insnList.add(VarInsnNode(Opcodes.ALOAD, 3))
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {PacketReceiveEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(PacketReceiveEvent::class.java),
                        "isCancelled",
                        "()Z",
                        false
                    )
                )
                // {this, ChannelHandlerContext, Packet, PacketReceiveEvent} | {Boolean.isCancelled}
                val continueLabel = LabelNode()
                insnList.add(JumpInsnNode(Opcodes.IFEQ, continueLabel))
                //如果没有被取消，即isCancelled == 0(false) jump到continueLabel
                // {this, Packet, PacketReceiveEvent} | {}
                insnList.add(InsnNode(Opcodes.RETURN))
                //结束代码
                // {this, Packet, PacketReceiveEvent} | {}
                insnList.add(continueLabel)
                //继续
                method.instructions.insert(insnList)
            }
        }
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES)
        classNode.accept(cw)
        return cw.toByteArray()
    }
}