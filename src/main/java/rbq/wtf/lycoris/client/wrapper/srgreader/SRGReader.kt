package rbq.wtf.lycoris.client.wrapper.srgreader

import rbq.wtf.lycoris.client.utils.FileUtils
import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.utils.StringStream
import rbq.wtf.lycoris.client.wrapper.srgreader.map.*
import java.io.File

class SRGReader(srgMap: File) {
    private val mapNodes: List<MapNode>

    //private List<Class<?>> loadedClasses = new ArrayList<>();
    init {
        mapNodes = readMap(FileUtils.readFileString(srgMap))
    }

    private fun readMap(srgMap: String): List<MapNode> {
        val mapNodes: ArrayList<MapNode> = ArrayList()
        // ASCII 10: 换行符 \n
        // ASCII 13： LE 回车符
        // System.lineSeparator(): ASCII 10 + ASCII 13 (两个字符)
        for (s in srgMap.split(10.toChar()).dropLastWhile { it.isEmpty() }) {
            try {
                val strings = s.split(" ")
                if (strings.isNotEmpty()) {
                    when(val type = getNodeType(strings[0])) {
                        NodeType.Class -> {
                            if (strings.size != 3) throw NullPointerException("Can't Parse SrgMap $s")
                            val mcpName = strings[1].replace("/", ".")
                            val srgName = strings[2].replace("/", ".")
                            mapNodes.add(
                                ClassNode(type, mcpName, srgName)
                            )
                        }
                        NodeType.Field -> {
                            if (strings.size != 3) throw NullPointerException("Can't Parse SrgMap $s")
                            val mcpName = strings[1]
                            val srgName = strings[2]
                            val mcpFieldName = mcpName.split("/").last()
                            val srgFieldName = srgName.split("/").last()
                            val mcpClassName = mcpName.replace("/", ".").replace(".$mcpFieldName", "")
                            val srgClassName = srgName.replace("/", ".").replace(".$srgFieldName", "")
                            mapNodes.add(
                                FieldNode(type, mcpName, mcpClassName, mcpFieldName, srgName, srgClassName, srgFieldName)
                            )
                        }
                        NodeType.Method -> {
                            if (strings.size != 5) {throw NullPointerException("Can't Parse SrgMap $s")}
                            val mcpName = strings[1]
                            val srgName = strings[3]
                            val mcpMethodName = mcpName.split("/").last()
                            val srgMethodName = srgName.split("/").last()
                            val mcpClassName = mcpName.replace("/", ".").replace(".$mcpMethodName", "")
                            val srgClassName = srgName.replace("/", ".").replace(".$srgMethodName", "")
                            val mcpSignatureString = strings[2]
                            val srgSignatureString = strings[4]
                            val mcpSignature = genSignature(mcpSignatureString)
                            val srgSignature = genSignature(srgSignatureString)
                            mapNodes.add(
                                MethodNode(type, mcpName, mcpClassName, mcpMethodName, mcpSignature,mcpSignatureString, srgName, srgClassName, srgMethodName, srgSignature, srgSignatureString)
                            )
                        }
                        else -> throw NullPointerException("Can't Parse SrgMap $s")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return mapNodes
    }

    private fun genSignature(sig: String): Signature {
        val args: ArrayList<Class<*>> = ArrayList() //方法传入参数列表
        var returnType: Class<*> = Void.TYPE //返回值类型,默认为void
        var onReadingClassName = false //是否在阅读ClassName
        var onReadingArgs = true //是否在阅读参数
        var className = StringBuilder()
        // 示例:
        // (Lnet/minecraft/util/EnumFacing;)Z
        // (I)Lnet/minecraft/block/state/IBlockState;
        // (Lnet/minecraft/network/INetHandler;)V
        val ss = StringStream(sig)
        while (ss.available()) {
            val t = ss.read() // 获取一个字符
            if (onReadingClassName) {
                if (t == ";") { // 一个ClassName阅读到末尾了,开始解析
                    try {
                        val target = Class.forName(className.toString().replace("/", ".")) //loadClass?
                        if (onReadingArgs) args.add(target) else returnType = target
                    } catch (e: Exception) {
                        if (className.toString().contains("net/minecraft/server")) {
                            Logger.warning("Failed to find a Server Class: $className, Ignored", "SRGReader")
                        } else {
                            e.printStackTrace()
                            Logger.error("Failed to find Class: $className", "SRGReader")
                            Logger.error("Failed to Generate Signature: $sig", "SRGReader")
                        }
                    }
                    onReadingClassName = false
                } else {
                    className.append(t)
                }
                continue
            }
            when (t) {
                "(" -> onReadingArgs = true
                ")" -> onReadingArgs = false
                "L" -> {
                    onReadingClassName = true
                    className = StringBuilder()
                }

                "Z" -> if (onReadingArgs) args.add(Boolean::class.javaPrimitiveType!!) else returnType =
                    Boolean::class.javaPrimitiveType!!

                "C" -> if (onReadingArgs) args.add(Char::class.javaPrimitiveType!!) else returnType =
                    Char::class.javaPrimitiveType!!

                "B" -> if (onReadingArgs) args.add(Byte::class.javaPrimitiveType!!) else returnType =
                    Byte::class.javaPrimitiveType!!

                "S" -> if (onReadingArgs) args.add(Short::class.javaPrimitiveType!!) else returnType =
                    Short::class.javaPrimitiveType!!

                "I" -> if (onReadingArgs) args.add(Int::class.javaPrimitiveType!!) else returnType =
                    Int::class.javaPrimitiveType!!

                "F" -> if (onReadingArgs) args.add(Float::class.javaPrimitiveType!!) else returnType =
                    Float::class.javaPrimitiveType!!

                "J" -> if (onReadingArgs) args.add(Long::class.javaPrimitiveType!!) else returnType =
                    Long::class.javaPrimitiveType!!

                "D" -> if (onReadingArgs) args.add(Double::class.javaPrimitiveType!!) else returnType =
                    Double::class.javaPrimitiveType!!

                "V" -> if (!onReadingArgs) returnType = Void.TYPE
                "[" -> {}
                else -> Logger.error("Found a Unknown Identifier: $t", "SRGReader")
            }
        }
        val classes = args.toArray(emptyArray<Class<*>>())
        return Signature(classes, returnType)
    }

    private fun getNodeType(type: String): NodeType {
        when (type) {
            "CL:" -> return NodeType.Class
            "FD:" -> return NodeType.Field
            "MD:" -> return NodeType.Method
        }
        throw NullPointerException("Failed to Parse NodeType: $type")
    }

    fun getClass(className: String, obfuscation: Boolean): ClassNode {
        for (mapNode in mapNodes) {
            if (mapNode is ClassNode && mapNode.equals(className, obfuscation)) {
                return mapNode
            }
        }
        throw NullPointerException("Failed to Get Class in SRGMap: $className")
    }

    fun getField(className: String, field: String, obfuscation: Boolean): FieldNode {
        for (mapNode in mapNodes) {
            if (mapNode is FieldNode && mapNode.equals(className, field, obfuscation)) {
                return mapNode
            }
        }
        throw NullPointerException("Failed to Get Field in SRGMap: Field: $field Class: $className")
    }

    fun getMethod(className: String, method: String, customSignature: String?, obfuscation: Boolean): MethodNode {
        for (mapNode in mapNodes) {
            if (mapNode is MethodNode && mapNode.equals(className, method, customSignature, obfuscation)) {
                    return mapNode
            }
        }
        throw NullPointerException("Failed to Get Method in SRGMap: Method: $method Class: $className Sig:$customSignature")
    }
}