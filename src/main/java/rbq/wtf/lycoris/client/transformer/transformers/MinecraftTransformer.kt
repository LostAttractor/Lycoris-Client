package rbq.wtf.lycoris.client.transformer.transformers

import net.minecraftforge.fml.common.gameevent.TickEvent
import rbq.wtf.lycoris.agent.asm.ClassReader
import rbq.wtf.lycoris.agent.asm.ClassWriter
import rbq.wtf.lycoris.agent.asm.Opcodes
import rbq.wtf.lycoris.agent.asm.Type
import rbq.wtf.lycoris.agent.asm.tree.*
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.event.Event
import rbq.wtf.lycoris.client.event.EventManager
import rbq.wtf.lycoris.client.event.LoopEvent
import rbq.wtf.lycoris.client.transformer.ClassTransformer
import rbq.wtf.lycoris.client.wrapper.wrappers.Minecraft

class MinecraftTransformer : ClassTransformer() {

    override val targetClass: Class<*>
        get() = Minecraft.wrapClass;

    override fun transform(bytes: ByteArray): ByteArray {
        val cr = ClassReader(bytes)
        val classNode = ClassNode()
        cr.accept(classNode, 0)
        for (method in classNode.methods) {
            if (method.name.equals(Minecraft.runTick.name)) {
                val insnList = InsnList()
                // {this} | {}
                insnList.add(FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client::class.java), "eventManager", "L${Type.getInternalName(EventManager::class.java)};"))
                //获取Client.eventManager
                // {this} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(TickEvent::class.java)))
                //新建对象但未初始化
                // {this} | {eventManager, uninitialized_TickEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                //入栈
                // {this} | {eventManager, uninitialized_TickEvent, uninitialized_TickEvent}
                insnList.add(MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(TickEvent::class.java), "<init>", "()V", false))
                //初始化对象
                // {this} | {eventManager, TickEvent}
                insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager::class.java), "callEvent", "(L${Type.getInternalName(Event::class.java)};)V", false))
                // 执行方法, 所有元素出栈
                // {this} | {}
                //method.instructions.insert(insnList);
            }
            if (method.name.equals(Minecraft.runGameLoop.name)) {
                val insnList = InsnList()
                // {this} | {}
                insnList.add(FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client::class.java), "eventManager", "L${Type.getInternalName(EventManager::class.java)};"))
                //获取Client.eventManager
                // {this} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(LoopEvent::class.java)))
                //新建对象但未初始化
                // {this} | {eventManager, uninitialized_TickEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                //入栈
                // {this} | {eventManager, uninitialized_TickEvent, uninitialized_TickEvent}
                insnList.add(MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(LoopEvent::class.java), "<init>", "()V", false))
                //初始化对象
                // {this} | {eventManager, TickEvent}
                insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager::class.java), "callEvent", "(L${Type.getInternalName(Event::class.java)};)V", false))
                // 执行方法, 所有元素出栈
                // {this} | {}
                //method.instructions.insert(insnList);
            }
        }
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES)
        classNode.accept(cw)
        return cw.toByteArray()
    }
}