package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClass
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import java.lang.reflect.Method
import java.nio.FloatBuffer

@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = MapEnum.VANILLA189)
object GlStateManager {
    @WrapClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = MapEnum.VANILLA189)
    lateinit var GlStateManagerClass: Class<*>

    @WrapMethod(mcpName = "alphaFunc", targetMap = MapEnum.VANILLA189)
    lateinit var alphaFunc: Method

    @WrapMethod(mcpName = "bindTexture", targetMap = MapEnum.VANILLA189)
    lateinit var bindTexture: Method

    @WrapMethod(mcpName = "blendFunc", targetMap = MapEnum.VANILLA189)
    lateinit var blendFunc: Method

    @WrapMethod(mcpName = "callList", targetMap = MapEnum.VANILLA189)
    lateinit var callList: Method

    @WrapMethod(mcpName = "clear", targetMap = MapEnum.VANILLA189)
    lateinit var clear: Method

    @WrapMethod(mcpName = "clearColor", targetMap = MapEnum.VANILLA189)
    lateinit var clearColor: Method

    @WrapMethod(mcpName = "clearDepth", targetMap = MapEnum.VANILLA189)
    lateinit var clearDepth: Method

    @WrapMethod(mcpName = "color", targetMap = MapEnum.VANILLA189, signature = "(FFF)V")
    lateinit var color_FFF: Method

    @WrapMethod(mcpName = "color", targetMap = MapEnum.VANILLA189, signature = "(FFFF)V")
    lateinit var color_FFFF: Method

    @WrapMethod(mcpName = "colorLogicOp", targetMap = MapEnum.VANILLA189)
    lateinit var colorLogicOp: Method

    @WrapMethod(mcpName = "colorMask", targetMap = MapEnum.VANILLA189)
    lateinit var colorMask: Method

    @WrapMethod(mcpName = "colorMaterial", targetMap = MapEnum.VANILLA189)
    lateinit var colorMaterial: Method

    @WrapMethod(mcpName = "cullFace", targetMap = MapEnum.VANILLA189)
    lateinit var cullFace: Method

    @WrapMethod(mcpName = "deleteTexture", targetMap = MapEnum.VANILLA189)
    lateinit var deleteTexture: Method

    @WrapMethod(mcpName = "depthFunc", targetMap = MapEnum.VANILLA189)
    lateinit var depthFunc: Method

    @WrapMethod(mcpName = "depthMask", targetMap = MapEnum.VANILLA189)
    lateinit var depthMask: Method

    @WrapMethod(mcpName = "disableAlpha", targetMap = MapEnum.VANILLA189)
    lateinit var disableAlpha: Method

    @WrapMethod(mcpName = "disableBlend", targetMap = MapEnum.VANILLA189)
    lateinit var disableBlend: Method

    @WrapMethod(mcpName = "disableColorLogic", targetMap = MapEnum.VANILLA189)
    lateinit var disableColorLogic: Method

    @WrapMethod(mcpName = "disableColorMaterial", targetMap = MapEnum.VANILLA189)
    lateinit var disableColorMaterial: Method

    @WrapMethod(mcpName = "disableCull", targetMap = MapEnum.VANILLA189)
    lateinit var disableCull: Method

    @WrapMethod(mcpName = "disableDepth", targetMap = MapEnum.VANILLA189)
    lateinit var disableDepth: Method

    @WrapMethod(mcpName = "disableFog", targetMap = MapEnum.VANILLA189)
    lateinit var disableFog: Method

    @WrapMethod(mcpName = "disableLight", targetMap = MapEnum.VANILLA189)
    lateinit var disableLight: Method

    @WrapMethod(mcpName = "disableLighting", targetMap = MapEnum.VANILLA189)
    lateinit var disableLighting: Method

    @WrapMethod(mcpName = "disableNormalize", targetMap = MapEnum.VANILLA189)
    lateinit var disableNormalize: Method

    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = MapEnum.VANILLA189)
    lateinit var disablePolygonOffset: Method

    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = MapEnum.VANILLA189)
    lateinit var disableRescaleNormal: Method

    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = MapEnum.VANILLA189)
    lateinit var disableTexGenCoord: Method

    @WrapMethod(mcpName = "disableTexture2D", targetMap = MapEnum.VANILLA189)
    lateinit var disableTexture2D: Method

    @WrapMethod(mcpName = "doPolygonOffset", targetMap = MapEnum.VANILLA189)
    lateinit var doPolygonOffset: Method

    @WrapMethod(mcpName = "enableAlpha", targetMap = MapEnum.VANILLA189)
    lateinit var enableAlpha: Method

    @WrapMethod(mcpName = "enableBlend", targetMap = MapEnum.VANILLA189)
    lateinit var enableBlend: Method

    @WrapMethod(mcpName = "enableColorLogic", targetMap = MapEnum.VANILLA189)
    lateinit var enableColorLogic: Method

    @WrapMethod(mcpName = "enableColorMaterial", targetMap = MapEnum.VANILLA189)
    lateinit var enableColorMaterial: Method

    @WrapMethod(mcpName = "enableCull", targetMap = MapEnum.VANILLA189)
    lateinit var enableCull: Method

    @WrapMethod(mcpName = "enableDepth", targetMap = MapEnum.VANILLA189)
    lateinit var enableDepth: Method

    @WrapMethod(mcpName = "enableFog", targetMap = MapEnum.VANILLA189)
    lateinit var enableFog: Method

    @WrapMethod(mcpName = "enableLight", targetMap = MapEnum.VANILLA189)
    lateinit var enableLight: Method

    @WrapMethod(mcpName = "enableLighting", targetMap = MapEnum.VANILLA189)
    lateinit var enableLighting: Method

    @WrapMethod(mcpName = "enableNormalize", targetMap = MapEnum.VANILLA189)
    lateinit var enableNormalize: Method

    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = MapEnum.VANILLA189)
    lateinit var enablePolygonOffset: Method

    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = MapEnum.VANILLA189)
    lateinit var enableRescaleNormal: Method

    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = MapEnum.VANILLA189)
    lateinit var enableTexGenCoord: Method

    @WrapMethod(mcpName = "enableTexture2D", targetMap = MapEnum.VANILLA189)
    lateinit var enableTexture2D: Method

    @WrapMethod(mcpName = "generateTexture", targetMap = MapEnum.VANILLA189)
    lateinit var generateTexture: Method

    @WrapMethod(mcpName = "getFloat", targetMap = MapEnum.VANILLA189)
    lateinit var getFloat: Method

    @WrapMethod(mcpName = "loadIdentity", targetMap = MapEnum.VANILLA189)
    lateinit var loadIdentity: Method

    @WrapMethod(mcpName = "matrixMode", targetMap = MapEnum.VANILLA189)
    lateinit var matrixMode: Method

    @WrapMethod(mcpName = "multMatrix", targetMap = MapEnum.VANILLA189)
    lateinit var multMatrix: Method

    @WrapMethod(mcpName = "ortho", targetMap = MapEnum.VANILLA189)
    lateinit var ortho: Method

    @WrapMethod(mcpName = "popAttrib", targetMap = MapEnum.VANILLA189)
    lateinit var popAttrib: Method

    @WrapMethod(mcpName = "popMatrix", targetMap = MapEnum.VANILLA189)
    lateinit var popMatrix: Method

    @WrapMethod(mcpName = "pushAttrib", targetMap = MapEnum.VANILLA189)
    lateinit var pushAttrib: Method

    @WrapMethod(mcpName = "pushMatrix", targetMap = MapEnum.VANILLA189)
    lateinit var pushMatrix: Method

    @WrapMethod(mcpName = "resetColor", targetMap = MapEnum.VANILLA189)
    lateinit var resetColor: Method

    @WrapMethod(mcpName = "rotate", targetMap = MapEnum.VANILLA189)
    lateinit var rotate: Method

    @WrapMethod(mcpName = "scale", targetMap = MapEnum.VANILLA189, signature = "(DDD)V")
    lateinit var scale_DDD: Method

    @WrapMethod(mcpName = "scale", targetMap = MapEnum.VANILLA189, signature = "(FFF)V")
    lateinit var scale_FFF: Method

    @WrapMethod(mcpName = "setActiveTexture", targetMap = MapEnum.VANILLA189)
    lateinit var setActiveTexture: Method

    @WrapMethod(mcpName = "setFog", targetMap = MapEnum.VANILLA189)
    lateinit var setFog: Method

    @WrapMethod(mcpName = "setFogDensity", targetMap = MapEnum.VANILLA189)
    lateinit var setFogDensity: Method

    @WrapMethod(mcpName = "setFogEnd", targetMap = MapEnum.VANILLA189)
    lateinit var setFogEnd: Method

    @WrapMethod(mcpName = "setFogStart", targetMap = MapEnum.VANILLA189)
    lateinit var setFogStart: Method

    @WrapMethod(mcpName = "shadeModel", targetMap = MapEnum.VANILLA189)
    lateinit var shadeModel: Method

    @WrapMethod(
        mcpName = "texGen",
        targetMap = MapEnum.VANILLA189,
        signature = "(Lnet/minecraft/client/renderer/GlStateManager\$TexGen;ILjava/nio/FloatBuffer;)V"
    )
    lateinit var texGen_NJ: Method

    @WrapMethod(
        mcpName = "texGen",
        targetMap = MapEnum.VANILLA189,
        signature = "(Lnet/minecraft/client/renderer/GlStateManager\$TexGen;I)V"
    )
    lateinit var texGen_NI: Method

    @WrapMethod(mcpName = "texGenCoord", targetMap = MapEnum.VANILLA189)
    lateinit var texGenCoord: Method

    @WrapMethod(mcpName = "translate", targetMap = MapEnum.VANILLA189, signature = "(FFF)V")
    lateinit var translate_FFF: Method

    @WrapMethod(mcpName = "translate", targetMap = MapEnum.VANILLA189, signature = "(DDD)V")
    lateinit var translate_DDD: Method

    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = MapEnum.VANILLA189)
    lateinit var tryBlendFuncSeparate: Method

    @WrapMethod(mcpName = "viewport", targetMap = MapEnum.VANILLA189)
    lateinit var viewport: Method

