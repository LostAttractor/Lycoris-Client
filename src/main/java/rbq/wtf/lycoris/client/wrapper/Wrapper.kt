package rbq.wtf.lycoris.client.wrapper

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.font.FontLoaders
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.utils.Logger.debug
import rbq.wtf.lycoris.client.utils.Logger.error
import rbq.wtf.lycoris.client.utils.Logger.info
import rbq.wtf.lycoris.client.wrapper.annotation.*
import rbq.wtf.lycoris.client.wrapper.annotation.repeat.*
import rbq.wtf.lycoris.client.wrapper.srgreader.SRGReader
import rbq.wtf.lycoris.client.wrapper.srgreader.map.ClassNode
import rbq.wtf.lycoris.client.wrapper.srgreader.map.FieldNode
import rbq.wtf.lycoris.client.wrapper.srgreader.map.MethodNode
import rbq.wtf.lycoris.client.wrapper.wrappers.GameSettings
import rbq.wtf.lycoris.client.wrapper.wrappers.KeyBinding
import rbq.wtf.lycoris.client.wrapper.wrappers.Minecraft
import rbq.wtf.lycoris.client.wrapper.wrappers.block.Block
import rbq.wtf.lycoris.client.wrapper.wrappers.block.state.IBlockState
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.Entity
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.EntityLivingBase
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.EntityPlayer
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.EntityPlayerSP
import rbq.wtf.lycoris.client.wrapper.wrappers.gui.*
import rbq.wtf.lycoris.client.wrapper.wrappers.init.Blocks
import rbq.wtf.lycoris.client.wrapper.wrappers.multiplayer.PlayerControllerMP
import rbq.wtf.lycoris.client.wrapper.wrappers.multiplayer.WorldClient
import rbq.wtf.lycoris.client.wrapper.wrappers.network.NetworkManager
import rbq.wtf.lycoris.client.wrapper.wrappers.network.Packet
import rbq.wtf.lycoris.client.wrapper.wrappers.potion.Potion
import rbq.wtf.lycoris.client.wrapper.wrappers.render.*
import rbq.wtf.lycoris.client.wrapper.wrappers.render.texture.AbstractTexture
import rbq.wtf.lycoris.client.wrapper.wrappers.render.texture.DynamicTexture
import rbq.wtf.lycoris.client.wrapper.wrappers.resources.IReloadableResourceManager
import rbq.wtf.lycoris.client.wrapper.wrappers.resources.IResource
import rbq.wtf.lycoris.client.wrapper.wrappers.resources.IResourceManager
import rbq.wtf.lycoris.client.wrapper.wrappers.util.*
import rbq.wtf.lycoris.client.wrapper.wrappers.util.event.HoverEvent
import rbq.wtf.lycoris.client.wrapper.wrappers.util.event.click.ClickEvent
import rbq.wtf.lycoris.client.wrapper.wrappers.util.event.click.ClickEventAction
import rbq.wtf.lycoris.client.wrapper.wrappers.util.text.IChatComponent
import rbq.wtf.lycoris.client.wrapper.wrappers.world.World
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier

object Wrapper {
    private val wrapperList: ArrayList<Class<out IWrapper?>> = ArrayList()
    lateinit var MapEnv: MapEnum //由RuntimeManager进行赋值
    var useMapObf: Boolean = true //由RuntimeManager进行赋值
    private lateinit var reader: SRGReader

    fun init() {
        info("Start Initialize Wrapper", "Wrapper")
        try {
            reader = SRGReader(Client.srgMap.file)
            loadWrappers()
            applyMap()
            //ReflectLoading.loadingProgress.setString("Loading Wrapper");
        } catch (e: Exception) {
            e.printStackTrace()
            info("Wrapper Initialized Failed", "Wrapper")
        }
        info("Wrapper Initialized Successful", "Wrapper")
    }

