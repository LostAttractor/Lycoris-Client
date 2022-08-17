package rbq.wtf.lycoris.agent.instrument.impl;


import rbq.wtf.lycoris.agent.Access;
import rbq.wtf.lycoris.agent.instrument.ClassTransformer;
import rbq.wtf.lycoris.agent.instrument.Instrumentation;
import rbq.wtf.lycoris.client.utils.Logger;

import java.nio.file.Paths;


public class InstrumentationImpl implements Instrumentation {
    public static final String NativeLibPath = Paths.get("").toAbsolutePath().getParent().resolve("Lycoris-Native-Loader/x64/Release/Lycoris-Native-Loader.dll").toString();

    public static void init() {
        Logger.log("Start Initialize Native", "Native");
        Logger.log("Load Native: " + NativeLibPath, "Native");
        System.load(NativeLibPath);
        Logger.log("Native Initialized Successful", "Native");
    }

    @Override
    public ClassTransformer[] getTransformers() {
        return Access.getTransformersAsArray();
    }

    @Override
    public native Class<?>[] getAllLoadedClasses();

    @Override
    public native int retransformClasses(Class<?>[] classes);

    @Override
    public native Class<?>[] getLoadedClasses(ClassLoader classLoader);

    @Override
    public void addTransformer(final ClassTransformer classTransformer) {
        Access.addTransformer(classTransformer);
    }
}