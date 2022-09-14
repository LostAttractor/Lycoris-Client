package rbq.wtf.lycoris.client.transformer.transformers

import rbq.wtf.lycoris.agent.asm.ClassReader
import rbq.wtf.lycoris.agent.asm.ClassWriter
import rbq.wtf.lycoris.agent.asm.Opcodes
import rbq.wtf.lycoris.agent.asm.Type
import rbq.wtf.lycoris.agent.asm.tree.*
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.event.Event
import rbq.wtf.lycoris.client.event.EventManager
import rbq.wtf.lycoris.client.event.MouseOverPostEvent
import rbq.wtf.lycoris.client.event.Render3DEvent
import rbq.wtf.lycoris.client.transformer.ClassTransformer
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.wrapper.wrappers.render.EntityRenderer

class EntityRendererTransformer : ClassTransformer() {

    override val targetClass: Class<*>
        get() = EntityRenderer.wrapClass

    override fun transform(bytes: ByteArray): ByteArray {
        val cr = ClassReader(bytes)
        val classNode = ClassNode()
        cr.accept(classNode, 0)
        for (method in classNode.methods) {
            if (method.name == EntityRenderer.renderWorldPass.name && method.desc == "(IFJ)V") {
                val insnList = InsnList()
                // {this, scaledResolution, partialTicks} | {}
                insnList.add(FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client::class.java), "eventManager", "L${Type.getInternalName(EventManager::class.java)};"))
                // {this, scaledResolution, partialTicks} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(Render3DEvent::class.java)))
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render3DEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render3DEvent, uninitialized_Render3DEvent}
                insnList.add(VarInsnNode(Opcodes.FLOAD, 2))
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render3DEvent, uninitialized_Render3DEvent, float_partialTicks}
                insnList.add(MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(Render3DEvent::class.java), "<init>", "(F)V", false))
                // {this, scaledResolution, partialTicks} | {eventManager, Render3DEvent}
                insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager::class.java), "callEvent", "(L${Type.getInternalName(Event::class.java)};)V", false))
                // {this, scaledResolution, partialTicks} | {}
                method.instructions.insert(insnList)
            }
            if (method.name == EntityRenderer.getMouseOver.name) {
                val insnList = InsnList()
                // {this, partialTicks} | {}
                insnList.add(FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client::class.java), "eventManager", "L${Type.getInternalName(EventManager::class.java)};"))
                // {this, partialTicks} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(MouseOverPostEvent::class.java)))
                // {this, partialTicks} | {eventManager, uninitialized_MouseOverPostEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, partialTicks} | {eventManager, uninitialized_MouseOverPostEvent, uninitialized_MouseOverPostEvent}
                insnList.add(VarInsnNode(Opcodes.FLOAD, 1))
                // {this, partialTicks} | {eventManager, uninitialized_MouseOverPostEvent, uninitialized_MouseOverPostEvent, float_partialTicks}
                insnList.add(MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(MouseOverPostEvent::class.java), "<init>", "(F)V", false))
                // {this, partialTicks} | {eventManager, MouseOverPostEvent}
                insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager::class.java), "callEvent", "(L${Type.getInternalName(Event::class.java)};)V", false))
                // {this, partialTicks} | {}
                method.instructions.insertBefore(method.instructions.last.previous, insnList)
            }
        }
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES)
        classNode.accept(cw)
        return cw.toByteArray()
    }
}