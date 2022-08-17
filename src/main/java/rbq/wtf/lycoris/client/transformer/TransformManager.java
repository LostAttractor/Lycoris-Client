package rbq.wtf.lycoris.client.transformer;

import rbq.wtf.lycoris.agent.LycorisAgent;
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.transformer.transformers.GuiIngameTransformer;
import rbq.wtf.lycoris.client.transformer.transformers.KeyBindingTransformer;
import rbq.wtf.lycoris.client.transformer.transformers.MinecraftTransformer;
import rbq.wtf.lycoris.client.utils.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TransformManager {
    public static List<ClassTransformer> transformers = new ArrayList<ClassTransformer>();

    public static void init() {
        transformers.add(new GuiIngameTransformer());
        transformers.add(new KeyBindingTransformer());
        transformers.add(new MinecraftTransformer());
    }

    public static void doTransform() {
        for (ClassTransformer classTransformer : transformers) {
            Logger.log("Start Transformer " + classTransformer.getTargetClass().getCanonicalName(), "Transformer");
            int error = LycorisAgent.retransformclass(new InstrumentationImpl(), classTransformer.getTargetClass());
            Logger.log("Transform Class " + classTransformer.getTargetClass().getCanonicalName() + " " + JVMTIError.parse(error), "Transformer");
        }
    }

    public static byte[] onTransform(Class<?> clazz, byte[] original_class_bytes) {
        if (clazz == null || clazz.getCanonicalName().equals("null")) {
            Logger.log("NULL Class", "Transformer");
            return original_class_bytes;
        }
        if (original_class_bytes == null) {
            Logger.log("NULL Class Bytes", "Transformer");
            return null;
        }
        byte[] class_bytes = null;
        Logger.log("Do Transformer " + clazz.getCanonicalName(), "Transformer");
        for (ClassTransformer transformer : transformers) {
            if (transformer.getTargetClass().equals(clazz)) {
                class_bytes = transformer.transform(original_class_bytes);
                Logger.log("Transform " + clazz.getCanonicalName() + " Successful", "Transformer");
            }
        }
        if (class_bytes == null) {
            Logger.log("Transform " + clazz.getCanonicalName() + " return null", "Transformer");
            return original_class_bytes;
        }
//        if (LycorisClient.debug) {{
        writeFileByBytes(class_bytes, Paths.get("").toAbsolutePath().getParent().resolve("debug"), clazz.getCanonicalName().replaceAll("/", ".") + ".class");
//        }}
        return class_bytes;
    }

    public static void writeFileByBytes(byte[] bytes, Path path, String name) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File dir = path.toFile();
        if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
            dir.mkdirs();
        }
        File file = path.resolve(name).toFile();
        try {
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
}
