package rbq.lycoris.agent;

import rbq.lycoris.agent.instrument.Instrumentation;

public class LycorisAgent {
    public static int retransformclass(Instrumentation instrumentation, Class<?> clazz) {
        return instrumentation.retransformClasses(new Class<?>[]{clazz});
    }

    public static Class<?>[] getAllLoadedClass(Instrumentation instrumentation) {
        return instrumentation.getAllLoadedClasses();
    }
}
