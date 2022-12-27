package rbq.lycoris.client.transformer.transformers

import rbq.lycoris.agent.asm.ClassReader
import rbq.lycoris.agent.asm.ClassWriter
import rbq.lycoris.agent.asm.Opcodes
import rbq.lycoris.agent.asm.Type
import rbq.lycoris.agent.asm.tree.*
import rbq.lycoris.client.Client
import rbq.lycoris.client.event.Event
import rbq.lycoris.client.event.EventManager
import rbq.lycoris.client.event.LoopEvent
import rbq.lycoris.client.event.TickEvent
import rbq.lycoris.client.transformer.ClassTransformer
import rbq.lycoris.client.wrapper.wrappers.Minecraft

class MinecraftTransformer : ClassTransformer() {

    override val targetClass: Class<*>
        get() = Minecraft.wrapClass;

    override fun transform(bytes: ByteArray): ByteArray {
        val cr = ClassReader(bytes)
        val classNode = ClassNode()
        cr.accept(classNode, 0)
        for (method in classNode.methods) {
            if (method.name == Minecraft.runTick.name) {
                val insnList = InsnList()
                // {this} | {}
                insnList.add(
                    FieldInsnNode(
                        Opcodes.GETSTATIC,
                        Type.getInternalName(Client::class.java),
                        "eventManager",
                        "L${Type.getInternalName(EventManager::class.java)};"
                    )
                )
                //获取Client.eventManager
                // {this} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(TickEvent::class.java)))
                //新建对象但未初始化
                // {this} | {eventManager, uninitialized_TickEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                //入栈
                // {this} | {eventManager, uninitialized_TickEvent, uninitialized_TickEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(TickEvent::class.java),
                        "<init>",
                        "()V",
                        false
                    )
                )
                //初始化对象
                // {this} | {eventManager, TickEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(EventManager::class.java),
                        "callEvent",
                        "(L${Type.getInternalName(Event::class.java)};)V",
                        false
                    )
                )
                // 执行方法, 所有元素出栈
                // {this} | {}
                method.instructions.insert(insnList);
            }
            if (method.name == Minecraft.runGameLoop.name) {
                val insnList = InsnList()
                // {this} | {}
                insnList.add(
                    FieldInsnNode(
                        Opcodes.GETSTATIC,
                        Type.getInternalName(Client::class.java),
                        "eventManager",
                        "L${Type.getInternalName(EventManager::class.java)};"
                    )
                )
                //获取Client.eventManager
                // {this} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(LoopEvent::class.java)))
                //新建对象但未初始化
                // {this} | {eventManager, uninitialized_LoopEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                //入栈
                // {this} | {eventManager, uninitialized_LoopEvent, uninitialized_LoopEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(LoopEvent::class.java),
                        "<init>",
                        "()V",
                        false
                    )
                )
                //初始化对象
                // {this} | {eventManager, LoopEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(EventManager::class.java),
                        "callEvent",
                        "(L${Type.getInternalName(Event::class.java)};)V",
                        false
                    )
                )
                // 执行方法, 所有元素出栈
                // {this} | {}
                method.instructions.insert(insnList);
            }
        }
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES)
        classNode.accept(cw)
        return cw.toByteArray()
    }
}