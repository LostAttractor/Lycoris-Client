package rbq.wtf.lycoris.client.transformer;

import rbq.wtf.lycoris.agent.LycorisAgent;
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.transformer.transformers.GuiIngameTransformer;

import java.util.ArrayList;
import java.util.List;

public class TransformerManager {
    public static List<ClassTransformer> transformers = new ArrayList<ClassTransformer>();
    public static void init(){
        transformers.add(new GuiIngameTransformer());
    }
    public static void doTransform(){
        for (ClassTransformer classTransformer : transformers) {
            System.out.println("[Transformer] Start Transformer " + classTransformer.getTargetClass().getCanonicalName());
            LycorisAgent.retransformclass(new InstrumentationImpl(),new MainTransformer(), classTransformer.getTargetClass().getCanonicalName());
        }
    }
    public static byte[] onTransform(Class<?> clazz, byte[] original_class_bytes){
        if (clazz == null) {
            System.out.println("[Transformer] NULL Class");
            return original_class_bytes;
        }
        if (original_class_bytes == null) {
            System.out.println("[Transformer] NULL Class Bytes");
            return null;
        }
        byte[] class_bytes = null;
        System.out.println("[Transformer] Do Transformer " + clazz.getCanonicalName());
        for (ClassTransformer transformer: transformers) {
            if (transformer.getTargetClass() == clazz){
                class_bytes = transformer.transform(original_class_bytes);
                System.out.println("[Transformer] Transform " + clazz.getCanonicalName() + "Success");

            }
        }
        if (class_bytes == null){
            System.out.println("[Transformer] Transform " + clazz.getCanonicalName() + " return null");
            return original_class_bytes;
        }
        return class_bytes;
    }
}
