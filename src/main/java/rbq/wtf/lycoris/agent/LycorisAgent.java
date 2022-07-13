package rbq.wtf.lycoris.agent;

import rbq.wtf.lycoris.agent.instrument.Instrumentation;

import java.util.Arrays;

public class LycorisAgent {
    public static void retransformclass(Instrumentation instrumentation, String classname){
        instrumentation.retransformClasses(Arrays.stream(instrumentation.getAllLoadedClasses())
                .filter(aClass -> aClass.getName().startsWith(classname))
                .toArray(Class[]::new));
    }
    public static Class<?>[] getAllLoadedClass (Instrumentation instrumentation) {
        return instrumentation.getAllLoadedClasses();
    }
}
