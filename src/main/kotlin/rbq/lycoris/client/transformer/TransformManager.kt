package rbq.lycoris.client.transformer

import rbq.lycoris.agent.instrument.impl.InstrumentationImpl
import rbq.lycoris.client.Client
import rbq.lycoris.client.transformer.transformers.*
import rbq.lycoris.client.utils.FileUtils.writeFile
import rbq.lycoris.client.utils.Logger
import java.io.IOException

object TransformManager {
    val instrumentationImpl = InstrumentationImpl()

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

    fun reTransform() {
        if (transformed) throw RuntimeException("Classes has been transformed")
        Logger.info("Re-Transforming Class...")
        doTransform()
        Logger.info("Client Initialized Successful")
    }

    fun doTransform() {
        //Load Native to use JVMTI to call onTransform
        Logger.info("Start Initialize Native", "Native")
        Logger.info("Load Native: " + Client.JVMTILib.file.name, "Native")
        /**
         * Native-Transformer
         *
         * 该Native库加载时会通过Windows API获得允许reTransform类的JVMTI实例并声明InstrumentationImpl中的一些Method
         * 在其中的reTransformClasses这个Method被运行时，会重载传入的类并添加Hook修改类的加载行为
         * 该Hook会通过下方的transform方法对期望加载的类进行修改并替换加载内容
        **/
        System.load(Client.JVMTILib.file.toString())
        Logger.info("Native Initialized Successful", "Native")
        //re-transform Class
        if (!transformed) {
            for (classTransformer in transformers) {
                Logger.info("Start Transformer " + classTransformer.targetClass.canonicalName, "Transformer")
                val error = instrumentationImpl.reTransformClasses(arrayOf(classTransformer.targetClass))
                Logger.info(
                    "Transformed Class " + classTransformer.targetClass.canonicalName + " " + JVMTIError.parse(error),
                    "Transformer"
                )
            }
            transformed = true
        } else {
            for (classTransformer in backTransformers) {
                Logger.info("Start Transformer " + classTransformer.targetClass.canonicalName, "TransformBack")
                val error = instrumentationImpl.reTransformClasses(arrayOf(classTransformer.targetClass))
                Logger.info(
                    "Transformed Class " + classTransformer.targetClass.canonicalName + " " + JVMTIError.parse(error),
                    "TransformBack"
                )
            }
            backTransformers.clear()
            transformed = false
        }
    }

    /**
     * Called in Native-Transformer
     *
     * @param clazz Class Target
     * @param original_class_bytes Original Class Bytes
     * @return Transformed Class Bytes
     */
    @JvmStatic
    fun transform(clazz: Class<*>?, original_class_bytes: ByteArray?): ByteArray? {
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
                writeFile(
                    Client.runPath.parent.resolve(
                        "debug/" + clazz.canonicalName.replace(
                            "/",
                            "."
                        ) + (if (transformed) "_back" else "") + ".class"
                    ).toFile(), classBytes
                )
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        return classBytes
    }
}