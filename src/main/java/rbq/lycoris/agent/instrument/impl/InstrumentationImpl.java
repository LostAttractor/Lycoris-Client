package rbq.lycoris.agent.instrument.impl;


import rbq.lycoris.agent.Access;
import rbq.lycoris.agent.instrument.ClassTransformer;
import rbq.lycoris.agent.instrument.Instrumentation;


public class InstrumentationImpl implements Instrumentation {
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