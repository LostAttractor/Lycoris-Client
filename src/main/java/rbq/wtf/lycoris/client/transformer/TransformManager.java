package rbq.wtf.lycoris.client.transformer;

import rbq.wtf.lycoris.agent.LycorisAgent;
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.transformer.transformers.*;
import rbq.wtf.lycoris.client.utils.FileUtils;
import rbq.wtf.lycoris.client.utils.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransformManager {
    public static List<ClassTransformer> transformers = new ArrayList<>();
    public static List<BackTransformer> backTransformers = new ArrayList<>();

    public static boolean transformed = false;

    public static void init() {
        if (transformed)
            throw new RuntimeException("Classes has been transformed");
        Logger.info("Start Initialize Transforms", "Transformer");
        transformers.add(new MinecraftTransformer());
        transformers.add(new GuiIngameTransformer());
        transformers.add(new KeyBindingTransformer());
        transformers.add(new EntityPlayerSPTransformer());
        transformers.add(new EntityRendererTransformer());
        transformers.add(new NetworkManagerTransformer());
        doTransform();
        Logger.info("Transforms Initialized Successful", "Transformer");
    }

    public static void doTransform() {
        //Load Native to use JVMTI to call onTransform
        Logger.info("Start Initialize Native", "Native");
        Logger.info("Load Native: " + Client.JVMTILib.getFile().getName(), "Native");
        System.load(Client.JVMTILib.getFile().toString());
        Logger.info("Native Initialized Successful", "Native");
        //re-transform Class
        if (!transformed) {
            for (ClassTransformer classTransformer : transformers) {
                Logger.info("Start Transformer " + classTransformer.getTargetClass().getCanonicalName(), "Transformer");
                int error = LycorisAgent.retransformclass(new InstrumentationImpl(), classTransformer.getTargetClass());
                Logger.info("Transformed Class " + classTransformer.getTargetClass().getCanonicalName() + " " + JVMTIError.parse(error), "Transformer");
            }
            transformed = true;
        } else {
            for (ClassTransformer classTransformer : backTransformers) {
                Logger.info("Start Transformer " + classTransformer.getTargetClass().getCanonicalName(), "TransformBack");
                int error = LycorisAgent.retransformclass(new InstrumentationImpl(), classTransformer.getTargetClass());
                Logger.info("Transformed Class " + classTransformer.getTargetClass().getCanonicalName() + " " + JVMTIError.parse(error), "TransformBack");
            }
            backTransformers.clear();
            transformed = false;
        }
    }

    public static byte[] onTransform(Class<?> clazz, byte[] original_class_bytes) {
        if (clazz == null || clazz.getCanonicalName().equals("null")) {
            Logger.warning("NULL Class", "Transformer");
            return original_class_bytes;
        }
        if (original_class_bytes == null) {
            Logger.warning("NULL Class Bytes", "Transformer");
            return null;
        }
        byte[] class_bytes = null;
        if (!transformed) {
            for (ClassTransformer transformer : transformers) {
                if (transformer.getTargetClass().equals(clazz)) {
                    class_bytes = transformer.transform(original_class_bytes);
                    backTransformers.add(new BackTransformer(clazz, original_class_bytes));
                    Logger.debug("Transform" + clazz.getCanonicalName() + " Successful", "Transformer");
                }
            }
        } else {
            for (ClassTransformer transformer : backTransformers) {
                if (transformer.getTargetClass().equals(clazz)) {
                    class_bytes = transformer.transform(original_class_bytes);
                    Logger.debug("Transform Back" + clazz.getCanonicalName() + " Successful", "Transformer");
                }
            }
        }
        if (class_bytes == null) {
            Logger.error("Transform " + clazz.getCanonicalName() + " return null", "Transformer");
            return original_class_bytes;
        }
        if (Client.developEnv) {
            try {
                FileUtils.writeFile(Client.runPath.getParent().resolve("debug/" + clazz.getCanonicalName().replaceAll("/", ".") + (transformed ? "_back" : "") + ".class").toFile(), class_bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return class_bytes;
    }
}
