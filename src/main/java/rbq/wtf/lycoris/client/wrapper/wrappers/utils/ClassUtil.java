package rbq.wtf.lycoris.client.wrapper.wrappers.utils;

import rbq.wtf.lycoris.agent.LycorisAgent;
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;

import java.util.ArrayList;
import java.util.List;

public class ClassUtil {
    public static List<Class<?>> getClasses(String pack) {
        ArrayList<Class<?>> list = new ArrayList<Class<?>>();
        for (Class<?> allLoadedClass : LycorisAgent.getAllLoadedClass(new InstrumentationImpl())) {
            if (allLoadedClass.getCanonicalName().startsWith(pack)){
                list.add(allLoadedClass);
            }
        }
        return list;
    }
}
