package rbq.wtf.lycoris.client.wrapper.wrappers.utils

import rbq.wtf.lycoris.agent.LycorisAgent
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl

object ClassUtil {
    fun getClasses(pack: String): List<Class<*>> {
        val list = ArrayList<Class<*>>()
        for (allLoadedClass in LycorisAgent.getAllLoadedClass(InstrumentationImpl())) {
            if (allLoadedClass.canonicalName.startsWith(pack)) {
                list.add(allLoadedClass)
            }
        }
        return list
    }
}