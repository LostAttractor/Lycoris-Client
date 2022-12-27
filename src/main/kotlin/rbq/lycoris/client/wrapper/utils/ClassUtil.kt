package rbq.lycoris.client.wrapper.utils

import rbq.lycoris.agent.instrument.impl.InstrumentationImpl
import rbq.lycoris.client.transformer.TransformManager

object ClassUtil {
    fun getClasses(pack: String): List<Class<*>> {
        val list = ArrayList<Class<*>>()
        for (allLoadedClass in TransformManager.instrumentationImpl.allLoadedClasses) {
            if (allLoadedClass.canonicalName.startsWith(pack)) {
                list.add(allLoadedClass)
            }
        }
        return list
    }
}