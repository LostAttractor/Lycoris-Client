package rbq.wtf.lycoris.client.transformer.transformers;

import net.minecraft.client.gui.ScaledResolution;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.Type;
import rbq.wtf.lycoris.agent.asm.tree.*;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.event.events.EventRender2D;

public class GuiIngameTransformer {
    public static void transform(ClassNode clazz, MethodNode method) {
        if (method.name.equals("renderTooltip") && method.desc.equalsIgnoreCase("(L"+Type.getInternalName(ScaledResolution.class)+";F)V")) {
            InsnList insns = new InsnList();
            insns.add(new TypeInsnNode(Opcodes.NEW, Type.getInternalName(EventRender2D.class)));
            insns.add(new VarInsnNode(Opcodes.ALOAD,1));
            insns.add(new VarInsnNode(Opcodes.FLOAD,2));
            insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(Lavr;F)V", false));
            method.instructions.insert(insns);
        }
    }

}