//    var glTexEnvi: Method
//    fun glTexEnvi(glTextureEnv: Int, glTextureEnvMode: Int, glModulate: Int) {
//        GL11.glTexEnvi(glTextureEnv, glTextureEnvMode, glModulate)
//    }

    @JvmStatic
    fun enableColorMaterial() {
        ReflectUtil.invokeStatic(enableColorMaterial)
    }

    @JvmStatic
    fun disableRescaleNormal() {
        ReflectUtil.invokeStatic(disableRescaleNormal)
    }

    @JvmStatic
    fun color(f1: Float, f2: Float, f3: Float, f4: Float) {
        ReflectUtil.invokeStatic(color_FFFF, f1, f2, f3, f4)
    }

    @JvmStatic
    fun disableCull() {
        ReflectUtil.invokeStatic(disableCull)
    }

    @JvmStatic
    fun disableDepth() {
        ReflectUtil.invokeStatic(disableDepth)
    }

    @JvmStatic
    fun depthMask(flag: Boolean) {
        ReflectUtil.invokeStatic(depthMask, flag)
    }

    fun disableLighting() {
        ReflectUtil.invokeStatic(disableLighting)
    }

    @JvmStatic
    fun enableAlpha() {
        ReflectUtil.invokeStatic(enableAlpha)
    }

    @JvmStatic
    fun disableAlpha() {
        ReflectUtil.invokeStatic(disableAlpha)
    }

    @JvmStatic
    fun disableBlend() {
        ReflectUtil.invokeStatic(disableBlend)
    }

    @JvmStatic
    fun enableBlend() {
        ReflectUtil.invokeStatic(enableBlend)
    }

    @JvmStatic
    fun bindTexture(texture: Int) {
        ReflectUtil.invokeStatic(bindTexture, texture)
    }

    @JvmStatic
    fun blendFunc(srcFactor: Int, dstFactor: Int) {
        ReflectUtil.invokeStatic(blendFunc, srcFactor, dstFactor)
    }

    @JvmStatic
    fun enableDepth() {
        ReflectUtil.invokeStatic(enableDepth)
    }

    @JvmStatic
    fun clear(mask: Int) {
        ReflectUtil.invokeStatic(clear, mask)
    }

    @JvmStatic
    fun pushMatrix() {
        ReflectUtil.invokeStatic(pushMatrix)
    }

    @JvmStatic
    fun popMatrix() {
        ReflectUtil.invokeStatic(popMatrix)
    }

    @JvmStatic
    fun rotate(angle: Float, x: Float, y: Float, z: Float) {
        ReflectUtil.invokeStatic(rotate, angle, x, y, z)
    }

    @JvmStatic
    fun setActiveTexture(texture: Int) {
        ReflectUtil.invokeStatic(setActiveTexture, texture)
    }

    @JvmStatic
    fun scale(x: Double, y: Double, z: Double) {
        ReflectUtil.invokeStatic(scale_DDD, x, y, z)
    }

    @JvmStatic
    fun translate(x: Float, y: Float, z: Float) {
        ReflectUtil.invokeStatic(translate_FFF, x, y, z)
    }

    @JvmStatic
    fun translate(x: Double, y: Double, z: Double) {
        ReflectUtil.invokeStatic(translate_DDD, x, y, z)
    }

    @JvmStatic
    fun multMatrix(matrix: FloatBuffer?) {
        ReflectUtil.invokeStatic(multMatrix, matrix)
    }

    @JvmStatic
    fun enableTexture2D() {
        ReflectUtil.invokeStatic(enableTexture2D)
    }

    @JvmStatic
    fun disableTexture2D() {
        ReflectUtil.invokeStatic(disableTexture2D)
    }

    @JvmStatic
    fun resetColor() {
        ReflectUtil.invokeStatic(resetColor)
    }

    @JvmStatic
    fun tryBlendFuncSeparate(srcFactor: Int, dstFactor: Int, srcFactorAlpha: Int, dstFactorAlpha: Int) {
        ReflectUtil.invokeStatic(tryBlendFuncSeparate, srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha)
    }

    @JvmStatic
    fun shadeModel(mode: Int) {
        ReflectUtil.invokeStatic(shadeModel, mode)
    }

    @JvmStatic
    fun disableLight(light: Int) {
        ReflectUtil.invokeStatic(disableLight, light)
    }

    @JvmStatic
    fun disableColorMaterial() {
        ReflectUtil.invokeStatic(disableColorMaterial)
    }

    @JvmStatic
    fun color(i: Float, i1: Float, i2: Float) {
        ReflectUtil.invokeStatic(color_FFF, i, i1, i2)
    }

    @JvmStatic
    fun alphaFunc(func: Int, ref: Float) {
        ReflectUtil.invokeStatic(alphaFunc, func, ref)
    }

    @JvmStatic
    fun enableLighting() {
        ReflectUtil.invokeStatic(enableLighting)
    }
}