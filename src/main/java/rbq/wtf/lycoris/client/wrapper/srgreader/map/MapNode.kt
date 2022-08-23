package rbq.wtf.lycoris.client.wrapper.srgreader.map

import rbq.wtf.lycoris.client.utils.Logger
import rbq.wtf.lycoris.client.wrapper.Wrapper
import java.util.StringJoiner

abstract class MapNode(val nodeType: NodeType, val mcpName: String, val srgName: String) {
    fun getName(obfuscation: Boolean) = if (obfuscation) srgName else mcpName
//    val useObf: Boolean
//        get() = Wrapper.useMapObf
}
class ClassNode(
    nodeType: NodeType,
    mcpClassName: String,
    srgClassName: String,
) : MapNode(nodeType, mcpClassName, srgClassName) {
    fun getClassName(obfuscation: Boolean) = getName(obfuscation)
//    val className: String
//        get() = getClassName(useObf)

    fun equals(otherClassName: String, obfuscation: Boolean): Boolean {
        return getClassName(obfuscation) == otherClassName
    }
}

class FieldNode(
    nodeType: NodeType,
    mcpName: String,
    val mcpClassName: String,
    val mcpFieldName: String,
    srgName: String,
    val srgClassName: String,
    val srgFieldName: String,
) : MapNode(nodeType, mcpName, srgName) {
    fun getClassName(obfuscation: Boolean) = if (obfuscation) srgClassName else mcpClassName
    fun getFieldName(obfuscation: Boolean) = if (obfuscation) srgFieldName else mcpFieldName

//    val className: String
//        get() = getClassName(useObf)
//
//    val fieldName: String
//        get() = getFieldName(useObf)

    fun equals(otherClassName: String, otherFieldName: String, obfuscation: Boolean): Boolean {
//        if (otherClassName == getClassName(obfuscation)) {
//            Logger.debug("EQUALS: $otherClassName == ${getClassName(obfuscation)} && $otherFieldName == ${getFieldName(obfuscation)}")
//            Logger.debug("EQUALSL: ${otherClassName.length} ${getClassName(obfuscation).length} && ${otherFieldName.length} == ${getFieldName(obfuscation).length}")
//        }
        return getClassName(obfuscation) == otherClassName && getFieldName(obfuscation) == otherFieldName
    }
}

class MethodNode(
    nodeType: NodeType,
    mcpName: String,
    private val mcpClassName: String,
    private val mcpMethodName: String,
    private val mcpSignature: Signature,
    private val mcpSignatureString: String,
    srgName: String,
    private val srgClassName: String,
    private val srgMethodName: String,
    private val srgSignature: Signature,
    private val srgSignatureString: String
) : MapNode(nodeType, mcpName, srgName) {
    fun getClassName(obfuscation: Boolean) = if (obfuscation) srgClassName else mcpClassName
    fun getMethodName(obfuscation: Boolean) = if (obfuscation) srgMethodName else mcpMethodName
    fun getSignature(obfuscation: Boolean) = if (obfuscation) srgSignature else mcpSignature
    fun getSignatureString(obfuscation: Boolean) = if (obfuscation) srgSignatureString else mcpSignatureString

//    val className: String
//        get() = getClassName(useObf)
//    val methodName: String
//        get() = getMethodName(useObf)
//    val signature: Signature
//        get() = getSignature(useObf)
//    val signatureString: String
//        get() = getSignatureString(useObf)
    fun equals(otherClassName: String, otherMethodName: String, otherSignatureString: String?, obfuscation: Boolean): Boolean {
        return getClassName(obfuscation) == otherClassName && getMethodName(obfuscation) == otherMethodName && (otherSignatureString == null || otherSignatureString == getSignatureString(obfuscation))
    }
}