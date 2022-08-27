package rbq.wtf.lycoris.client.transformer.transformers

import rbq.wtf.lycoris.agent.asm.ClassReader
import rbq.wtf.lycoris.agent.asm.ClassWriter
import rbq.wtf.lycoris.agent.asm.Opcodes
import rbq.wtf.lycoris.agent.asm.Type
import rbq.wtf.lycoris.agent.asm.tree.*
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.event.Event
import rbq.wtf.lycoris.client.event.EventManager
import rbq.wtf.lycoris.client.event.KeyEvent
import rbq.wtf.lycoris.client.transformer.ClassTransformer
import rbq.wtf.lycoris.client.wrapper.wrappers.KeyBinding

class KeyBindingTransformer : ClassTransformer() {

    override val targetClass: Class<*>
        get() = KeyBinding.wrapClass

    override fun transform(bytes: ByteArray): ByteArray {
        val cr = ClassReader(bytes)
        val classNode = ClassNode()
        cr.accept(classNode, 0)
        for (method in classNode.methods) {
            if (method.name == KeyBinding.onTick.name && method.desc.equals("(I)V", ignoreCase = true)) {
                val insnList = InsnList()
                // Static Method //
                // {int} | {}
                insnList.add(FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client::class.java), "eventManager", "L${Type.getInternalName(EventManager::class.java)};"))
                //获取Client.eventManager
                // {int} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(KeyEvent::class.java)))
                //新建对象但未初始化
                // {int} | {eventManager, uninitialized_KeyEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                //入栈
                // {int} | {eventManager, uninitialized_KeyEvent, uninitialized_KeyEvent}
                insnList.add(VarInsnNode(Opcodes.ILOAD, 0))
                // {int} | {eventManager, uninitialized_KeyEvent, uninitialized_KeyEvent, int}
                insnList.add(MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(KeyEvent::class.java), "<init>", "(I)V", false))
                //初始化对象
                // {int} | {eventManager, KeyEvent, ini}
                insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager::class.java), "callEvent", "(L${Type.getInternalName(Event::class.java)};)V", false))
                // 执行方法, 所有元素出栈
                // {int} | {}
                method.instructions.insert(insnList)
            }
        }
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES)
        classNode.accept(cw)
        return cw.toByteArray()
    }
}