    private fun loadWrappers() {
        info("Loading wrappers", "Wrapper")
        val classes: MutableList<Class<*>> = ArrayList()
        //init
        classes.add(Blocks::class.java)
        //client
        classes.add(Minecraft::class.java)
        classes.add(KeyBinding::class.java)
        classes.add(GameSettings::class.java)
        //entity
        classes.add(Entity::class.java)
        classes.add(EntityLivingBase::class.java)
        classes.add(EntityPlayer::class.java)
        classes.add(EntityPlayerSP::class.java)
        //multiplayer
        classes.add(PlayerControllerMP::class.java)
        classes.add(WorldClient::class.java)
        //world
        classes.add(World::class.java)
        //network
        classes.add(Packet::class.java)
        classes.add(NetworkManager::class.java)
        //block
        classes.add(Block::class.java)
        //block.state
        classes.add(IBlockState::class.java)
        //potion
        classes.add(Potion::class.java)
        //gui
        classes.add(Gui::class.java)
        classes.add(ScaledResolution::class.java)
        classes.add(GuiIngame::class.java)
        classes.add(GuiChat::class.java)
        classes.add(GuiScreen::class.java)
        classes.add(GuiTextField::class.java)
        //render
        classes.add(DefaultVertexFormats::class.java)
        classes.add(GlStateManager::class.java)
        classes.add(OpenGlHelper::class.java)
        classes.add(Tessellator::class.java)
        classes.add(VertexFormat::class.java)
        classes.add(WorldRenderer::class.java)
        classes.add(EntityRenderer::class.java)
        classes.add(FontLoaders::class.java)
        //render.texture
        classes.add(AbstractTexture::class.java)
        classes.add(DynamicTexture::class.java)
        //resource
        classes.add(IResource::class.java)
        classes.add(IResourceManager::class.java)
        classes.add(IReloadableResourceManager::class.java)
        //util
        classes.add(AxisAlignedBB::class.java)
        classes.add(ResourceLocation::class.java)
        classes.add(BlockPos::class.java)
        classes.add(Vec3i::class.java)
        classes.add(Vec3::class.java)
        classes.add(ChatAllowedCharacters::class.java)
        classes.add(ChatStyle::class.java)
        classes.add(FoodStats::class.java)
        classes.add(MovementInput::class.java)
        classes.add(MovingObjectPosition::class.java)
        //util.event
        classes.add(ClickEvent::class.java)
        classes.add(ClickEventAction::class.java)
        classes.add(HoverEvent::class.java)
        //util.text
        classes.add(IChatComponent::class.java)
        for (aClass in classes) {
            // Logger.log(aClass.getCanonicalName(), "Wrapper");
            if (aClass != IWrapper::class.java) {
                wrapperList.add(aClass as Class<out IWrapper?>)
            }
        }
    }

    @Throws(
        IllegalAccessException::class,
        ClassNotFoundException::class,
        NoSuchFieldException::class,
        NoSuchMethodException::class
    )
    private fun applyMap() {
        for (wrapper in wrapperList) {
            debug("Apply wrapper " + wrapper.canonicalName, "Wrapper")
            for (declaredAnnotation in wrapper.declaredAnnotations) {
                if (declaredAnnotation is WrapperClasses) {
                    for (wrapperClass in declaredAnnotation.value) {
                        applyClass(wrapperClass, wrapper)
                    }
                } else if (declaredAnnotation is WrapperClass) {
                    applyClass(declaredAnnotation, wrapper)
                }
            }
        }
        for (wrapper in wrapperList) {
            debug("Apply constructor " + wrapper.canonicalName, "Wrapper")
            for (declaredAnnotation in wrapper.declaredAnnotations) {
                if (declaredAnnotation is WrapperClasses) {
                    for (wrapperClass in declaredAnnotation.value) {
                        applyConstructor(wrapperClass, wrapper)
                    }
                } else if (declaredAnnotation is WrapperClass) {
                    applyConstructor(declaredAnnotation, wrapper)
                }
            }
        }
    }

