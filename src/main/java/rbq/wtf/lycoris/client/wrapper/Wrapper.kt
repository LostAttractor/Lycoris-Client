package rbq.wtf.lycoris.client.wrapper

import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.font.FontLoaders
import rbq.wtf.lycoris.client.utils.FileUtils
import rbq.wtf.lycoris.client.utils.Logger.debug
import rbq.wtf.lycoris.client.utils.Logger.error
import rbq.wtf.lycoris.client.utils.Logger.info
import rbq.wtf.lycoris.client.wrapper.SRGReader.SRGReader
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MapNode
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MethodNode
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.NodeType
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.*
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.*
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.GameSettings
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.KeyBinding
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block.Block
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block.state.IBlockState
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.Entity
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.EntityLivingBase
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.EntityPlayer
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.EntityPlayerSP
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.*
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.init.Blocks
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.multiplayer.PlayerControllerMP
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.multiplayer.WorldClient
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network.NetworkManager
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network.Packet
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.potion.Potion
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.*
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture.AbstractTexture
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture.DynamicTexture
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources.IReloadableResourceManager
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources.IResource
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.resources.IResourceManager
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.*
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.HoverEvent
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.click.ClickEvent
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.event.click.ClickEventAction
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.text.IChatComponent
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.world.World
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.nio.file.Path
import java.util.*

