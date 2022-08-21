package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;

import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;

@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = MapEnum.VANILLA189)
public class GlStateManager {
    @WrapClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = MapEnum.VANILLA189)
    public static Class<?> GlStateManagerClass;
    @WrapMethod(mcpName = "alphaFunc", targetMap = MapEnum.VANILLA189)
    public static Method alphaFunc;
    @WrapMethod(mcpName = "bindTexture", targetMap = MapEnum.VANILLA189)
    public static Method bindTexture;
    @WrapMethod(mcpName = "blendFunc", targetMap = MapEnum.VANILLA189)
    public static Method blendFunc;
    @WrapMethod(mcpName = "callList", targetMap = MapEnum.VANILLA189)
    public static Method callList;
    @WrapMethod(mcpName = "clear", targetMap = MapEnum.VANILLA189)
    public static Method clear;
    @WrapMethod(mcpName = "clearColor", targetMap = MapEnum.VANILLA189)
    public static Method clearColor;
    @WrapMethod(mcpName = "clearDepth", targetMap = MapEnum.VANILLA189)
    public static Method clearDepth;
    @WrapMethod(mcpName = "color", targetMap = MapEnum.VANILLA189, signature = "(FFF)V")
    public static Method color_FFF;
    @WrapMethod(mcpName = "color", targetMap = MapEnum.VANILLA189, signature = "(FFFF)V")
    public static Method color_FFFF;
    @WrapMethod(mcpName = "colorLogicOp", targetMap = MapEnum.VANILLA189)
    public static Method colorLogicOp;
    @WrapMethod(mcpName = "colorMask", targetMap = MapEnum.VANILLA189)
    public static Method colorMask;
    @WrapMethod(mcpName = "colorMaterial", targetMap = MapEnum.VANILLA189)
    public static Method colorMaterial;
    @WrapMethod(mcpName = "cullFace", targetMap = MapEnum.VANILLA189)
    public static Method cullFace;
    @WrapMethod(mcpName = "deleteTexture", targetMap = MapEnum.VANILLA189)
    public static Method deleteTexture;
    @WrapMethod(mcpName = "depthFunc", targetMap = MapEnum.VANILLA189)
    public static Method depthFunc;
    @WrapMethod(mcpName = "depthMask", targetMap = MapEnum.VANILLA189)
    public static Method depthMask;
    @WrapMethod(mcpName = "disableAlpha", targetMap = MapEnum.VANILLA189)
    public static Method disableAlpha;
    @WrapMethod(mcpName = "disableBlend", targetMap = MapEnum.VANILLA189)
    public static Method disableBlend;
    @WrapMethod(mcpName = "disableColorLogic", targetMap = MapEnum.VANILLA189)
    public static Method disableColorLogic;
    @WrapMethod(mcpName = "disableColorMaterial", targetMap = MapEnum.VANILLA189)
    public static Method disableColorMaterial;
    @WrapMethod(mcpName = "disableCull", targetMap = MapEnum.VANILLA189)
    public static Method disableCull;
    @WrapMethod(mcpName = "disableDepth", targetMap = MapEnum.VANILLA189)
    public static Method disableDepth;
    @WrapMethod(mcpName = "disableFog", targetMap = MapEnum.VANILLA189)
    public static Method disableFog;
    @WrapMethod(mcpName = "disableLight", targetMap = MapEnum.VANILLA189)
    public static Method disableLight;
    @WrapMethod(mcpName = "disableLighting", targetMap = MapEnum.VANILLA189)
    public static Method disableLighting;
    @WrapMethod(mcpName = "disableNormalize", targetMap = MapEnum.VANILLA189)
    public static Method disableNormalize;
    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = MapEnum.VANILLA189)
    public static Method disablePolygonOffset;
    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = MapEnum.VANILLA189)
    public static Method disableRescaleNormal;
    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = MapEnum.VANILLA189)
    public static Method disableTexGenCoord;
    @WrapMethod(mcpName = "disableTexture2D", targetMap = MapEnum.VANILLA189)
    public static Method disableTexture2D;
    @WrapMethod(mcpName = "doPolygonOffset", targetMap = MapEnum.VANILLA189)
    public static Method doPolygonOffset;
    @WrapMethod(mcpName = "enableAlpha", targetMap = MapEnum.VANILLA189)
    public static Method enableAlpha;
    @WrapMethod(mcpName = "enableBlend", targetMap = MapEnum.VANILLA189)
    public static Method enableBlend;
    @WrapMethod(mcpName = "enableColorLogic", targetMap = MapEnum.VANILLA189)
    public static Method enableColorLogic;
    @WrapMethod(mcpName = "enableColorMaterial", targetMap = MapEnum.VANILLA189)
    public static Method enableColorMaterial;
    @WrapMethod(mcpName = "enableCull", targetMap = MapEnum.VANILLA189)
    public static Method enableCull;
    @WrapMethod(mcpName = "enableDepth", targetMap = MapEnum.VANILLA189)
    public static Method enableDepth;
    @WrapMethod(mcpName = "enableFog", targetMap = MapEnum.VANILLA189)
    public static Method enableFog;
    @WrapMethod(mcpName = "enableLight", targetMap = MapEnum.VANILLA189)
    public static Method enableLight;
    @WrapMethod(mcpName = "enableLighting", targetMap = MapEnum.VANILLA189)
    public static Method enableLighting;
    @WrapMethod(mcpName = "enableNormalize", targetMap = MapEnum.VANILLA189)
    public static Method enableNormalize;
    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = MapEnum.VANILLA189)
    public static Method enablePolygonOffset;
    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = MapEnum.VANILLA189)
    public static Method enableRescaleNormal;
    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = MapEnum.VANILLA189)
    public static Method enableTexGenCoord;
    @WrapMethod(mcpName = "enableTexture2D", targetMap = MapEnum.VANILLA189)
    public static Method enableTexture2D;
    @WrapMethod(mcpName = "generateTexture", targetMap = MapEnum.VANILLA189)
    public static Method generateTexture;
    @WrapMethod(mcpName = "getFloat", targetMap = MapEnum.VANILLA189)
    public static Method getFloat;
    @WrapMethod(mcpName = "loadIdentity", targetMap = MapEnum.VANILLA189)
    public static Method loadIdentity;
    @WrapMethod(mcpName = "matrixMode", targetMap = MapEnum.VANILLA189)
    public static Method matrixMode;
    @WrapMethod(mcpName = "multMatrix", targetMap = MapEnum.VANILLA189)
    public static Method multMatrix;
    @WrapMethod(mcpName = "ortho", targetMap = MapEnum.VANILLA189)
    public static Method ortho;
    @WrapMethod(mcpName = "popAttrib", targetMap = MapEnum.VANILLA189)
    public static Method popAttrib;
    @WrapMethod(mcpName = "popMatrix", targetMap = MapEnum.VANILLA189)
    public static Method popMatrix;
    @WrapMethod(mcpName = "pushAttrib", targetMap = MapEnum.VANILLA189)
    public static Method pushAttrib;
    @WrapMethod(mcpName = "pushMatrix", targetMap = MapEnum.VANILLA189)
    public static Method pushMatrix;
    @WrapMethod(mcpName = "resetColor", targetMap = MapEnum.VANILLA189)
    public static Method resetColor;
    @WrapMethod(mcpName = "rotate", targetMap = MapEnum.VANILLA189)
    public static Method rotate;
    @WrapMethod(mcpName = "scale", targetMap = MapEnum.VANILLA189, signature = "(DDD)V")
    public static Method scale_DDD;
    @WrapMethod(mcpName = "scale", targetMap = MapEnum.VANILLA189, signature = "(FFF)V")
    public static Method scale_FFF;
    @WrapMethod(mcpName = "setActiveTexture", targetMap = MapEnum.VANILLA189)
    public static Method setActiveTexture;
    @WrapMethod(mcpName = "setFog", targetMap = MapEnum.VANILLA189)
    public static Method setFog;
    @WrapMethod(mcpName = "setFogDensity", targetMap = MapEnum.VANILLA189)
    public static Method setFogDensity;
    @WrapMethod(mcpName = "setFogEnd", targetMap = MapEnum.VANILLA189)
    public static Method setFogEnd;
    @WrapMethod(mcpName = "setFogStart", targetMap = MapEnum.VANILLA189)
    public static Method setFogStart;
    @WrapMethod(mcpName = "shadeModel", targetMap = MapEnum.VANILLA189)
    public static Method shadeModel;
    @WrapMethod(mcpName = "texGen", targetMap = MapEnum.VANILLA189, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    public static Method texGen_NJ;
    @WrapMethod(mcpName = "texGen", targetMap = MapEnum.VANILLA189, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    public static Method texGen_NI;
    @WrapMethod(mcpName = "texGenCoord", targetMap = MapEnum.VANILLA189)
    public static Method texGenCoord;
    @WrapMethod(mcpName = "translate", targetMap = MapEnum.VANILLA189, signature = "(FFF)V")
    public static Method translate_FFF;
    @WrapMethod(mcpName = "translate", targetMap = MapEnum.VANILLA189, signature = "(DDD)V")
    public static Method translate_DDD;
    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = MapEnum.VANILLA189)
    public static Method tryBlendFuncSeparate;
    @WrapMethod(mcpName = "viewport", targetMap = MapEnum.VANILLA189)
    public static Method viewport;
    public static Method glTexEnvi;

    public static void glTexEnvi(int glTextureEnv, int glTextureEnvMode, int glModulate) {
        GL11.glTexEnvi(glTextureEnv, glTextureEnvMode, glModulate);

    }

    public static void enableColorMaterial() {
        ReflectUtil.invokeStatic(enableColorMaterial);
    }

    public static void disableRescaleNormal() {
        ReflectUtil.invokeStatic(disableRescaleNormal);
    }

    public static void color(float f1, float f2, float f3, float f4) {
        ReflectUtil.invokeStatic(color_FFFF, f1, f2, f3, f4);
    }

    public static void disableCull() {
        ReflectUtil.invokeStatic(disableCull);
    }

    public static void disableDepth() {
        ReflectUtil.invokeStatic(disableDepth);
    }

    public static void depthMask(boolean flag) {
        ReflectUtil.invokeStatic(depthMask, flag);
    }

    public static void disableLighting() {
        ReflectUtil.invokeStatic(disableLighting);
    }

    public static void enableAlpha() {
        ReflectUtil.invokeStatic(enableAlpha);
    }

    public static void disableAlpha() {
        ReflectUtil.invokeStatic(disableAlpha);
    }

    public static void disableBlend() {
        ReflectUtil.invokeStatic(disableBlend);
    }

    public static void enableBlend() {
        ReflectUtil.invokeStatic(enableBlend);
    }

    public static void bindTexture(int texture) {
        ReflectUtil.invokeStatic(bindTexture, texture);
    }

    public static void blendFunc(int srcFactor, int dstFactor) {
        ReflectUtil.invokeStatic(blendFunc, srcFactor, dstFactor);
    }

    public static void enableDepth() {
        ReflectUtil.invokeStatic(enableDepth);
    }

    public static void clear(int mask) {
        ReflectUtil.invokeStatic(clear, mask);
    }

    public static void pushMatrix() {
        ReflectUtil.invokeStatic(pushMatrix);
    }

    public static void popMatrix() {
        ReflectUtil.invokeStatic(popMatrix);
    }

    public static void rotate(float angle, float x, float y, float z) {
        ReflectUtil.invokeStatic(rotate, angle, x, y, z);
    }

    public static void setActiveTexture(int texture) {
        ReflectUtil.invokeStatic(setActiveTexture, texture);
    }

    public static void scale(double x, double y, double z) {
        ReflectUtil.invokeStatic(scale_DDD, x, y, z);
    }

    public static void translate(float x, float y, float z) {
        ReflectUtil.invokeStatic(translate_FFF, x, y, z);
    }

    public static void translate(double x, double y, double z) {
        ReflectUtil.invokeStatic(translate_DDD, x, y, z);
    }

    public static void multMatrix(FloatBuffer matrix) {
        ReflectUtil.invokeStatic(multMatrix, matrix);
    }

    public static void enableTexture2D() {
        ReflectUtil.invokeStatic(enableTexture2D);
    }

    public static void disableTexture2D() {
        ReflectUtil.invokeStatic(disableTexture2D);
    }

    public static void resetColor() {
        ReflectUtil.invokeStatic(resetColor);
    }

    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        ReflectUtil.invokeStatic(tryBlendFuncSeparate, srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }

    public static void shadeModel(int mode) {
        ReflectUtil.invokeStatic(shadeModel, mode);
    }

    public static void disableLight(int light) {
        ReflectUtil.invokeStatic(disableLight, light);
    }

    public static void disableColorMaterial() {
        ReflectUtil.invokeStatic(disableColorMaterial);
    }

    public static void color(float i, float i1, float i2) {
        ReflectUtil.invokeStatic(color_FFF, i, i1, i2);
    }

    public static void alphaFunc(int func, float ref) {
        ReflectUtil.invokeStatic(alphaFunc, func, ref);
    }

    public static void enableLighting() {
        ReflectUtil.invokeStatic(enableLighting);
    }
}
