package rbq.wtf.lycoris.client.utils;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.Gui;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.GlStateManager;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.Tessellator;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.WorldRenderer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    private static Minecraft mc = Minecraft.getMinecraft();
    private static final List<Integer> csBuffer;
    private static final Consumer<Integer> ENABLE_CLIENT_STATE;
    private static int lastScale;
    private static int lastScaleWidth;
    private static int lastScaleHeight;

    private static final Consumer<Integer> DISABLE_CLIENT_STATE;

    public static float delta;

    static {
        csBuffer = new ArrayList<Integer>();
        ENABLE_CLIENT_STATE = GL11::glEnableClientState;
        DISABLE_CLIENT_STATE = GL11::glEnableClientState;
    }

    public static int width() {
        return new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth();
    }

    public static int height() {
        return new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
    }

    public static int getHexRGB(final int hex) {
        return 0xFF000000 | hex;
    }

    public static int reAlpha(int color, float alpha) {
        Color c = new Color(color);
        float r = ((float) 1 / 255) * c.getRed();
        float g = ((float) 1 / 255) * c.getGreen();
        float b = ((float) 1 / 255) * c.getBlue();
        return new Color(r, g, b, alpha).getRGB();
    }

    public static void drawRoundRect(double xPosition, double yPosition, double endX, double endY, int radius,
                                     int color) {
        double width = endX - xPosition;
        double height = endY - yPosition;
        RenderUtils.drawRect(xPosition + radius, yPosition + radius, xPosition + width - radius, yPosition + height - radius,
                color);
        RenderUtils.drawRect(xPosition, yPosition + radius, xPosition + radius, yPosition + height - radius, color);
        RenderUtils.drawRect(xPosition + width - radius, yPosition + radius, xPosition + width, yPosition + height - radius,
                color);
        RenderUtils.drawRect(xPosition + radius, yPosition, xPosition + width - radius, yPosition + radius, color);
        RenderUtils.drawRect(xPosition + radius, yPosition + height - radius, xPosition + width - radius, yPosition + height,
                color);
        RenderUtils.drawFilledCircle(xPosition + radius, yPosition + radius, radius, color, 1);
        RenderUtils.drawFilledCircle(xPosition + radius, yPosition + height - radius, radius, color, 2);
        RenderUtils.drawFilledCircle(xPosition + width - radius, yPosition + radius, radius, color, 3);
        RenderUtils.drawFilledCircle(xPosition + width - radius, yPosition + height - radius, radius, color, 4);
    }

    public static void drawFilledCircle(double x, double y, double r, int c, int id) {
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;
        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        glColor4f(f1, f2, f3, f);
        glBegin(GL_POLYGON);
        if (id == 1) {
            glVertex2d(x, y);
            for (int i = 0; i <= 90; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else if (id == 2) {
            glVertex2d(x, y);
            for (int i = 90; i <= 180; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else if (id == 3) {
            glVertex2d(x, y);
            for (int i = 270; i <= 360; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else if (id == 4) {
            glVertex2d(x, y);
            for (int i = 180; i <= 270; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else {
            for (int i = 0; i <= 360; i++) {
                double x2 = Math.sin((i * 3.141526D / 180)) * r;
                double y2 = Math.cos((i * 3.141526D / 180)) * r;
                glVertex2f((float) (x - x2), (float) (y - y2));
            }
        }
        glEnd();
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }


    public static void color(int color, float alpha) {
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }


    public static void drawRoundedRect(float x, float y, float x2, float y2, final float round, final int color) {
        x += (float) (round / 2.0f + 0.5);
        y += (float) (round / 2.0f + 0.5);
        x2 -= (float) (round / 2.0f + 0.5);
        y2 -= (float) (round / 2.0f + 0.5);
        Gui.drawRect((int) x, (int) y, (int) x2, (int) y2, color);
        circle(x2 - round / 2.0f, y + round / 2.0f, round, color);
        circle(x + round / 2.0f, y2 - round / 2.0f, round, color);
        circle(x + round / 2.0f, y + round / 2.0f, round, color);
        circle(x2 - round / 2.0f, y2 - round / 2.0f, round, color);
        Gui.drawRect((int) (x - round / 2.0f - 0.5f), (int) (y + round / 2.0f), (int) x2, (int) (y2 - round / 2.0f),
                color);
        Gui.drawRect((int) x, (int) (y + round / 2.0f), (int) (x2 + round / 2.0f + 0.5f), (int) (y2 - round / 2.0f),
                color);
        Gui.drawRect((int) (x + round / 2.0f), (int) (y - round / 2.0f - 0.5f), (int) (x2 - round / 2.0f),
                (int) (y2 - round / 2.0f), color);
        Gui.drawRect((int) (x + round / 2.0f), (int) y, (int) (x2 - round / 2.0f), (int) (y2 + round / 2.0f + 0.5f),
                color);
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void circle(final float x, final float y, final float radius, final int fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void circle(final float x, final float y, final float radius, final Color fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void arc(final float x, final float y, final float start, final float end, final float radius,
                           final int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arc(final float x, final float y, final float start, final float end, final float radius,
                           final Color color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arcEllipse(final float x, final float y, float start, float end, final float w, final float h,
                                  final int color) {
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        float temp = 0.0f;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        final Tessellator var15 = Tessellator.getInstance();
        final WorldRenderer var16 = var15.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        if (var11 > 0.5f) {
            glEnable(2848);
            GL11.glLineWidth(2.0f);
            glBegin(3);
            for (float i = end; i >= start; i -= 4.0f) {
                final float ldx = (float) Math.cos(i * 3.141592653589793 / 180.0) * w * 1.001f;
                final float ldy = (float) Math.sin(i * 3.141592653589793 / 180.0) * h * 1.001f;
                GL11.glVertex2f(x + ldx, y + ldy);
            }
            GL11.glEnd();
            glDisable(2848);
        }
        glBegin(6);
        for (float i = end; i >= start; i -= 4.0f) {
            final float ldx = (float) Math.cos(i * 3.141592653589793 / 180.0) * w;
            final float ldy = (float) Math.sin(i * 3.141592653589793 / 180.0) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void arcEllipse(final float x, final float y, float start, float end, final float w, final float h,
                                  final Color color) {
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        float temp = 0.0f;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }
        final Tessellator var9 = Tessellator.getInstance();
        final WorldRenderer var10 = var9.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f,
                color.getAlpha() / 255.0f);
        if (color.getAlpha() > 0.5f) {
            glEnable(2848);
            GL11.glLineWidth(2.0f);
            glBegin(3);
            for (float i = end; i >= start; i -= 4.0f) {
                final float ldx = (float) Math.cos(i * 3.141592653589793 / 180.0) * w * 1.001f;
                final float ldy = (float) Math.sin(i * 3.141592653589793 / 180.0) * h * 1.001f;
                GL11.glVertex2f(x + ldx, y + ldy);
            }
            GL11.glEnd();
            glDisable(2848);
        }
        glBegin(6);
        for (float i = end; i >= start; i -= 4.0f) {
            final float ldx = (float) Math.cos(i * 3.141592653589793 / 180.0) * w;
            final float ldy = (float) Math.sin(i * 3.141592653589793 / 180.0) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawBorderedRect(final double x, final double y, final double x2, final double d, final float l1,
                                        final int col1, final int col2) {
        RenderUtils.drawRect(x, y, x2, d, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        glEnable(3042);
        glDisable(3553);
        GL11.glBlendFunc(770, 771);
        glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, d);
        GL11.glVertex2d(x2, d);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, d);
        GL11.glVertex2d(x2, d);
        GL11.glEnd();
        GL11.glPopMatrix();
        glEnable(3553);
        glDisable(3042);
        glDisable(2848);
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void pre() {
        glDisable(2929);
        glDisable(3553);
        glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }

    public static void post() {
        glDisable(3042);
        glEnable(3553);
        glEnable(2929);
        GL11.glColor3d(1.0, 1.0, 1.0);
    }

    public static Color blend(final Color color1, final Color color2, final double ratio) {
        final float r = (float) ratio;
        final float ir = 1.0f - r;
        final float[] rgb1 = new float[3];
        final float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        final Color color3 = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir,
                rgb1[2] * r + rgb2[2] * ir);
        return color3;
    }


    public static void setupRender(final boolean start) {
        if (start) {
            GlStateManager.enableBlend();
            glEnable(2848);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GlStateManager.blendFunc(770, 771);
            GL11.glHint(3154, 4354);
        } else {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            glDisable(2848);
            GlStateManager.enableDepth();
        }
        GlStateManager.depthMask(!start);
    }

    public static double getAnimationState(double animation, final double finalState, final double speed) {
        final float add = (float) (0.01 * speed);
        animation = ((animation < finalState) ? ((animation + add < finalState) ? (animation += add) : finalState)
                : ((animation - add > finalState) ? (animation -= add) : finalState));
        return animation;
    }

    public static void drawRect(float x1, float y1, float x2, float y2, int color) {
        GL11.glPushMatrix();
        glEnable(3042);
        glDisable(3553);
        GL11.glBlendFunc(770, 771);
        glEnable(2848);
        GL11.glPushMatrix();
        color(color);
        glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        glEnable(3553);
        glDisable(3042);
        glDisable(2848);
        GL11.glPopMatrix();
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void color(int color) {
        float f = (color >> 24 & 0xFF) / 255.0F;
        float f1 = (color >> 16 & 0xFF) / 255.0F;
        float f2 = (color >> 8 & 0xFF) / 255.0F;
        float f3 = (color & 0xFF) / 255.0F;
        GL11.glColor4f(f1, f2, f3, f);
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float) (col1 >> 24 & 255) / 255.0f;
        float f1 = (float) (col1 >> 16 & 255) / 255.0f;
        float f2 = (float) (col1 >> 8 & 255) / 255.0f;
        float f3 = (float) (col1 & 255) / 255.0f;
        float f4 = (float) (col2 >> 24 & 255) / 255.0f;
        float f5 = (float) (col2 >> 16 & 255) / 255.0f;
        float f6 = (float) (col2 >> 8 & 255) / 255.0f;
        float f7 = (float) (col2 & 255) / 255.0f;
        glEnable((int) 3042);
        glDisable((int) 3553);
        GL11.glBlendFunc((int) 770, (int) 771);
        glEnable((int) 2848);
        GL11.glShadeModel((int) 7425);
        GL11.glPushMatrix();
        glBegin((int) 7);
        GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
        GL11.glVertex2d((double) left, (double) top);
        GL11.glVertex2d((double) left, (double) bottom);
        GL11.glColor4f((float) f5, (float) f6, (float) f7, (float) f4);
        GL11.glVertex2d((double) right, (double) bottom);
        GL11.glVertex2d((double) right, (double) top);
        GL11.glEnd();
        GL11.glPopMatrix();
        glEnable((int) 3553);
        glDisable((int) 3042);
        glDisable((int) 2848);
        GL11.glShadeModel((int) 7424);
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void drawGradientSidewaysS(double left, double top, double right, double bottom, int col1, int col2,
                                             int mode) {
        float f = (float) (col1 >> 24 & 255) / 255.0f;
        float f1 = (float) (col1 >> 16 & 255) / 255.0f;
        float f2 = (float) (col1 >> 8 & 255) / 255.0f;
        float f3 = (float) (col1 & 255) / 255.0f;
        float f4 = (float) (col2 >> 24 & 255) / 255.0f;
        float f5 = (float) (col2 >> 16 & 255) / 255.0f;
        float f6 = (float) (col2 >> 8 & 255) / 255.0f;
        float f7 = (float) (col2 & 255) / 255.0f;
        glEnable((int) 3042);
        glDisable((int) 3553);
        GL11.glBlendFunc((int) 770, (int) 771);
        glEnable((int) 2848);
        GL11.glShadeModel((int) 7425);
        GL11.glPushMatrix();
        glBegin((int) 7);
        if (mode == 1) {
            GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
            GL11.glVertex2d((double) left, (double) top);
            GL11.glVertex2d((double) right, (double) top);
            GL11.glColor4f((float) f5, (float) f6, (float) f7, (float) f4);
            GL11.glVertex2d((double) left, (double) bottom);
            GL11.glVertex2d((double) right, (double) top);
        }
        if (mode == 2) {
            GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
            GL11.glVertex2d((double) left, (double) top);
            GL11.glVertex2d((double) right, (double) bottom);
            GL11.glColor4f((float) f5, (float) f6, (float) f7, (float) f4);
            GL11.glVertex2d((double) right, (double) top);
            GL11.glVertex2d((double) left, (double) top);
        }
        if (mode == 3) {
            GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
            GL11.glVertex2d((double) left, (double) bottom);
            GL11.glVertex2d((double) right, (double) bottom);
            GL11.glColor4f((float) f5, (float) f6, (float) f7, (float) f4);
            GL11.glVertex2d((double) right, (double) bottom);
            GL11.glVertex2d((double) left, (double) top);
        }
        if (mode == 4) {
            GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
            GL11.glVertex2d((double) left, (double) bottom);
            GL11.glVertex2d((double) right, (double) bottom);
            GL11.glColor4f((float) f5, (float) f6, (float) f7, (float) f4);
            GL11.glVertex2d((double) right, (double) top);
            GL11.glVertex2d((double) left, (double) bottom);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        glEnable((int) 3553);
        glDisable((int) 3042);
        glDisable((int) 2848);
        GL11.glShadeModel((int) 7424);
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void drawGradientSidewaysV(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float) (col1 >> 24 & 255) / 255.0f;
        float f1 = (float) (col1 >> 16 & 255) / 255.0f;
        float f2 = (float) (col1 >> 8 & 255) / 255.0f;
        float f3 = (float) (col1 & 255) / 255.0f;
        float f4 = (float) (col2 >> 24 & 255) / 255.0f;
        float f5 = (float) (col2 >> 16 & 255) / 255.0f;
        float f6 = (float) (col2 >> 8 & 255) / 255.0f;
        float f7 = (float) (col2 & 255) / 255.0f;
        glEnable((int) 3042);
        glDisable((int) 3553);
        GL11.glBlendFunc((int) 770, (int) 771);
        glEnable((int) 2848);
        GL11.glShadeModel((int) 7425);
        GL11.glPushMatrix();
        glBegin((int) 7);
        GL11.glColor4f((float) f1, (float) f2, (float) f3, (float) f);
        GL11.glVertex2d((double) left, (double) bottom);
        GL11.glVertex2d((double) right, (double) bottom);
        GL11.glColor4f((float) f5, (float) f6, (float) f7, (float) f4);
        GL11.glVertex2d((double) right, (double) top);
        GL11.glVertex2d((double) left, (double) top);
        GL11.glEnd();
        GL11.glPopMatrix();
        glEnable((int) 3553);
        glDisable((int) 3042);
        glDisable((int) 2848);
        GL11.glShadeModel((int) 7424);
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void drawRect(double d, double e, double f, double g, int color) {
        GL11.glPushMatrix();
        glEnable((int) 3042);
        glDisable((int) 3553);
        GL11.glBlendFunc((int) 770, (int) 771);
        glEnable((int) 2848);
        GL11.glPushMatrix();
        RenderUtils.color(color);
        glBegin((int) 7);
        GL11.glVertex2d((double) f, (double) e);
        GL11.glVertex2d((double) d, (double) e);
        GL11.glVertex2d((double) d, (double) g);
        GL11.glVertex2d((double) f, (double) g);
        GL11.glEnd();
        GL11.glPopMatrix();
        glEnable((int) 3553);
        glDisable((int) 3042);
        glDisable((int) 2848);
        GL11.glPopMatrix();
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor,
                                         int borderColor) {
        RenderUtils.rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.color((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
        RenderUtils.rectangle(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.color((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
        RenderUtils.rectangle(x, y, x + width, y1, borderColor);
        GlStateManager.color((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
        RenderUtils.rectangle(x1 - width, y, x1, y1, borderColor);
        GlStateManager.color((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
        RenderUtils.rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.color((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
    }

    public static void rectangle(double left, double top, double right, double bottom, int color) {
        double var5;
        if (left < right) {
            var5 = left;
            left = right;
            right = var5;
        }
        if (top < bottom) {
            var5 = top;
            top = bottom;
            bottom = var5;
        }
        float var11 = (float) (color >> 24 & 255) / 255.0f;
        float var6 = (float) (color >> 16 & 255) / 255.0f;
        float var7 = (float) (color >> 8 & 255) / 255.0f;
        float var8 = (float) (color & 255) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int) 770, (int) 771, (int) 1, (int) 0);
        GlStateManager.color((float) var6, (float) var7, (float) var8, (float) var11);
//        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
//        worldRenderer.pos(left, bottom, 0.0).endVertex();
//        worldRenderer.pos(right, bottom, 0.0).endVertex();
//        worldRenderer.pos(right, top, 0.0).endVertex();
//        worldRenderer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
    }

    public static void pre3D() {
        GL11.glPushMatrix();
        glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        glDisable(GL11.GL_TEXTURE_2D);
        glEnable(GL11.GL_LINE_SMOOTH);
        glDisable(GL11.GL_DEPTH_TEST);
        glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
    }

    public static void post3D() {
        GL11.glDepthMask(true);
        glEnable(GL11.GL_DEPTH_TEST);
        glDisable(GL11.GL_LINE_SMOOTH);
        glEnable(GL11.GL_TEXTURE_2D);
        glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glColor4f(1, 1, 1, 1);
    }

    public static void glColor(int hex) {
        float alpha = (hex >> 24 & 0xFF) / 255.0F;
        float red = (hex >> 16 & 0xFF) / 255.0F;
        float green = (hex >> 8 & 0xFF) / 255.0F;
        float blue = (hex & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawFilledCircle(float xx, float yy, float radius, Color col) {
        int sections = 50;
        double dAngle = 6.283185307179586 / (double) sections;
        GL11.glPushMatrix();
        glEnable((int) 3042);
        glDisable((int) 3553);
        GL11.glBlendFunc((int) 770, (int) 771);
        glEnable((int) 2848);
        glBegin((int) 6);
        int i = 0;
        while (i < sections) {
            float x = (float) ((double) radius * Math.sin((double) ((double) i * dAngle)));
            float y = (float) ((double) radius * Math.cos((double) ((double) i * dAngle)));
            GL11.glColor4f((float) ((float) col.getRed() / 255.0f), (float) ((float) col.getGreen() / 255.0f),
                    (float) ((float) col.getBlue() / 255.0f), (float) ((float) col.getAlpha() / 255.0f));
            GL11.glVertex2f((float) (xx + x), (float) (yy + y));
            ++i;
        }
        GlStateManager.color((float) 0.0f, (float) 0.0f, (float) 0.0f);
        GL11.glEnd();
        glEnable((int) 3553);
        glDisable((int) 3042);
        glDisable((int) 2848);
        GL11.glPopMatrix();
    }

    public static double[] convertTo2D(final double x, final double y, final double z) {
        final FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        final IntBuffer viewport = BufferUtils.createIntBuffer(16);
        final FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
        final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(2982, modelView);
        GL11.glGetFloat(2983, projection);
        GL11.glGetInteger(2978, viewport);
        final boolean result = GLU.gluProject((float) x, (float) y, (float) z, modelView, projection, viewport,
                screenCoords);
        return (double[]) (result
                ? new double[]{screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2)}
                : null);
    }

    public static void doGlScissor(int x, int y, int width, int height) {
        Minecraft mc = Minecraft.getMinecraft();
        int scaleFactor = 1;
        int k = mc.getGameSettings().getGuiScale();
        if (k == 0) {
            k = 1000;
        }
        while (scaleFactor < k && mc.getDisplayHeight() / (scaleFactor + 1) >= 320
                && mc.getDisplayWidth() / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        GL11.glScissor((int) (x * scaleFactor), (int) (mc.getDisplayHeight() - (y + height) * scaleFactor),
                (int) (width * scaleFactor), (int) (height * scaleFactor));

    }

    public static void drawCircle(double x, double y, double radius, int c) {
        float alpha = (float) (c >> 24 & 255) / 255.0f;
        float red = (float) (c >> 16 & 255) / 255.0f;
        float green = (float) (c >> 8 & 255) / 255.0f;
        float blue = (float) (c & 255) / 255.0f;
        boolean blend = GL11.glIsEnabled((int) 3042);
        boolean line = GL11.glIsEnabled((int) 2848);
        boolean texture = GL11.glIsEnabled((int) 3553);
        if (!blend) {
            glEnable((int) 3042);
        }
        if (!line) {
            glEnable((int) 2848);
        }
        if (texture) {
            glDisable((int) 3553);
        }
        GL11.glBlendFunc((int) 770, (int) 771);
        GL11.glColor4f((float) red, (float) green, (float) blue, (float) alpha);
        glBegin((int) 9);
        int i = 0;
        while (i <= 360) {
            GL11.glVertex2d(
                    (double) ((double) x + Math.sin((double) ((double) i * 3.141526 / 180.0)) * (double) radius),
                    (double) ((double) y + Math.cos((double) ((double) i * 3.141526 / 180.0)) * (double) radius));
            ++i;
        }
        GL11.glEnd();
        if (texture) {
            glEnable((int) 3553);
        }
        if (!line) {
            glDisable((int) 2848);
        }
        if (!blend) {
            glDisable((int) 3042);
        }
    }

    public static void drawFullCircle(int cx, int cy, double r, int segments, float lineWidth, int part, int c) {
        GL11.glScalef((float) 0.5f, (float) 0.5f, (float) 0.5f);
        r *= 2.0;
        cx *= 2;
        cy *= 2;
        float f2 = (float) (c >> 24 & 255) / 255.0f;
        float f22 = (float) (c >> 16 & 255) / 255.0f;
        float f3 = (float) (c >> 8 & 255) / 255.0f;
        float f4 = (float) (c & 255) / 255.0f;
        glEnable((int) 3042);
        GL11.glLineWidth((float) lineWidth);
        glDisable((int) 3553);
        glEnable((int) 2848);
        GL11.glBlendFunc((int) 770, (int) 771);
        GL11.glColor4f((float) f22, (float) f3, (float) f4, (float) f2);
        glBegin((int) 3);
        int i = segments - part;
        while (i <= segments) {
            double x = Math.sin((double) i * 3.141592653589793 / 180.0) * r;
            double y = Math.cos((double) i * 3.141592653589793 / 180.0) * r;
            GL11.glVertex2d((double) ((double) cx + x), (double) ((double) cy + y));
            ++i;
        }
        GL11.glEnd();
        glDisable((int) 2848);
        glEnable((int) 3553);
        glDisable((int) 3042);
        GL11.glScalef((float) 2.0f, (float) 2.0f, (float) 2.0f);
    }


    public static void drawHollowBox(float x, float y, float x1, float y1, float thickness, int color) {
        RenderUtils.drawHorizontalLine(x, y, x1, thickness, color);
        RenderUtils.drawHorizontalLine(x, y1, x1, thickness, color);
        RenderUtils.drawVerticalLine(x, y, y1, thickness, color);
        RenderUtils.drawVerticalLine(x1 - thickness, y, y1, thickness, color);
    }

    public static void drawHorizontalLine(float x, float y, float x1, float thickness, int color) {
        RenderUtils.drawRect2(x, y, x1, y + thickness, color);
    }

    public static void drawRect2(double x, double y, double x2, double y2, int color) {
        RenderUtils.drawRect(x, y, x2, y2, color);
    }

    public static void drawVerticalLine(float x, float y, float y1, float thickness, int color) {
        RenderUtils.drawRect2(x, y, x + thickness, y1, color);
    }

    public static int rainbow(int delay) {
        double rainbow = Math.ceil((double) (System.currentTimeMillis() + (long) delay) / 10.0);
        return Color.getHSBColor((float) ((rainbow %= 360.0) / 360.0), 0.5f, 1.0f).getRGB();
    }

    public static void drawCircle2(final float x, final float y, final float radius, final int start, final int end) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        glColor(new Color(255, 255, 255).getRGB());
        glEnable(2848);
        GL11.glLineWidth(2.0f);
        glBegin(3);
        for (float i = (float) end; i >= start; i -= 4.0f) {
            GL11.glVertex2f((float) (x + Math.cos(i * 3.141592653589793 / 180.0) * (radius * 1.001f)),
                    (float) (y + Math.sin(i * 3.141592653589793 / 180.0) * (radius * 1.001f)));
        }
        GL11.glEnd();
        glDisable(2848);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static Color rainbow(long time, float count, float fade) {
        float hue = ((float) time + (1.0F + count) * 2.0E8F) / 1.0E10F % 1.0F;
        long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0F, 1.0F)).intValue()),
                16);
        Color c = new Color((int) color);
        return new Color((float) c.getRed() / 255.0F * fade, (float) c.getGreen() / 255.0F * fade,
                (float) c.getBlue() / 255.0F * fade, (float) c.getAlpha() / 255.0F);
    }

    public static class R2DUtils {
        public static void enableGL2D() {
            glDisable((int) 2929);
            glEnable((int) 3042);
            glDisable((int) 3553);
            GL11.glBlendFunc((int) 770, (int) 771);
            GL11.glDepthMask((boolean) true);
            glEnable((int) 2848);
            GL11.glHint((int) 3154, (int) 4354);
            GL11.glHint((int) 3155, (int) 4354);
        }

        public static void disableGL2D() {
            glEnable((int) 3553);
            glDisable((int) 3042);
            glEnable((int) 2929);
            glDisable((int) 2848);
            GL11.glHint((int) 3154, (int) 4352);
            GL11.glHint((int) 3155, (int) 4352);
        }

        public static void drawRoundedRect(float x, float y, float x1, float y1, int borderC, int insideC) {
            R2DUtils.enableGL2D();
            GL11.glScalef((float) 0.5f, (float) 0.5f, (float) 0.5f);
            R2DUtils.drawVLine(x *= 2.0f, (y *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
            R2DUtils.drawVLine((x1 *= 2.0f) - 1.0f, y + 1.0f, y1 - 2.0f, borderC);
            R2DUtils.drawHLine(x + 2.0f, x1 - 3.0f, y, borderC);
            R2DUtils.drawHLine(x + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
            R2DUtils.drawHLine(x + 1.0f, x + 1.0f, y + 1.0f, borderC);
            R2DUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y + 1.0f, borderC);
            R2DUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
            R2DUtils.drawHLine(x + 1.0f, x + 1.0f, y1 - 2.0f, borderC);
            R2DUtils.drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
            GL11.glScalef((float) 2.0f, (float) 2.0f, (float) 2.0f);
            R2DUtils.disableGL2D();
            Gui.drawRect(0, 0, 0, 0, 0);
        }

        public static void drawRect(double x2, double y2, double x1, double y1, int color) {
            R2DUtils.enableGL2D();
            R2DUtils.glColor(color, color, color, color);
//							R2DUtils.drawRect(x2, y2, x1, y1, color);
            R2DUtils.drawRect(x2, y2, x1, y1);
            R2DUtils.disableGL2D();
        }

        private static void drawRect(double x2, double y2, double x1, double y1) {
            glBegin((int) 7);
            GL11.glVertex2d((double) x2, (double) y1);
            GL11.glVertex2d((double) x1, (double) y1);
            GL11.glVertex2d((double) x1, (double) y2);
            GL11.glVertex2d((double) x2, (double) y2);
            GL11.glEnd();
        }

        public static void drawHLine(float x, float y, float x1, int y1) {
            if (y < x) {
                float var5 = x;
                x = y;
                y = var5;
            }
            R2DUtils.drawRect(x, x1, y + 1.0f, x1 + 1.0f, y1);
        }

        public static void drawVLine(float x, float y, float x1, int y1) {
            if (x1 < y) {
                float var5 = y;
                y = x1;
                x1 = var5;
            }
            R2DUtils.drawRect(x, y + 1.0f, x + 1.0f, x1, y1);
        }

        public static void drawHLine(float x, float y, float x1, int y1, int y2) {
            if (y < x) {
                float var5 = x;
                x = y;
                y = var5;
            }
            R2DUtils.drawGradientRect(x, x1, y + 1.0f, x1 + 1.0f, y1, y2);
        }

        public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
            R2DUtils.enableGL2D();
            GL11.glShadeModel((int) 7425);
            glBegin((int) 7);
            RenderUtils.glColor(topColor);
            GL11.glVertex2f((float) x, (float) y1);
            GL11.glVertex2f((float) x1, (float) y1);
            RenderUtils.glColor(bottomColor);
            GL11.glVertex2f((float) x1, (float) y);
            GL11.glVertex2f((float) x, (float) y);
            GL11.glEnd();
            GL11.glShadeModel((int) 7424);
            R2DUtils.disableGL2D();
        }

        public static void glColor(float alpha, int redRGB, int greenRGB, int blueRGB) {
            float red = 0.003921569F * redRGB;
            float green = 0.003921569F * greenRGB;
            float blue = 0.003921569F * blueRGB;
            GL11.glColor4f(red, green, blue, alpha);
        }
    }

    public static void drawblock(double a, double a2, double a3, int a4, int a5, float a6) {
        float a7 = (float) (a4 >> 24 & 255) / 255.0f;
        float a8 = (float) (a4 >> 16 & 255) / 255.0f;
        float a9 = (float) (a4 >> 8 & 255) / 255.0f;
        float a10 = (float) (a4 & 255) / 255.0f;
        float a11 = (float) (a5 >> 24 & 255) / 255.0f;
        float a12 = (float) (a5 >> 16 & 255) / 255.0f;
        float a13 = (float) (a5 >> 8 & 255) / 255.0f;
        float a14 = (float) (a5 & 255) / 255.0f;
        org.lwjgl.opengl.GL11.glPushMatrix();
//		org.lwjgl.opengl.GL11.glEnable((int) 3042);
        org.lwjgl.opengl.GL11.glBlendFunc((int) 770, (int) 771);
        glDisable((int) 3553);
        glEnable((int) 2848);
        glDisable((int) 2929);
        org.lwjgl.opengl.GL11.glDepthMask((boolean) false);
        org.lwjgl.opengl.GL11.glColor4f((float) a8, (float) a9, (float) a10, (float) a7);
//        drawOutlinedBoundingBox(new AxisAlignedBB(a, a2, a3, a + 1.0, a2 + 1.0, a3 + 1.0));
        org.lwjgl.opengl.GL11.glLineWidth((float) a6);
        org.lwjgl.opengl.GL11.glColor4f((float) a12, (float) a13, (float) a14, (float) a11);
//        drawOutlinedBoundingBox(new AxisAlignedBB(a, a2, a3, a + 1.0, a2 + 1.0, a3 + 1.0));
        glDisable((int) 2848);
        glEnable((int) 3553);
        glEnable((int) 2929);
        org.lwjgl.opengl.GL11.glDepthMask((boolean) true);
//		org.lwjgl.opengl.GL11.glDisable((int) 3042);
        org.lwjgl.opengl.GL11.glPopMatrix();
    }

    public static void drawArc(float n, float n2, double n3, final int n4, final int n5, final double n6,
                               final int n7) {
        n3 *= 2.0;
        n *= 2.0f;
        n2 *= 2.0f;
        final float n8 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n4 & 0xFF) / 255.0f;
        glDisable(2929);
        glEnable(3042);
        glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glLineWidth((float) n7);
        glEnable(2848);
        GL11.glColor4f(n9, n10, n11, n8);
        glBegin(3);
        int n12 = n5;
        while (n12 <= n6) {
            GL11.glVertex2d(n + Math.sin(n12 * 3.141592653589793 / 180.0) * n3,
                    n2 + Math.cos(n12 * 3.141592653589793 / 180.0) * n3);
            ++n12;
        }
        GL11.glEnd();
        glDisable(2848);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        glEnable(3553);
        glDisable(3042);
        glEnable(2929);
        glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);

    }

    public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue,
                                         float alpha) {
        GL11.glPushMatrix();
        glEnable((int) 3042);
        GL11.glBlendFunc((int) 770, (int) 771);
        glDisable((int) 3553);
        glEnable((int) 2848);
        glDisable((int) 2929);
        GL11.glDepthMask((boolean) false);
        GL11.glColor4f((float) red, (float) green, (float) blue, (float) alpha);
//        RenderUtil.drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glColor3f(1, 1, 1);
        glDisable((int) 2848);
        glEnable((int) 3553);
        glEnable((int) 2929);
        GL11.glDepthMask((boolean) true);
        glDisable((int) 3042);
        GL11.glPopMatrix();
    }


    public static void enableSmoothLine(float width) {
        glDisable((int) 3008);
        glEnable((int) 3042);
        GL11.glBlendFunc((int) 770, (int) 771);
        glDisable((int) 3553);
        glDisable((int) 2929);
        GL11.glDepthMask((boolean) false);
        glEnable((int) 2884);
        glEnable((int) 2848);
        GL11.glHint((int) 3154, (int) 4354);
        GL11.glHint((int) 3155, (int) 4354);
        GL11.glLineWidth((float) width);
    }

    public static void disableSmoothLine() {
        glEnable((int) 3553);
        glEnable((int) 2929);
        glDisable((int) 3042);
        glEnable((int) 3008);
        GL11.glDepthMask((boolean) true);
        GL11.glCullFace((int) 1029);
        glDisable((int) 2848);
        GL11.glHint((int) 3154, (int) 4352);
        GL11.glHint((int) 3155, (int) 4352);
    }

    public static void drawOutline(double x, double y, double width, double height, double lineWidth, int color) {
        RenderUtils.drawRect(x, y, x + width, y + lineWidth, color);
        RenderUtils.drawRect(x, y, x + lineWidth, y + height, color);
        RenderUtils.drawRect(x, y + height - lineWidth, x + width, y + height, color);
        RenderUtils.drawRect(x + width - lineWidth, y, x + width, y + height, color);
    }

    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static String getShaderCode(InputStreamReader file) {
        String shaderSource = "";
        try {
            String line;
            BufferedReader reader = new BufferedReader((Reader) file);
            while ((line = reader.readLine()) != null) {
                shaderSource = String.valueOf((Object) shaderSource) + line + "\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit((int) -1);
        }
        return shaderSource.toString();
    }

    public static int createShader(String shaderCode, int shaderType) throws Exception {
        int shader;
        block4:
        {
            shader = 0;
            try {
                shader = ARBShaderObjects.glCreateShaderObjectARB((int) shaderType);
                if (shader != 0)
                    break block4;
                return 0;
            } catch (Exception exc) {
                ARBShaderObjects.glDeleteObjectARB((int) shader);
                throw exc;
            }
        }
        ARBShaderObjects.glShaderSourceARB((int) shader, (CharSequence) shaderCode);
        ARBShaderObjects.glCompileShaderARB((int) shader);
        if (ARBShaderObjects.glGetObjectParameteriARB((int) shader, (int) 35713) == 0) {
            throw new RuntimeException("Error creating shader:");
        }
        return shader;
    }

    public static void drawBoundingBox(double x, double y, double z, double width, double height, float red,
                                       float green, float blue, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableDepth();
        GlStateManager.color(red, green, blue, alpha);
//        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void startGlScissor(int x, int y, int width, int height) {
        int scaleFactor = new ScaledResolution(mc).getScaleFactor();
        GL11.glPushMatrix();
        glEnable((int) 3089);
        GL11.glScissor((int) (x * scaleFactor), (int) (RenderUtils.mc.getDisplayHeight() - (y + height) * scaleFactor),
                (int) (width * scaleFactor), (int) ((height += 14) * scaleFactor));
    }

    public static void stopGlScissor() {
        glDisable((int) 3089);
        GL11.glPopMatrix();
    }
}
