package rbq.wtf.lycoris.client.wrapper.bridge;

import rbq.wtf.lycoris.agent.asm.*;
import rbq.wtf.lycoris.client.detector.MargeleAntiCheatDetector;
import rbq.wtf.lycoris.client.wrapper.wrappers.impl.GuiScreenImpl;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.GuiChat;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.GuiScreen;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.IGuiScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BridgeUtil {
    public static Class<?> GuiScreenBridge = null;
    public static Class<?> GuiChatReplace = null;
    public static Class<?> MargeleAntiCheatAccessor = null;

    public static void init() {
        try {
            Method classloader = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
            classloader.setAccessible(true);
            byte[] buf = buildGuiScreenBridge("rbq.wtf.lycoris.client.wrapper.bridge.accessor.GuiScreenBridge");
            GuiScreenBridge = (Class) classloader.invoke(BridgeUtil.class.getClassLoader(), null, buf, 0, buf.length);

            buf = buildGuiChatBridge("rbq.wtf.lycoris.client.wrapper.bridge.accessor.GuiCommandReplace");
            GuiChatReplace = (Class) classloader.invoke(BridgeUtil.class.getClassLoader(), null, buf, 0, buf.length);
            if (MargeleAntiCheatDetector.isMAC()) {
                buf = buildMargeleAntiCheatAccessor("rbq.wtf.lycoris.client.wrapper.bridge.accessor.MargeleAntiCheatAccessor");
                MargeleAntiCheatAccessor = (Class) classloader.invoke(BridgeUtil.class.getClassLoader(), null, buf, 0, buf.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static IGuiScreen createGuiScreen(GuiScreenImpl guiScreen) {
        try {
            Constructor<?> constructor = GuiScreenBridge.getConstructor(GuiScreenImpl.class);
            return new IGuiScreen(constructor.newInstance(guiScreen));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
            throw new NullPointerException("Failed to create GuiScreen");
        }
    }

    public static IGuiScreen createGuiChat(GuiScreenImpl guiScreen) {
        try {
            Constructor<?> constructor = GuiChatReplace.getConstructor(GuiScreenImpl.class);
            return new IGuiScreen(constructor.newInstance(guiScreen));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
            throw new NullPointerException("Failed to create GuiScreen");
        }
    }

    public static byte[] buildMargeleAntiCheatAccessor(String pkg) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, pkg.replace(".", "/")
                , null, Object.class.getCanonicalName().replace(".", "/"), new String[]{});
        MethodVisitor setCheatVL = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "setCheatVL", "(I)V", null, null);
        setCheatVL.visitVarInsn(Opcodes.ILOAD, 0);
        setCheatVL.visitFieldInsn(Opcodes.PUTSTATIC, Type.getInternalName(MargeleAntiCheatDetector.getMAC()), "cheatingVl", "I");
        setCheatVL.visitInsn(Opcodes.RETURN);
        //setCheatVL.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(MargeleAntiCheatDetector.getMAC()),"setCheatVL","(I)V",false);
        setCheatVL.visitEnd();
        MethodVisitor getCheatVL = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "getCheatVL", "()I", null, null);
        getCheatVL.visitFieldInsn(Opcodes.GETSTATIC, Type.getInternalName(MargeleAntiCheatDetector.getMAC()), "cheatingVl", "I");
        getCheatVL.visitInsn(Opcodes.IRETURN);
        //setCheatVL.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(MargeleAntiCheatDetector.getMAC()),"setCheatVL","(I)V",false);
        getCheatVL.visitEnd();
        return cw.toByteArray();
    }

    public static byte[] buildGuiChatBridge(String pkg) {
        return buildGuiScreenBridge(GuiChat.GuiChatClass, pkg);
    }

    public static byte[] buildGuiScreenBridge(String pkg) {
        return buildGuiScreenBridge(GuiScreen.GuiScreenClass, pkg);
    }

    public static byte[] buildGuiScreenBridge(Class superClass, String pkg) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        String wrappedScreen = "wrappedScreen";
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, pkg.replace(".", "/")
                , null, superClass.getCanonicalName().replace(".", "/"), new String[]{});
        cw.visitField(Opcodes.ACC_PRIVATE, wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";", null, null);
        MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(L" + Type.getInternalName(GuiScreenImpl.class) + ";)V", null, null);
        //    ALOAD 0
        //    INVOKESPECIAL java/lang/Object.<init> ()V
        //    ALOAD 0
        //    ALOAD 1
        //    PUTFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    RETURN
        //    MAXSTACK = 2
        //    MAXLOCALS = 2

        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitMethodInsn(Opcodes.INVOKESPECIAL, superClass.getCanonicalName().replace(".", "/"), "<init>", "()V");
        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitVarInsn(Opcodes.ALOAD, 1);
        init.visitFieldInsn(Opcodes.PUTFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        init.visitInsn(Opcodes.RETURN);

        init.visitMaxs(2, 2);
        init.visitEnd();
        MethodVisitor drawScreen = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.drawScreen.getName(), "(IIF)V", null, null);

        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    ILOAD 1
        //    ILOAD 2
        //    FLOAD 3
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.drawScreen (IIF)V
        //    RETURN
        drawScreen.visitVarInsn(Opcodes.ALOAD, 0);
        drawScreen.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        drawScreen.visitVarInsn(Opcodes.ILOAD, 1);
        drawScreen.visitVarInsn(Opcodes.ILOAD, 2);
        drawScreen.visitVarInsn(Opcodes.FLOAD, 3);
        drawScreen.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "drawScreen", "(IIF)V");
        drawScreen.visitInsn(Opcodes.RETURN);

        drawScreen.visitMaxs(4, 4);
        drawScreen.visitEnd();

        MethodVisitor initGui = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.initGui.getName(), "()V", null, null);
        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.initGui ()V
        //    RETURN
        initGui.visitVarInsn(Opcodes.ALOAD, 0);
        initGui.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        initGui.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "initGui", "()V");
        initGui.visitInsn(Opcodes.RETURN);

        initGui.visitMaxs(1, 1);
        initGui.visitEnd();
        MethodVisitor onGuiClosed = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.onGuiClosed.getName(), "()V", null, null);
        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.onGuiClosed ()V
        //    RETURN
        onGuiClosed.visitVarInsn(Opcodes.ALOAD, 0);
        onGuiClosed.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        onGuiClosed.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "onGuiClosed", "()V");
        onGuiClosed.visitInsn(Opcodes.RETURN);

        onGuiClosed.visitMaxs(1, 1);
        onGuiClosed.visitEnd();
        MethodVisitor keyTyped = cw.visitMethod(Opcodes.ACC_PROTECTED, GuiScreen.keyTyped.getName(), "(CI)V", null, null);
        //ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    ILOAD 1
        //    ILOAD 2
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.keyTyped (CI)Z
        //    IFEQ L0
        //    ALOAD 0
        //    ILOAD 1
        //    ILOAD 2
        //    INVOKESPECIAL al/nya/reflect/wrapper/wraps/wrapper/gui/GuiScreen.keyTyped (CI)V
        //   L0
        //   FRAME SAME
        //    RETURN
        Label L0 = new Label();
        keyTyped.visitVarInsn(Opcodes.ALOAD, 0);
        keyTyped.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        keyTyped.visitVarInsn(Opcodes.ILOAD, 1);
        keyTyped.visitVarInsn(Opcodes.ILOAD, 2);
        keyTyped.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "keyTyped", "(CI)Z");
        keyTyped.visitJumpInsn(Opcodes.IFEQ, L0);
        keyTyped.visitVarInsn(Opcodes.ALOAD, 0);
        keyTyped.visitVarInsn(Opcodes.ILOAD, 1);
        keyTyped.visitVarInsn(Opcodes.ILOAD, 2);
        keyTyped.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass), GuiScreen.keyTyped.getName(), "(CI)V");
        keyTyped.visitLabel(L0);
        keyTyped.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        keyTyped.visitInsn(Opcodes.RETURN);

        keyTyped.visitMaxs(3, 3);
        keyTyped.visitEnd();


        MethodVisitor mouseReleased = cw.visitMethod(Opcodes.ACC_PROTECTED, GuiScreen.mouseReleased.getName(), "(III)V", null, null);
        mouseReleased.visitVarInsn(Opcodes.ALOAD, 0);
        mouseReleased.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        mouseReleased.visitVarInsn(Opcodes.ILOAD, 1);
        mouseReleased.visitVarInsn(Opcodes.ILOAD, 2);
        mouseReleased.visitVarInsn(Opcodes.ILOAD, 3);
        mouseReleased.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "mouseReleased", "(III)V");
        mouseReleased.visitInsn(Opcodes.RETURN);

        mouseReleased.visitMaxs(4, 4);
        mouseReleased.visitEnd();


        MethodVisitor mouseClicked = cw.visitMethod(Opcodes.ACC_PROTECTED, GuiScreen.mouseClicked.getName(), "(III)V", null, null);
        mouseClicked.visitVarInsn(Opcodes.ALOAD, 0);
        mouseClicked.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        mouseClicked.visitVarInsn(Opcodes.ILOAD, 1);
        mouseClicked.visitVarInsn(Opcodes.ILOAD, 2);
        mouseClicked.visitVarInsn(Opcodes.ILOAD, 3);
        mouseClicked.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "mouseClicked", "(III)V");
        mouseClicked.visitInsn(Opcodes.RETURN);

        mouseClicked.visitMaxs(4, 4);
        mouseClicked.visitEnd();
        MethodVisitor updateScreen = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.updateScreen.getName(), "()V", null, null);
        //    ALOAD 0
        //    GETFIELD al/nya/reflect/wrapper/bridge/BridgeExample.guiScreen : Lal/nya/reflect/wrapper/wraps/impl/GuiScreenImpl;
        //    INVOKEVIRTUAL al/nya/reflect/wrapper/wraps/impl/GuiScreenImpl.updateScreen ()V
        //    RETURN
        updateScreen.visitVarInsn(Opcodes.ALOAD, 0);
        updateScreen.visitFieldInsn(Opcodes.GETFIELD, pkg.replace(".", "/"), wrappedScreen, "L" + Type.getInternalName(GuiScreenImpl.class) + ";");
        updateScreen.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreenImpl.class), "updateScreen", "()V");
        updateScreen.visitInsn(Opcodes.RETURN);

        updateScreen.visitMaxs(1, 1);
        updateScreen.visitEnd();
        return cw.toByteArray();
    }
}
