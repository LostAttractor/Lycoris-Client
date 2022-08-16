package rbq.wtf.lycoris.agent;

import rbq.wtf.lycoris.agent.instrument.Instrumentation;

import java.util.Arrays;

public class LycorisAgent {
    public static int retransformclass(Instrumentation instrumentation, Class<?> clazz){
        return instrumentation.retransformClasses(new Class<?>[]{clazz});
    }
    public static Class<?>[] getAllLoadedClass (Instrumentation instrumentation) {
        return instrumentation.getAllLoadedClasses();
    }
}