object Wrapper {
    private val wrapperList: MutableList<Class<out IWrapper?>> = ArrayList()
    var MapEnv: MapEnum? = null
    var useMapObf = false
    var srgMap: String? = null
    var srgPath: Path? = null
    private var reader: SRGReader? = null
    fun init() {
        info("Start Initialize Wrapper", "Wrapper")
        try {
            MapEnv = MapEnum.VANILLA189
            useMapObf = !Client.developEnv //是否使用混淆后的名称，在MDK环境下需设为false
            srgPath =
                if (Client.developEnv) Client.runPath.parent.resolve("maps/" + MapEnv.toString() + ".srg") else Client.runPath.resolve(
                    MapEnv.toString() + ".srg"
                )
            srgMap = FileUtils.readFileByPath(srgPath)
            reader = SRGReader(srgMap)
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

    fun getWrapperList(): List<Class<out IWrapper?>> {
        return wrapperList
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
                                        val mapNode = readClass(target.mcpName)
                                        classes.add(reflectClassByMap(mapNode))
                                    }
                                }
                            } else if (declaredAnnotation is WrapperClass) {
                                val target: WrapperClass = declaredAnnotation
                                if (target.targetMap == MapEnv) {
                                    val mapNode = readClass(target.mcpName)
                                    classes.add(reflectClassByMap(mapNode))
                                }
                            }
                        }
                    } else {
                        classes.add(aClass.java)
                    }
                }
                val mapNode = readClass(wrapperClass.mcpName)
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
                        if (annotation is WrapFields) {
                            for (wrapField in annotation.value) {
                                applyField(wrapperClass, wrapField, declaredField)
                            }
                        } else if (annotation is WrapField) {
                            applyField(wrapperClass, annotation, declaredField)
                        } else if (annotation is WrapMethods) {
                            for (wrapMethod in annotation.value) {
                                applyMethod(wrapperClass, wrapMethod, declaredField)
                            }
                        } else if (annotation is WrapMethod) {
                            applyMethod(wrapperClass, annotation, declaredField)
                        } else if (annotation is WrapClasses) {
                            for (wrapClass in annotation.value) {
                                applyClass(wrapperClass, wrapClass, declaredField)
                            }
                        } else if (annotation is WrapClass) {
                            applyClass(wrapperClass, annotation, declaredField)
                        } else if (annotation is WrapEnums) {
                            for (wrapEnum in annotation.value) {
                                applyEnum(wrapperClass, wrapEnum, declaredField)
                            }
                        } else if (annotation is WrapEnum) {
                            applyEnum(wrapperClass, annotation, declaredField)
                        } else if (annotation is WrapObjects) {
                            for (wrapObject in annotation.value) {
                                applyObject(wrapperClass, wrapObject, declaredField)
                            }
                        } else if (annotation is WrapObject) {
                            applyObject(wrapperClass, annotation, declaredField)
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
                var mapNode = readField(wrapperClass.mcpName, wrapEnum.mcpName)
                if (wrapEnum.customSrgName != "none") {
                    val clazz = readClass(wrapperClass.mcpName)
                    if (clazz != null) mapNode =
                        MapNode(NodeType.Field, "", clazz.srg.replace(".", "/") + wrapEnum.customSrgName)
                }
                if (mapNode != null) {
                    try {
                        declaredField.isAccessible = true
                        declaredField[null] = reflectEnumByMap(mapNode)
                    } catch (e: Exception) {
                        error(
                            "Failed to apply Enum: " + wrapperClass.mcpName + " " + wrapEnum.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                        e.printStackTrace()
                    }
                    debug(
                        "Successful apply Enum: " + wrapperClass.mcpName + " " + wrapEnum.mcpName + " -> " + mapNode.srg,
                        "Wrapper"
                    )
                } else {
                    error("Failed to find Enum in SrgMaps: " + wrapEnum.mcpName, "Wrapper")
                }
            }
        }
    }

    @Throws(ClassNotFoundException::class)
    private fun reflectEnumByMap(mapNode: MapNode): Enum<*> {
        val srg = mapNode.srg
        val field = srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size - 1]
        val clazz = srg.replace("/", ".").replace(".$field", "")
        val c = reader!!.getClassNative(clazz)
        for (enumConstant in c.enumConstants) {
            if (enumConstant is Enum<*>) {
                if (enumConstant.name == field) return enumConstant
            }
        }
        return reflectEnumByMapMcp(mapNode, c)
    }

    private fun reflectEnumByMapMcp(mapNode: MapNode, c: Class<*>): Enum<*> {
        val srg = mapNode.mcp
        val field = srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size - 1]
        for (enumConstant in c.enumConstants) {
            if (enumConstant is Enum<*>) {
                if (enumConstant.name == field) return enumConstant
            }
        }
        throw NullPointerException("Can't wrap: " + mapNode.mcp + " -> " + c.canonicalName + "." + field + "]")
    }

    private fun applyObject(wrapperClass: WrapperClass, wrapObject: WrapObject, declaredField: Field) {
        if (wrapObject.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                var mapNode = readField(wrapperClass.mcpName, wrapObject.mcpName)
                if (wrapObject.customSrgName != "none") {
                    val clazz = readClass(wrapperClass.mcpName)
                    if (clazz != null) mapNode =
                        MapNode(NodeType.Field, "", clazz.srg.replace(".", "/") + wrapObject.customSrgName)
                }
                if (mapNode != null) {
                    try {
                        declaredField.isAccessible = true
                        declaredField[null] = reflectFieldByMap(mapNode)[null]
                        debug(
                            "Successful Apply Object: " + wrapperClass.mcpName + " " + wrapObject.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        error(
                            "Failed to Apply Object: " + wrapperClass.mcpName + " " + wrapObject.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                    }
                } else {
                    error("Failed to find Object in SrgMap: " + wrapObject.mcpName, "Wrapper")
                }
            }
        }
    }

    private fun applyField(wrapperClass: WrapperClass, wrapField: WrapField, declaredField: Field) {
        if (wrapField.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                var mapNode = readField(wrapperClass.mcpName, wrapField.mcpName)
                if (wrapField.customSrgName != "none") {
                    val clazz = readClass(wrapperClass.mcpName)
                    if (clazz != null) mapNode =
                        MapNode(NodeType.Field, "", clazz.srg.replace(".", "/") + wrapField.customSrgName)
                }
                if (mapNode != null) {
                    try {
                        declaredField.isAccessible = true
                        declaredField[null] = reflectFieldByMap(mapNode)
                        debug(
                            "Successful Apply Field: " + wrapperClass.mcpName + " " + wrapField.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        error(
                            "Failed to Apply Field: " + wrapperClass.mcpName + " " + wrapField.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                    }
                } else {
                    error("Failed to find Field in SrgMap: " + wrapField.mcpName, "Wrapper")
                }
            }
        }
    }

    private fun applyClass(wrapperClass: WrapperClass, wrapClass: WrapClass, declaredField: Field) {
        if (wrapClass.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                //ReadMap
                val mapNode = readClass(wrapClass.mcpName)
                if (mapNode != null) {
                    try {
                        declaredField.isAccessible = true
                        declaredField[null] = reflectClassByMap(mapNode)
                        debug(
                            "Successful Apply Class: " + wrapperClass.mcpName + " " + wrapClass.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        error(
                            "Failed to Apply Class: " + wrapperClass.mcpName + " " + wrapClass.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                    }
                } else {
                    error("Failed to find Class in SrgMap: " + wrapClass.mcpName, "Wrapper")
                }
            }
        }
    }

    private fun applyMethod(wrapperClass: WrapperClass, wrapMethod: WrapMethod, declaredField: Field) {
        if (wrapMethod.targetMap == MapEnv) {
            if (Modifier.isStatic(declaredField.modifiers)) {
                val mapNode = if (wrapMethod.signature == "none") readMethod(
                    wrapperClass.mcpName,
                    wrapMethod.mcpName,
                    null
                ) else readMethod(wrapperClass.mcpName, wrapMethod.mcpName, wrapMethod.signature)
                if (mapNode != null) {
                    if (mapNode.srg == null) {
                        error("null" + " " + wrapperClass.mcpName + " " + wrapMethod.mcpName, "Wrapper")
                    }
                    try {
                        declaredField.isAccessible = true
                        declaredField[null] = reflectMethodByMap(mapNode)
                    } catch (e: Exception) {
                        error(
                            "Failed to Apply Method: " + wrapperClass.mcpName + " " + wrapMethod.mcpName + " -> " + mapNode.srg,
                            "Wrapper"
                        )
                        e.printStackTrace()
                    }
                    debug(
                        "Successful Apply Method: " + wrapperClass.mcpName + " " + wrapMethod.mcpName + " -> " + mapNode.srg,
                        "Wrapper"
                    )
                } else {
                    error("Failed to find Method in SrgMap: " + wrapMethod.mcpName, "Wrapper")
                }
            }
        }
    }

    @Throws(ClassNotFoundException::class)
    private fun reflectFieldByMap(mapNode: MapNode): Field {
        val srg = if (useMapObf) mapNode.srg else mapNode.mcp
        val field = srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size - 1]
        val clazz = srg.replace("/", ".").replace(".$field", "")
        val c = reader!!.getClassNative(clazz)
        for (cField in c.fields) {
            if (cField.name == field) {
                cField.isAccessible = true
                return cField
            }
        }
        for (declaredField in c.declaredFields) {
            if (declaredField.name == field) {
                declaredField.isAccessible = true
                return declaredField
            }
        }
        throw NullPointerException("Can't wrap: " + mapNode.mcp + " -> " + c.canonicalName + "." + field + "]")
    }

    @Throws(ClassNotFoundException::class)
    private fun reflectClassByMap(mapNode: MapNode?): Class<*> {
        val srg = if (useMapObf) mapNode!!.srg else mapNode!!.mcp
        debug("reflectClassByMap: " + mapNode.srg + " / " + mapNode.mcp, "Wrapper")
        return reader!!.getClassNative(srg.replace("/", "."))
    }

    @Throws(ClassNotFoundException::class, NoSuchMethodException::class)
    private fun reflectMethodByMap(mapNode: MapNode): Method {
        if (mapNode is MethodNode) {
            val srg = if (useMapObf) mapNode.getSrg() else mapNode.getMcp()
            val method = srg.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[srg.split("/".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray().size - 1]
            val clazz = srg.replace("/", ".").replace(".$method", "")
            val c = reader!!.getClassNative(clazz)
            debug(
                "Try Get Method: " + clazz + " " + method + " " + Arrays.toString(
                    mapNode.signature.args
                ), "Wrapper"
            )
            val m = c.getDeclaredMethod(method, *mapNode.signature.args)
            m.isAccessible = true
            return m
        }
        throw NoSuchMethodException("Can't wrap: " + mapNode.mcp)
    }

    fun readField(clazz: String, field: String): MapNode? {
        val map = clazz.replace(".", "/") + "/" + field
        for (mapNode in reader!!.mapNodes) {
            if (mapNode.nodeType == NodeType.Field && mapNode.mcp == map) {
                return mapNode
            }
        }
        return null
    }

    fun readClass(clazz: String): MapNode? {
        val map = clazz.replace(".", "/")
        for (mapNode in reader!!.mapNodes) {
            if (mapNode.nodeType == NodeType.Class && mapNode.mcp == map) {
                return mapNode
            }
        }
        return null
    }

    fun readMethod(clazz: String, method: String, customSig: String?): MapNode? {
        val map = clazz.replace(".", "/") + "/" + method
        for (mapNode in reader!!.mapNodes) {
            if (mapNode.nodeType == NodeType.Method && mapNode.mcp == map) {
                if (customSig != null) {
                    if (customSig == (mapNode as MethodNode).deobfSig) {
                        return mapNode
                    }
                } else {
                    return mapNode
                }
            }
        }
        return null
    }
}