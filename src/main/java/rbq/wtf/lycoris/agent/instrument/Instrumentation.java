package rbq.wtf.lycoris.agent.instrument;


public interface Instrumentation {
    Class<?>[] getAllLoadedClasses();

    ClassTransformer[] getTransformers();

    int retransformClasses(final Class<?>[] classes);

    Class<?>[] getLoadedClasses(final ClassLoader classLoader);

    void addTransformer(final ClassTransformer classTransformer);
}