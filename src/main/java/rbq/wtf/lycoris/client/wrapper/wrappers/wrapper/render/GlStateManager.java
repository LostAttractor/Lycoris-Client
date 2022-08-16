package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;

import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;

@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager",targetMap = MapEnum.Srg1_8_9)
public class GlStateManager{
    @WrapClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = MapEnum.Srg1_8_9)
    public static Class GlStateManagerClass;
    @WrapMethod(mcpName = "alphaFunc", targetMap = MapEnum.Srg1_8_9)
    public static Method alphaFunc;
    @WrapMethod(mcpName = "bindTexture", targetMap = MapEnum.Srg1_8_9)
    public static Method bindTexture;
    @WrapMethod(mcpName = "blendFunc", targetMap = MapEnum.Srg1_8_9)
    public static Method blendFunc;
    @WrapMethod(mcpName = "callList", targetMap = MapEnum.Srg1_8_9)
    public static Method callList;
    @WrapMethod(mcpName = "clear", targetMap = MapEnum.Srg1_8_9)
    public static Method clear;
    @WrapMethod(mcpName = "clearColor", targetMap = MapEnum.Srg1_8_9)
    public static Method clearColor;
    @WrapMethod(mcpName = "clearDepth", targetMap = MapEnum.Srg1_8_9)
    public static Method clearDepth;
    @WrapMethod(mcpName = "color", targetMap = MapEnum.Srg1_8_9, signature = "(FFF)V")
    public static Method color_FFF;
    @WrapMethod(mcpName = "color", targetMap = MapEnum.Srg1_8_9, signature = "(FFFF)V")
    public static Method color_FFFF;
    @WrapMethod(mcpName = "colorLogicOp", targetMap = MapEnum.Srg1_8_9)
    public static Method colorLogicOp;
    @WrapMethod(mcpName = "colorMask", targetMap = MapEnum.Srg1_8_9)
    public static Method colorMask;
    @WrapMethod(mcpName = "colorMaterial", targetMap = MapEnum.Srg1_8_9)
    public static Method colorMaterial;
    @WrapMethod(mcpName = "cullFace", targetMap = MapEnum.Srg1_8_9)
    public static Method cullFace;
    @WrapMethod(mcpName = "deleteTexture", targetMap = MapEnum.Srg1_8_9)
    public static Method deleteTexture;
    @WrapMethod(mcpName = "depthFunc", targetMap = MapEnum.Srg1_8_9)
    public static Method depthFunc;
    @WrapMethod(mcpName = "depthMask", targetMap = MapEnum.Srg1_8_9)
    public static Method depthMask;
    @WrapMethod(mcpName = "disableAlpha", targetMap = MapEnum.Srg1_8_9)
    public static Method disableAlpha;
    @WrapMethod(mcpName = "disableBlend", targetMap = MapEnum.Srg1_8_9)
    public static Method disableBlend;
    @WrapMethod(mcpName = "disableColorLogic", targetMap = MapEnum.Srg1_8_9)
    public static Method disableColorLogic;
    @WrapMethod(mcpName = "disableColorMaterial", targetMap = MapEnum.Srg1_8_9)
    public static Method disableColorMaterial;
    @WrapMethod(mcpName = "disableCull", targetMap = MapEnum.Srg1_8_9)
    public static Method disableCull;
    @WrapMethod(mcpName = "disableDepth", targetMap = MapEnum.Srg1_8_9)
    public static Method disableDepth;
    @WrapMethod(mcpName = "disableFog", targetMap = MapEnum.Srg1_8_9)
    public static Method disableFog;
    @WrapMethod(mcpName = "disableLight", targetMap = MapEnum.Srg1_8_9)
    public static Method disableLight;
    @WrapMethod(mcpName = "disableLighting", targetMap = MapEnum.Srg1_8_9)
    public static Method disableLighting;
    @WrapMethod(mcpName = "disableNormalize", targetMap = MapEnum.Srg1_8_9)
    public static Method disableNormalize;
    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = MapEnum.Srg1_8_9)
    public static Method disablePolygonOffset;
    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = MapEnum.Srg1_8_9)
    public static Method disableRescaleNormal;
    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = MapEnum.Srg1_8_9)
    public static Method disableTexGenCoord;
    @WrapMethod(mcpName = "disableTexture2D", targetMap = MapEnum.Srg1_8_9)
    public static Method disableTexture2D;
    @WrapMethod(mcpName = "doPolygonOffset", targetMap = MapEnum.Srg1_8_9)
    public static Method doPolygonOffset;
    @WrapMethod(mcpName = "enableAlpha", targetMap = MapEnum.Srg1_8_9)
    public static Method enableAlpha;
    @WrapMethod(mcpName = "enableBlend", targetMap = MapEnum.Srg1_8_9)
    public static Method enableBlend;
    @WrapMethod(mcpName = "enableColorLogic", targetMap = MapEnum.Srg1_8_9)
    public static Method enableColorLogic;
    @WrapMethod(mcpName = "enableColorMaterial", targetMap = MapEnum.Srg1_8_9)
    public static Method enableColorMaterial;
    @WrapMethod(mcpName = "enableCull", targetMap = MapEnum.Srg1_8_9)
    public static Method enableCull;
    @WrapMethod(mcpName = "enableDepth", targetMap = MapEnum.Srg1_8_9)
    public static Method enableDepth;
    @WrapMethod(mcpName = "enableFog", targetMap = MapEnum.Srg1_8_9)
    public static Method enableFog;
    @WrapMethod(mcpName = "enableLight", targetMap = MapEnum.Srg1_8_9)
    public static Method enableLight;
    @WrapMethod(mcpName = "enableLighting", targetMap = MapEnum.Srg1_8_9)
    public static Method enableLighting;
    @WrapMethod(mcpName = "enableNormalize", targetMap = MapEnum.Srg1_8_9)
    public static Method enableNormalize;
    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = MapEnum.Srg1_8_9)
    public static Method enablePolygonOffset;
    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = MapEnum.Srg1_8_9)
    public static Method enableRescaleNormal;
    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = MapEnum.Srg1_8_9)
    public static Method enableTexGenCoord;
    @WrapMethod(mcpName = "enableTexture2D", targetMap = MapEnum.Srg1_8_9)
    public static Method enableTexture2D;
    @WrapMethod(mcpName = "generateTexture", targetMap = MapEnum.Srg1_8_9)
    public static Method generateTexture;
    @WrapMethod(mcpName = "getFloat", targetMap = MapEnum.Srg1_8_9)
    public static Method getFloat;
    @WrapMethod(mcpName = "loadIdentity", targetMap = MapEnum.Srg1_8_9)
    public static Method loadIdentity;
    @WrapMethod(mcpName = "matrixMode", targetMap = MapEnum.Srg1_8_9)
    public static Method matrixMode;
    @WrapMethod(mcpName = "multMatrix", targetMap = MapEnum.Srg1_8_9)
    public static Method multMatrix;
    @WrapMethod(mcpName = "ortho", targetMap = MapEnum.Srg1_8_9)
    public static Method ortho;
    @WrapMethod(mcpName = "popAttrib", targetMap = MapEnum.Srg1_8_9)
    public static Method popAttrib;
    @WrapMethod(mcpName = "popMatrix", targetMap = MapEnum.Srg1_8_9)
    public static Method popMatrix;
    @WrapMethod(mcpName = "pushAttrib", targetMap = MapEnum.Srg1_8_9)
    public static Method pushAttrib;
    @WrapMethod(mcpName = "pushMatrix", targetMap = MapEnum.Srg1_8_9)
    public static Method pushMatrix;
    @WrapMethod(mcpName = "resetColor", targetMap = MapEnum.Srg1_8_9)
    public static Method resetColor;
    @WrapMethod(mcpName = "rotate", targetMap = MapEnum.Srg1_8_9)
    public static Method rotate;
    @WrapMethod(mcpName = "scale", targetMap = MapEnum.Srg1_8_9, signature = "(DDD)V")
    public static Method scale_DDD;
    @WrapMethod(mcpName = "scale", targetMap = MapEnum.Srg1_8_9, signature = "(FFF)V")
    public static Method scale_FFF;
    @WrapMethod(mcpName = "setActiveTexture", targetMap = MapEnum.Srg1_8_9)
    public static Method setActiveTexture;
    @WrapMethod(mcpName = "setFog", targetMap = MapEnum.Srg1_8_9)
    public static Method setFog;
    @WrapMethod(mcpName = "setFogDensity", targetMap = MapEnum.Srg1_8_9)
    public static Method setFogDensity;
    @WrapMethod(mcpName = "setFogEnd", targetMap = MapEnum.Srg1_8_9)
    public static Method setFogEnd;
    @WrapMethod(mcpName = "setFogStart", targetMap = MapEnum.Srg1_8_9)
    public static Method setFogStart;
    @WrapMethod(mcpName = "shadeModel", targetMap = MapEnum.Srg1_8_9)
    public static Method shadeModel;
    @WrapMethod(mcpName = "texGen", targetMap = MapEnum.Srg1_8_9, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    public static Method texGen_NJ;
    @WrapMethod(mcpName = "texGen", targetMap = MapEnum.Srg1_8_9, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    public static Method texGen_NI;
    @WrapMethod(mcpName = "texGenCoord", targetMap = MapEnum.Srg1_8_9)
    public static Method texGenCoord;
    @WrapMethod(mcpName = "translate", targetMap = MapEnum.Srg1_8_9, signature = "(FFF)V")
    public static Method translate_FFF;
    @WrapMethod(mcpName = "translate", targetMap = MapEnum.Srg1_8_9, signature = "(DDD)V")
    public static Method translate_DDD;
    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = MapEnum.Srg1_8_9)
    public static Method tryBlendFuncSeparate;
    @WrapMethod(mcpName = "viewport", targetMap = MapEnum.Srg1_8_9)
    public static Method viewport;
    public static Method glTexEnvi;
    public static void glTexEnvi(int glTextureEnv, int glTextureEnvMode, int glModulate) {
        GL11.glTexEnvi(glTextureEnv, glTextureEnvMode, glModulate);
            
    }

    public static void enableColorMaterial() {
        ReflectUtil.invoke(enableColorMaterial, null);
    }

    public static void disableRescaleNormal() {
        ReflectUtil.invoke(disableRescaleNormal, null);
    }

    public static void color(float f1, float f2, float f3, float f4) {
        ReflectUtil.invoke(color_FFFF, null, f1, f2, f3, f4);
    }

    public static void disableCull() {
        ReflectUtil.invoke(disableCull, null);
    }

    public static void disableDepth() {
        ReflectUtil.invoke(disableDepth, null);
    }
    public static void depthMask(boolean flag) {
        ReflectUtil.invoke(depthMask, null,flag);
    }

    public static void disableLighting() {
        ReflectUtil.invoke(disableLighting, null);
    }

    public static void enableAlpha() {
        ReflectUtil.invoke(enableAlpha, null);
    }

    public static void disableAlpha() {
        ReflectUtil.invoke(disableAlpha, null);
    }

    public static void disableBlend() {
        ReflectUtil.invoke(disableBlend, null);
    }

    public static void enableBlend() {
        ReflectUtil.invoke(enableBlend, null);
    }

    public static void bindTexture(int texture) {
        ReflectUtil.invoke(bindTexture, null, texture);
    }

    public static void blendFunc(int srcFactor, int dstFactor) {
        ReflectUtil.invoke(blendFunc, null, srcFactor, dstFactor);
    }

    public static void enableDepth() {
        ReflectUtil.invoke(enableDepth, null);
    }

    public static void clear(int mask) {
        ReflectUtil.invoke(clear, null, mask);
    }

    public static void pushMatrix() {
        ReflectUtil.invoke(pushMatrix, null);
    }

    public static void popMatrix() {
        ReflectUtil.invoke(popMatrix, null);
    }

    public static void rotate(float angle, float x, float y, float z) {
        ReflectUtil.invoke(rotate, null, angle, x, y, z);
    }

    public static void setActiveTexture(int texture) {
        ReflectUtil.invoke(setActiveTexture, null, texture);
    }

    public static void scale(double x, double y, double z) {
        ReflectUtil.invoke(scale_DDD, null, x, y, z);
    }

    public static void translate(float x, float y, float z) {
        ReflectUtil.invoke(translate_FFF, null, x, y, z);
    }

    public static void translate(double x, double y, double z) {
        ReflectUtil.invoke(translate_DDD, null, x, y, z);
    }

    public static void multMatrix(FloatBuffer matrix) {
        ReflectUtil.invoke(multMatrix, null, matrix);
    }

    public static void enableTexture2D() {
        ReflectUtil.invoke(enableTexture2D, null);
    }

    public static void disableTexture2D() {
        ReflectUtil.invoke(disableTexture2D, null);
    }

    public static void resetColor() {
        ReflectUtil.invoke(resetColor, null);
    }

    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        ReflectUtil.invoke(tryBlendFuncSeparate, null, srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }

    public static void shadeModel(int mode) {
        ReflectUtil.invoke(shadeModel, null, mode);
    }
    public static void disableLight(int light) {
        ReflectUtil.invoke(disableLight, null, light);
    }
    public static void disableColorMaterial() {
        ReflectUtil.invoke(disableColorMaterial, null);
    }

    public static void color(float i, float i1, float i2) {
        ReflectUtil.invoke(color_FFF, null, i, i1, i2);
    }

    public static void alphaFunc(int func, float ref) {
        ReflectUtil.invoke(alphaFunc,null,func,ref);
    }

    public static void enableLighting() {
        ReflectUtil.invoke(enableLighting,null);
    }
}