    private fun applyConstructor(wrapperClass: WrapperClass, wrapper: Class<out IWrapper?>) {
        if (wrapperClass.targetMap == MapEnv) {
            for (declaredField in wrapper.declaredFields) {
                try {
                    for (annotation in declaredField.declaredAnnotations) {
                        if (annotation is WrapConstructors) {
                            for (wrapConstructor in annotation.value) {
                                applyConstructor(wrapperClass, wrapConstructor, declaredField)
                            }
                        } else if (annotation is WrapConstructor) {
                            applyConstructor(wrapperClass, annotation, declaredField)
                        }
                    }
                } catch (e: Exception) {
                    error("Failed to apply Constructor: " + wrapperClass.mcpName, "Wrapper")
                    e.printStackTrace()
                }
            }
        }
    }

    @Throws(
        NoSuchFieldException::class,
        ClassNotFoundException::class,
        NoSuchMethodException::class,
        IllegalAccessException::class
    )
    private fun applyConstructor(wrapperClass: WrapperClass, wrapConstructor: WrapConstructor, declaredField: Field) {
        if (wrapConstructor.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                val classes: MutableList<Class<*>> = ArrayList()
                for (aClass in wrapConstructor.signature) {
                    if (IWrapper::class.java.isAssignableFrom(aClass.java)) {
                        for (declaredAnnotation in aClass.java.declaredAnnotations) {
                            if (declaredAnnotation is WrapperClasses) {
                                for (target in declaredAnnotation.value) {
                                    if (target.targetMap == MapEnv) {
                                        val mapNode = reader.getClass(target.mcpName, false)
                                        classes.add(reflectClassByMap(mapNode))
                                    }
                                }
                            } else if (declaredAnnotation is WrapperClass) {
                                val target: WrapperClass = declaredAnnotation
                                if (target.targetMap == MapEnv) {
                                    val mapNode = reader.getClass(target.mcpName, false)
                                    classes.add(reflectClassByMap(mapNode))
                                }
                            }
                        }
                    } else {
                        classes.add(aClass.java)
                    }
                }
                val mapNode = reader.getClass(wrapperClass.mcpName, false)
                val target = reflectClassByMap(mapNode)
                val constructor: Constructor<*> = if (classes.size == 0) {
                    target.constructors[0]
                } else {
                    target.getConstructor(*classes.toTypedArray())
                }
                declaredField[null] = constructor
            }
        }
    }

    @Throws(
        NoSuchFieldException::class,
        ClassNotFoundException::class,
        IllegalAccessException::class,
        NoSuchMethodException::class
    )
    private fun applyClass(wrapperClass: WrapperClass, wrapper: Class<out IWrapper?>) {
        if (wrapperClass.targetMap == MapEnv) {
            for (declaredField in wrapper.declaredFields) {
                try {
                    for (annotation in declaredField.declaredAnnotations) {
                        when (annotation) {
                            is WrapFields -> {
                                for (wrapField in annotation.value) {
                                    applyField(wrapperClass, wrapField, declaredField)
                                }
                            }
                            is WrapField -> {
                                applyField(wrapperClass, annotation, declaredField)
                            }
                            is WrapMethods -> {
                                for (wrapMethod in annotation.value) {
                                    applyMethod(wrapperClass, wrapMethod, declaredField)
                                }
                            }
                            is WrapMethod -> {
                                applyMethod(wrapperClass, annotation, declaredField)
                            }
                            is WrapClasses -> {
                                for (wrapClass in annotation.value) {
                                    applyClass(wrapperClass, wrapClass, declaredField)
                                }
                            }
                            is WrapClass -> {
                                applyClass(wrapperClass, annotation, declaredField)
                            }
                            is WrapClassAuto -> {
                                applyClassAuto(wrapperClass, declaredField)
                            }
                            is WrapEnums -> {
                                for (wrapEnum in annotation.value) {
                                    applyEnum(wrapperClass, wrapEnum, declaredField)
                                }
                            }
                            is WrapEnum -> {
                                applyEnum(wrapperClass, annotation, declaredField)
                            }
                            is WrapObjects -> {
                                for (wrapObject in annotation.value) {
                                    applyObject(wrapperClass, wrapObject, declaredField)
                                }
                            }
                            is WrapObject -> {
                                applyObject(wrapperClass, annotation, declaredField)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun applyEnum(wrapperClass: WrapperClass, wrapEnum: WrapEnum, declaredField: Field) {
        if (wrapEnum.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                var mapNode = reader.getField(wrapperClass.mcpName, wrapEnum.mcpName, false)
                if (wrapEnum.customSrgName != "none") {
//                    val clazz = reader.getClass(wrapperClass.mcpName)
                    mapNode = FieldNode(
                        mapNode.nodeType,
                        mapNode.mcpName,
                        mapNode.mcpClassName,
                        mapNode.mcpFieldName,
                        mapNode.srgName.replace(mapNode.srgFieldName, wrapEnum.customSrgName),
                        mapNode.srgClassName,
                        wrapEnum.customSrgName
                    )
//                    mapNode =
//                        MapNode(NodeType.Field, "", clazz.srg.replace(".", "/") + wrapEnum.customSrgName)
                }
                try {
                    declaredField.isAccessible = true
                    declaredField[null] = reflectEnumByMap(mapNode)
                    debug(
                        "Successful apply Enum: " + wrapperClass.mcpName + " " + wrapEnum.mcpName + " -> " + mapNode.getFieldName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                } catch (e: Exception) {
                    error(
                        "Failed to apply Enum: " + wrapperClass.mcpName + " " + wrapEnum.mcpName + " -> " + mapNode.getFieldName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                    e.printStackTrace()
                }
            }
        }
    }

    private fun applyObject(wrapperClass: WrapperClass, wrapObject: WrapObject, declaredField: Field) {
        if (wrapObject.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                var mapNode = reader.getField(wrapperClass.mcpName, wrapObject.mcpName, false)
                if (wrapObject.customSrgName != "none") {
                    mapNode = FieldNode(
                        mapNode.nodeType,
                        mapNode.mcpName,
                        mapNode.mcpClassName,
                        mapNode.mcpFieldName,
                        mapNode.srgName.replace(mapNode.srgFieldName, wrapObject.customSrgName),
                        mapNode.srgClassName,
                        wrapObject.customSrgName
                    )
                }
                try {
                    declaredField.isAccessible = true
                    declaredField[null] = reflectFieldByMap(mapNode)[null]
                    debug(
                        "Successful Apply Object: " + wrapperClass.mcpName + " " + wrapObject.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    error(
                        "Failed to Apply Object: " + wrapperClass.mcpName + " " + wrapObject.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                }
            }
        }
    }

    private fun applyField(wrapperClass: WrapperClass, wrapField: WrapField, declaredField: Field) {
        if (wrapField.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                var mapNode = reader.getField(wrapperClass.mcpName, wrapField.mcpName, false)
                if (wrapField.customSrgName != "none") {
                    mapNode = FieldNode(
                        mapNode.nodeType,
                        mapNode.mcpName,
                        mapNode.mcpClassName,
                        mapNode.mcpFieldName,
                        mapNode.srgName.replace(mapNode.srgFieldName, wrapField.customSrgName),
                        mapNode.srgClassName,
                        wrapField.customSrgName
                    )
                }
                try {
                    declaredField.isAccessible = true
                    declaredField[null] = reflectFieldByMap(mapNode)
                    debug(
                        "Successful Apply Field: " + wrapperClass.mcpName + " " + wrapField.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    error(
                        "Failed to Apply Field: " + wrapperClass.mcpName + " " + wrapField.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                }
            }
        }
    }

    private fun applyClassAuto(wrapperClass: WrapperClass, declaredField: Field) {
        if (wrapperClass.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                val mapNode = reader.getClass(wrapperClass.mcpName, false)
                try {
                    declaredField.isAccessible = true
                    declaredField[null] = reflectClassByMap(mapNode)
                    debug("Successful Apply Class: "  + wrapperClass.mcpName + " -> " + mapNode.getName(useMapObf), "Wrapper")
                } catch (e: Exception) {
                    e.printStackTrace()
                    error(
                        "Failed to Apply Class: " +  wrapperClass.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                }
            }
        }
    }

    private fun applyClass(wrapperClass: WrapperClass, wrapClass: WrapClass, declaredField: Field) {
        if (wrapClass.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                val mapNode = reader.getClass(wrapClass.mcpName, false)
                try {
                    declaredField.isAccessible = true
                    declaredField[null] = reflectClassByMap(mapNode)
                    debug(
                        "Successful Apply Class: " + wrapperClass.mcpName + " " + wrapClass.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    error(
                        "Failed to Apply Class: " + wrapperClass.mcpName + " " + wrapClass.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                }
            }
        }
    }

    private fun applyMethod(wrapperClass: WrapperClass, wrapMethod: WrapMethod, declaredField: Field) {
        if (wrapMethod.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                val mapNode = reader.getMethod(
                    wrapperClass.mcpName,
                    wrapMethod.mcpName,
                    if (wrapMethod.signature == "none") null else wrapMethod.signature,
                    false
                )
                try {
                    declaredField.isAccessible = true
                    declaredField[null] = reflectMethodByMap(mapNode)
                    debug(
                        "Successful Apply Method: " + wrapperClass.mcpName + " " + wrapMethod.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                } catch (e: Exception) {
                    error(
                        "Failed to Apply Method: " + wrapperClass.mcpName + " " + wrapMethod.mcpName + " -> " + mapNode.getName(
                            useMapObf
                        ),
                        "Wrapper"
                    )
                    e.printStackTrace()
                }
            }
        }
    }

    @Throws(ClassNotFoundException::class)
    private fun reflectEnumByMap(mapNode: FieldNode): Enum<*> {
        val fieldName = mapNode.getFieldName(useMapObf)
        val className = mapNode.getClassName(useMapObf)
        debug("reflectEnumByMap: $className", "Wrapper")
        val c = getClassNative(className)
        for (enumConstant in c.enumConstants) {
            if (enumConstant is Enum<*>) {
                if (enumConstant.name == fieldName) return enumConstant
            }
        }
        throw NoSuchFieldException("Can't Found Enum: " + mapNode.getName(false) + " -> " + mapNode.getName(true))
    }

    @Throws(ClassNotFoundException::class, NoSuchFieldException::class)
    private fun reflectFieldByMap(mapNode: FieldNode): Field {
        val className = mapNode.getClassName(useMapObf)
        val fieldName = mapNode.getFieldName(useMapObf)
        debug("reflectFieldByMap: $className, $fieldName", "Wrapper")
        val c = getClassNative(className)
        val f = c.getDeclaredField(fieldName) //无法访问继承类的元素!!
        f.isAccessible = true
        return f
    }

    @Throws(ClassNotFoundException::class, NoSuchMethodException::class)
    private fun reflectMethodByMap(mapNode: MethodNode): Method {
        val className = mapNode.getClassName(useMapObf)
        val methodName = mapNode.getMethodName(useMapObf)
        val signature = mapNode.getSignature(useMapObf)
        val c = getClassNative(className)
        val m = c.getDeclaredMethod(methodName, *signature.args)
        m.isAccessible = true
        return m
    }

    @Throws(ClassNotFoundException::class)
    private fun reflectClassByMap(mapNode: ClassNode): Class<*> {
        val className = mapNode.getClassName(useMapObf).replace("/", ".")
        debug("reflectClassByMap: $className", "Wrapper")
        return getClassNative(className) //declaringClass
    }

    @Throws(ClassNotFoundException::class)
    fun getClassNative(name: String): Class<*> {
        Logger.debug("Try to get Class: $name", "Wrapper")
        return this.javaClass.classLoader.loadClass(name)
    }
}