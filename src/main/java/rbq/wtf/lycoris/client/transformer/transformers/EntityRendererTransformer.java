package rbq.wtf.lycoris.client.transformer.transformers;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.Event;
import rbq.wtf.lycoris.client.event.EventManager;
import rbq.wtf.lycoris.client.event.Render3DEvent;
import rbq.wtf.lycoris.client.transformer.ClassTransformer;
import rbq.wtf.lycoris.client.wrapper.wrappers.render.EntityRenderer;

public class EntityRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityRenderer.EntityRendererClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityRenderer.renderWorldPass.getName()) && method.desc.equals("(IFJ)V")) {
                InsnList insnList = new InsnList();
                // {this, scaledResolution, partialTicks} | {}
                insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, Type.getInternalName(Client.class), "eventManager", "L" + Type.getInternalName(EventManager.class) + ";"));
                // {this, scaledResolution, partialTicks} | {eventManager}
                insnList.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(Render3DEvent.class)));
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render3DEvent}
                insnList.add(new InsnNode(Opcodes.DUP));
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render3DEvent, uninitialized_Render3DEvent}
                insnList.add(new VarInsnNode(Opcodes.FLOAD, 2));
                // {this, scaledResolution, partialTicks} | {eventManager, uninitialized_Render3DEvent, uninitialized_Render3DEvent, float_partialTicks}
                insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(Render3DEvent.class), "<init>", "(F)V", false));
                // {this, scaledResolution, partialTicks} | {eventManager, Render3DEvent}
                insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventManager.class), "callEvent", "(L" + Type.getInternalName(Event.class) + ";)V", false));
                // {this, scaledResolution, partialTicks} | {}
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
