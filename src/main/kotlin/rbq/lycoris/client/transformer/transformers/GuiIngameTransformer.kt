package rbq.lycoris.client.transformer.transformers

import rbq.lycoris.agent.asm.ClassReader
import rbq.lycoris.agent.asm.ClassWriter
import rbq.lycoris.agent.asm.Opcodes
import rbq.lycoris.agent.asm.Type
import rbq.lycoris.agent.asm.tree.*
import rbq.lycoris.client.Client
import rbq.lycoris.client.event.Event
import rbq.lycoris.client.event.EventManager
import rbq.lycoris.client.event.Render2DEvent
import rbq.lycoris.client.transformer.ClassTransformer
import rbq.lycoris.client.wrapper.wrappers.gui.GuiIngame
import rbq.lycoris.client.wrapper.wrappers.gui.ScaledResolution

class GuiIngameTransformer : ClassTransformer() {

    override val targetClass: Class<*>
        get() = GuiIngame.wrapClass

    override fun transform(bytes: ByteArray): ByteArray {
        val cr = ClassReader(bytes)
        val classNode = ClassNode()
        cr.accept(classNode, 0)
        for (method in classNode.methods) {
            if (method.name == GuiIngame.renderTooltip.name && method.desc.equals(
                    "(L${Type.getInternalName(ScaledResolution.wrapClass)};F)V", ignoreCase = true
                )
            ) {
                val insnList = InsnList()
                // {this, scaledResolution, partialTicks} | {}
                insnList.add(
                    FieldInsnNode(
                        Opcodes.GETSTATIC,
                        Type.getInternalName(Client::class.java),
                        "eventManager",
                        "L${Type.getInternalName(EventManager::class.java)};"
                    )
                )
                // {this, scaledResolution, partialTicks} | {eventManager}
                insnList.add(TypeInsnNode(Opcodes.NEW, Type.getInternalName(Render2DEvent::class.java)))
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render2DEvent}
                insnList.add(InsnNode(Opcodes.DUP))
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render2DEvent, uninitialized_Render2DEvent}
                insnList.add(VarInsnNode(Opcodes.FLOAD, 2))
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render2DEvent, uninitialized_Render2DEvent, float_partialTicks}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        Type.getInternalName(Render2DEvent::class.java),
                        "<init>",
                        "(F)V",
                        false
                    )
                )
                // {this, scaledResolution, partialTicks} | {eventManager, Render2DEvent}
                insnList.add(
                    MethodInsnNode(
                        Opcodes.INVOKEVIRTUAL,
                        Type.getInternalName(EventManager::class.java),
                        "callEvent",
                        "(L${Type.getInternalName(Event::class.java)};)V",
                        false
                    )
                )
                // {this, scaledResolution, partialTicks} | {}
                method.instructions.insert(insnList)
            }
        }
        val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES)
        classNode.accept(cw)
        return cw.toByteArray()
    }
}