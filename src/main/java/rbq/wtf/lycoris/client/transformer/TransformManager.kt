package rbq.wtf.lycoris.client.transformer

import rbq.wtf.lycoris.agent.LycorisAgent
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.transformer.transformers.*
import rbq.wtf.lycoris.client.utils.FileUtils.writeFile
import rbq.wtf.lycoris.client.utils.Logger
import java.io.IOException

object TransformManager {
    var transformers: MutableList<ClassTransformer> = ArrayList()
    var backTransformers: MutableList<BackTransformer> = ArrayList()
    var transformed = false

    fun init() {
        if (transformed) throw RuntimeException("Classes has been transformed")
        Logger.info("Start Initialize Transforms", "Transformer")
        transformers.add(MinecraftTransformer())
        transformers.add(GuiIngameTransformer())
        transformers.add(KeyBindingTransformer())
        transformers.add(EntityPlayerSPTransformer())
        transformers.add(EntityRendererTransformer())
        transformers.add(NetworkManagerTransformer())
        doTransform()
        Logger.info("Transforms Initialized Successful", "Transformer")
    }

    fun doTransform() {
        //Load Native to use JVMTI to call onTransform
        Logger.info("Start Initialize Native", "Native")
        Logger.info("Load Native: " + Client.JVMTILib.file.name, "Native")
        System.load(Client.JVMTILib.file.toString())
        Logger.info("Native Initialized Successful", "Native")
        //re-transform Class
        if (!transformed) {
            for (classTransformer in transformers) {
                Logger.info("Start Transformer " + classTransformer.targetClass.canonicalName, "Transformer")
                val error = LycorisAgent.retransformclass(InstrumentationImpl(), classTransformer.targetClass)
                Logger.info(
                    "Transformed Class " + classTransformer.targetClass.canonicalName + " " + JVMTIError.parse(error),
                    "Transformer"
                )
            }
            transformed = true
        } else {
            for (classTransformer in backTransformers) {
                Logger.info("Start Transformer " + classTransformer.targetClass.canonicalName, "TransformBack")
                val error = LycorisAgent.retransformclass(InstrumentationImpl(), classTransformer.targetClass)
                Logger.info(
                    "Transformed Class " + classTransformer.targetClass.canonicalName + " " + JVMTIError.parse(error),
                    "TransformBack"
                )
            }
            backTransformers.clear()
            transformed = false
        }
    }

    @JvmStatic
    fun onTransform(clazz: Class<*>?, original_class_bytes: ByteArray?): ByteArray? {
        if (clazz == null || clazz.canonicalName == "null") {
            Logger.warning("NULL Class", "Transformer")
            return original_class_bytes
        }
        if (original_class_bytes == null) {
            Logger.warning("NULL Class Bytes", "Transformer")
            return null
        }
        var classBytes = ByteArray(0)
        if (!transformed) {
            for (transformer in transformers) {
                if (transformer.targetClass == clazz) {
                    classBytes = transformer.transform(original_class_bytes)
                    backTransformers.add(BackTransformer(clazz, original_class_bytes))
                    Logger.debug("Transform" + clazz.canonicalName + " Successful", "Transformer")
                }
            }
        } else {
            for (transformer in backTransformers) {
                if (transformer.targetClass == clazz) {
                    classBytes = transformer.transform(original_class_bytes)
                    Logger.debug("Transform" + clazz.canonicalName + " Successful", "TransformBack")
                }
            }
        }
//        if (classBytes == null) {
//            Logger.error("Transform " + clazz.canonicalName + " return null", "Transformer")
//            return original_class_bytes
//        }
        if (Client.developEnv) {
            try {
                writeFile(Client.runPath.parent.resolve("debug/" + clazz.canonicalName.replace("/", ".") + (if (transformed) "_back" else "") + ".class").toFile(), classBytes)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        return classBytes
    }
}