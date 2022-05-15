package rbq.wtf.lycoris.client.gui.NewClickGUI;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.lang.reflect.Field;
import java.util.List;

public class BlurUtil {
    private static ShaderGroup blurShader;
    private static Minecraft mc = Minecraft.getMinecraft();
    private static Framebuffer buffer;
    private static float lastScale;
    private static int lastScaleWidth;
    private static int lastScaleHeight;
    private static ResourceLocation shader = new ResourceLocation("shaders/post/blur.json");

    public static void initFboAndShader() {
        try {
            blurShader = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), shader);
            blurShader.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
            buffer = new Framebuffer(mc.displayWidth, mc.displayHeight, true);
            buffer.setFramebufferColor(1,1,1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setShaderConfigs(float intensity, float blurWidth, float blurHeight) {

        try {
//            Field field = ObfuscationReflectionHelper.findField(ShaderGroup.class,"field_148031_d");
//            Field field = ReflectionHelper.findField(ShaderGroup.class, new String[]{"field_148031_d", "listShaders"});
            Field field = ReflectionHelper.findField(ShaderGroup.class, new String[]{"listShaders", "field_148031_d"});
            field.setAccessible(true);
            ((List<Shader>) field.get(blurShader)).get(0).getShaderManager().getShaderUniform("Radius").set(intensity);
            ((List<Shader>) field.get(blurShader)).get(1).getShaderManager().getShaderUniform("Radius").set(intensity);
            ((List<Shader>) field.get(blurShader)).get(0).getShaderManager().getShaderUniform("BlurDir").set(blurWidth, blurHeight);
            ((List<Shader>) field.get(blurShader)).get(1).getShaderManager().getShaderUniform("BlurDir").set(blurHeight, blurWidth);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void blurAll(float intensity) {
        Field field = ReflectionHelper.findField(Minecraft.class, new String[]{"timer", "field_71428_T"});
        field.setAccessible(true);
        Field field2 = ReflectionHelper.findField(Timer.class, new String[]{"renderPartialTicks", "field_194147_b"});
        field2.setAccessible(true);

        ScaledResolution scale = new ScaledResolution(mc);
        float factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;

        setShaderConfigs(intensity, 0, 1);
        buffer.bindFramebuffer(true);
        try {
            blurShader.render(field2.getFloat(field.get(Minecraft.getMinecraft())));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mc.getFramebuffer().bindFramebuffer(true);
    }

}