package rbq.lycoris.agent.instrument.impl;


import rbq.lycoris.agent.Access;
import rbq.lycoris.agent.instrument.ClassTransformer;
import rbq.lycoris.agent.instrument.Instrumentation;


public class InstrumentationImpl implements Instrumentation {
    @Override
    public ClassTransformer[] getTransformers() {
        return Access.getTransformersAsArray();
    }

    /**
     * Impl by JNI
     * @return All Loaded Classes
     */
    @Override
    public native Class<?>[] getAllLoadedClasses();

    /**
     * Impl by JNI
     *
     * @param classLoader classloader
     * @return Loaded Classes by classloader
     */
    @Override
    public native Class<?>[] getLoadedClasses(ClassLoader classLoader); //由Native实现

    /**
     * Impl by JNI
     *
     * @param classes classes need been transform
     * @return error code
     */
    @Override
    public native int reTransformClasses(Class<?>[] classes);

    @Override
    public void addTransformer(final ClassTransformer classTransformer) {
        Access.addTransformer(classTransformer);
    }
}