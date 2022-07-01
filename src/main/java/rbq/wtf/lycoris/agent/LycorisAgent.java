package rbq.wtf.lycoris.agent;

import rbq.wtf.lycoris.agent.instrument.ClassTransformer;
import rbq.wtf.lycoris.agent.instrument.Instrumentation;

import java.util.Arrays;

public class LycorisAgent {
    public static void retransformclass(Instrumentation instrumentation, ClassTransformer transformer, String classname){
        instrumentation.addTransformer(transformer);
        instrumentation.retransformClasses(Arrays.stream(instrumentation.getAllLoadedClasses())
                .filter(aClass -> aClass.getName().startsWith(classname))
                .toArray(Class[]::new));
    }
}
