package rbq.wtf.lycoris.agent.instrument.impl;



import rbq.wtf.lycoris.agent.Access;
import rbq.wtf.lycoris.agent.instrument.ClassTransformer;
import rbq.wtf.lycoris.agent.instrument.Instrumentation;

import java.nio.file.Path;
import java.nio.file.Paths;


public class InstrumentationImpl implements Instrumentation
{
    public static void init(){
        System.out.println("InitNaive");
        System.load(Paths.get("").toAbsolutePath().getParent().resolve("Lycoris-Native-Loader/x64/Release/Lycoris-Native-Loader.dll").toString());
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