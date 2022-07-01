package rbq.wtf.lycoris.agent.instrument.impl;



import rbq.wtf.lycoris.agent.Access;
import rbq.wtf.lycoris.agent.instrument.ClassTransformer;
import rbq.wtf.lycoris.agent.instrument.Instrumentation;


//javah -d .\ -classpath .\ InstrumentationImpl
//javah -d .\ -classpath .\ pub\nextsense\gc\nextagent\instrument\impl\InstrumentationImpl
//javah -d .\ -classpath .\ InstrumentationImpl
public class InstrumentationImpl implements Instrumentation
{
    static {

        System.load("E:\\Lycoris Client\\Lycoris Native Loader\\x64\\Release\\Lycoris Native Loader.dll");
        // System.load("C:\\Users\\Nplus\\source\\repos\\nextAgentV4\\x64\\Debug\\nextAgentV4.dll");
    }

    @Override
    public ClassTransformer[] getTransformers() {
        return Access.getTransformersAsArray();
    }

    @Override
    public native Class<?>[] getAllLoadedClasses();

    @Override
    public native void retransformClasses(Class<?>[] classes);

    @Override
    public native Class<?>[] getLoadedClasses(ClassLoader classLoader);

    @Override
    public void addTransformer(final ClassTransformer classTransformer) {
        Access.addTransformer(classTransformer);
    }
}