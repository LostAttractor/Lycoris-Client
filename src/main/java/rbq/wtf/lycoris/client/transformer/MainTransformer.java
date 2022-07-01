package rbq.wtf.lycoris.client.transformer;


import rbq.wtf.lycoris.agent.asm.ClassReader;
import rbq.wtf.lycoris.agent.asm.ClassWriter;
import rbq.wtf.lycoris.agent.asm.Opcodes;
import rbq.wtf.lycoris.agent.asm.tree.ClassNode;
import rbq.wtf.lycoris.agent.asm.tree.MethodNode;
import rbq.wtf.lycoris.agent.instrument.ClassTransformer;

import rbq.wtf.lycoris.client.transformer.transformers.GuiIngameTransformer;
import rbq.wtf.lycoris.client.transformer.transformers.KeyBindingTransformer;
import rbq.wtf.lycoris.client.transformer.transformers.MinecraftTransformer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class MainTransformer implements ClassTransformer, Opcodes {

    public static Set<String> classNameSet;

    static {
        classNameSet = new HashSet<String>();
        String[] nameArray = new String[]{
                OBFMap.getString("net.minecraft.client.Minecraft").replaceAll(".","/"),
                OBFMap.getString("net.minecraft.client.settings.KeyBinding").replaceAll(".","/"),
                OBFMap.getString("net.minecraft.client.gui.GuiIngame").replaceAll(".","/")
        };

        for (int i = 0; i < nameArray.length; i++) {
            classNameSet.add(nameArray[i]);
        }
    }

    public static boolean needTransform(String name) {
        return classNameSet.contains(name);
    }



    private byte[] transformMethods(byte[] bytes, BiConsumer<ClassNode, MethodNode> transformer) {
        ClassReader classReader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        ClassWriter classWriter = new ClassWriter(0);
        try {
            classReader.accept(classNode, 0);
            classNode.methods.forEach(m ->
                    transformer.accept(classNode, m)
            );
           System.out.println("正在转换 -> " + classNode.name);
            System.out.println("具体转换 -> " + classReader.getClassName());
            classNode.accept(classWriter);
        } catch (Throwable e){
            e.printStackTrace();
            System.out.println("异常 -> " + classReader);
        }
        getFileByBytes(classWriter.toByteArray(),"E:\\Lycoris Client\\Output",
                classReader.getClassName().replaceAll("/",".")+".class");
        return classWriter.toByteArray();
    }

    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	public byte[] transform(String name, byte[] classByte) {
        System.out.println(name);
        try {
            if(name.contains(OBFMap.getString("net.minecraft.client.Minecraft"))){
                return transformMethods(classByte, MinecraftTransformer::transform);
            }
            if(name.contains(OBFMap.getString("net.minecraft.client.settings.KeyBinding"))){
                return transformMethods(classByte, KeyBindingTransformer::transform);
            }
            if(name.contains(OBFMap.getString("net.minecraft.client.gui.GuiIngame"))){
                return transformMethods(classByte, GuiIngameTransformer::transform);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classByte;
    }


    @Override
    public byte[] transform(ClassLoader arg0, String name, Class<?> clazz, ProtectionDomain arg3, byte[] classByte)
    {
        return transform(clazz.getName(), classByte);
    }

}
