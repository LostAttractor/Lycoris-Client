package rbq.wtf.lycoris.client.clickgui.utils;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.utils.MinecraftInstance;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.Gui;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.GlStateManager;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.OpenGlHelper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.Tessellator;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.WorldRenderer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.AxisAlignedBB;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil extends MinecraftInstance {

    public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
        R2DUtils.drawGradientRect(x, y, x1, y1, topColor, bottomColor);
    }

    public static void enableSmoothLine(float width) {
        glDisable(3008);
        glEnable(3042);
        GL11.glBlendFunc(770, 771);
        glDisable(3553);
        glDisable(2929);
        GL11.glDepthMask(false);
        glEnable(2884);
        glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(width);
    }

    public static void disableSmoothLine() {
        glEnable(3553);
        glEnable(2929);
        glDisable(3042);
        glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }


    public static int width() {
        return (new ScaledResolution(mc)).getScaledWidth();
    }

    public static int height() {
        return (new ScaledResolution(mc)).getScaledHeight();
    }

    public static void drawFastRoundedRect(final float x0, final float y0, final float x1, final float y1, final float radius, final int color) {
        final int Semicircle = 18;
        final float f = 90.0f / Semicircle;
        final float f2 = (color >> 24 & 0xFF) / 255.0f;
        final float f3 = (color >> 16 & 0xFF) / 255.0f;
        final float f4 = (color >> 8 & 0xFF) / 255.0f;
        final float f5 = (color & 0xFF) / 255.0f;
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(f3, f4, f5, f2);
        GL11.glBegin(5);
        GL11.glVertex2f(x0 + radius, y0);
        GL11.glVertex2f(x0 + radius, y1);
        GL11.glVertex2f(x1 - radius, y0);
        GL11.glVertex2f(x1 - radius, y1);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(x0, y0 + radius);
        GL11.glVertex2f(x0 + radius, y0 + radius);
        GL11.glVertex2f(x0, y1 - radius);
        GL11.glVertex2f(x0 + radius, y1 - radius);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(x1, y0 + radius);
        GL11.glVertex2f(x1 - radius, y0 + radius);
        GL11.glVertex2f(x1, y1 - radius);
        GL11.glVertex2f(x1 - radius, y1 - radius);
        GL11.glEnd();
        GL11.glBegin(6);
        float f6 = x1 - radius;
        float f7 = y0 + radius;
        GL11.glVertex2f(f6, f7);
        for (int j = 0; j <= Semicircle; ++j) {
            final float f8 = j * f;
            GL11.glVertex2f((float) (f6 + radius * Math.cos(Math.toRadians(f8))), (float) (f7 - radius * Math.sin(Math.toRadians(f8))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f6 = x0 + radius;
        f7 = y0 + radius;
        GL11.glVertex2f(f6, f7);
        for (int j = 0; j <= Semicircle; ++j) {
            final float f9 = j * f;
            GL11.glVertex2f((float) (f6 - radius * Math.cos(Math.toRadians(f9))), (float) (f7 - radius * Math.sin(Math.toRadians(f9))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f6 = x0 + radius;
        f7 = y1 - radius;
        GL11.glVertex2f(f6, f7);
        for (int j = 0; j <= Semicircle; ++j) {
            final float f10 = j * f;
            GL11.glVertex2f((float) (f6 - radius * Math.cos(Math.toRadians(f10))), (float) (f7 + radius * Math.sin(Math.toRadians(f10))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f6 = x1 - radius;
        f7 = y1 - radius;
        GL11.glVertex2f(f6, f7);
        for (int j = 0; j <= Semicircle; ++j) {
            final float f11 = j * f;
            GL11.glVertex2f((float) (f6 + radius * Math.cos(Math.toRadians(f11))), (float) (f7 + radius * Math.sin(Math.toRadians(f11))));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

    public static void enableGL3D(float lineWidth) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(lineWidth);
    }

    public static int rainbow(int delay) {
        double rainbow = Math.ceil((double) (System.currentTimeMillis() + (long) delay) / 10.0D);
        return Color.getHSBColor((float) ((rainbow %= 360.0D) / 360.0D), 0.5F, 1.0F).getRGB();
    }

    public static int rainbow(int delay, float slowspeed) {
        double rainbow = Math.ceil((double) (System.currentTimeMillis() + (long) delay) / (double) slowspeed);
        return Color.getHSBColor((float) ((rainbow %= 360.0D) / 360.0D), 0.5F, 1.0F).getRGB();
    }

    public static void disableGL3D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }


    public static void drawBorderedRect(float x, float y, float x1, float y1, float width, int borderColor) {
        R2DUtils.enableGL2D();
        glColor(borderColor);
        R2DUtils.drawRect(x + width, y, x1 - width, y + width);
        R2DUtils.drawRect(x, y, x + width, y1);
        R2DUtils.drawRect(x1 - width, y, x1, y1);
        R2DUtils.drawRect(x + width, y1 - width, x1 - width, y1);
        R2DUtils.disableGL2D();
    }

    public static void drawCircle(double x, double y, double radius, int c) {
        float f2 = (float) (c >> 24 & 255) / 255.0F;
        float f22 = (float) (c >> 16 & 255) / 255.0F;
        float f3 = (float) (c >> 8 & 255) / 255.0F;
        float f4 = (float) (c & 255) / 255.0F;
        GlStateManager.alphaFunc(516, 0.001F);
        GlStateManager.color(f22, f3, f4, f2);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tes = Tessellator.getInstance();

        for (double i = 0.0D; i < 360.0D; ++i) {
            double f5 = Math.sin(i * 3.141592653589793D / 180.0D) * radius;
            double f6 = Math.cos(i * 3.141592653589793D / 180.0D) * radius;
            GL11.glVertex2d((double) f3 + x, (double) f4 + y);
        }

        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.alphaFunc(516, 0.1F);
    }

    public static void drawFilledCircle1(float x, float y, float radius, int fill) {
        arc1(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void arc1(float x, float y, float start, float end, float radius, int color) {
        arcEllipse1(x, y, start, end, radius, radius, color);
    }


    public static void arcEllipse1(float x, float y, float start, float end, float w, float h, int color) {
        float ldy;
        float ldx;
        float i;
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        float temp = 0.0f;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }
        float var11 = (float) (color >> 24 & 255) / 255.0f;
        float var6 = (float) (color >> 16 & 255) / 255.0f;
        float var7 = (float) (color >> 8 & 255) / 255.0f;
        float var8 = (float) (color & 255) / 255.0f;
        Tessellator var9 = Tessellator.getInstance();
        WorldRenderer var10 = var9.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var6, var7, var8, var11);
        if (var11 > 0.5f) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0f);
            GL11.glBegin(3);
            i = end;
            while (i >= start) {
                ldx = (float) Math.cos((double) i * 3.141592653589793 / 180.0) * (w * 1.001f);
                ldy = (float) Math.sin((double) i * 3.141592653589793 / 180.0) * (h * 1.001f);
                GL11.glVertex2f(x + ldx, y + ldy);
                i -= 4.0f;
            }
            GL11.glEnd();
            GL11.glDisable(2848);
        }
        GL11.glBegin(6);
        i = end;
        while (i >= start) {
            ldx = (float) Math.cos((double) i * 3.141592653589793 / 180.0) * w;
            ldy = (float) Math.sin((double) i * 3.141592653589793 / 180.0) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
            i -= 4.0f;
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawFilledCircle(double x, double y, double r, int c, int id) {
        final boolean blend = glIsEnabled(GL_BLEND);
        final boolean texture = glIsEnabled(GL_TEXTURE_2D);
        if (!blend) glEnable(GL_BLEND);
        if (texture) glDisable(GL_TEXTURE_2D);
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;

//		glEnable(GL_POINT_SMOOTH);
        glColor4f(f1, f2, f3, f);
        glBegin(GL_POLYGON);
        if (id == 1) {
            glVertex2d(x, y);
            for (int i = 0; i <= 90; i++) {
                double x2 = Math.sin((i * Math.PI / 180)) * r;
                double y2 = Math.cos((i * Math.PI / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else if (id == 2) {
            glVertex2d(x, y);
            for (int i = 90; i <= 180; i++) {
                double x2 = Math.sin((i * Math.PI / 180)) * r;
                double y2 = Math.cos((i * Math.PI / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else if (id == 3) {
            glVertex2d(x, y);
            for (int i = 270; i <= 360; i++) {
                double x2 = Math.sin((i * Math.PI / 180)) * r;
                double y2 = Math.cos((i * Math.PI / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else if (id == 4) {
            glVertex2d(x, y);
            for (int i = 180; i <= 270; i++) {
                double x2 = Math.sin((i * Math.PI / 180)) * r;
                double y2 = Math.cos((i * Math.PI / 180)) * r;
                glVertex2d(x - x2, y - y2);
            }
        } else {
            for (int i = 0; i <= 360; i++) {
                double x2 = Math.sin((i * Math.PI / 180)) * r;
                double y2 = Math.cos((i * Math.PI / 180)) * r;
                glVertex2f((float) (x - x2), (float) (y - y2));
            }
        }
        glEnd();
//		glDisable(GL_POINT_SMOOTH);
        if (!blend) glDisable(GL_BLEND);
        if (texture) glEnable(GL_TEXTURE_2D);
        GlStateManager.resetColor();
    }

    public static void drawFullCircle(int cx, int cy, double r, int segments, float lineWidth, int part, int c) {
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        r *= 2.0D;
        cx *= 2;
        cy *= 2;
        float f2 = (float) (c >> 24 & 255) / 255.0F;
        float f22 = (float) (c >> 16 & 255) / 255.0F;
        float f3 = (float) (c >> 8 & 255) / 255.0F;
        float f4 = (float) (c & 255) / 255.0F;
        GL11.glEnable(3042);
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glBegin(3);

        for (int i = segments - part; i <= segments; ++i) {
            double x = Math.sin((double) i * 3.141592653589793D / 180.0D) * r;
            double y = Math.cos((double) i * 3.141592653589793D / 180.0D) * r;
            GL11.glVertex2d((double) cx + x, (double) cy + y);
        }

        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    public static void drawBox(AxisAlignedBB box) {
        if (box != null) {
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glEnd();
        }
    }

    public static void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 255) / 255.0F;
        float red = (float) (hex >> 16 & 255) / 255.0F;
        float green = (float) (hex >> 8 & 255) / 255.0F;
        float blue = (float) (hex & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawOutlinedRect(int x, int y, int width, int height, int lineSize, Color lineColor,
                                        Color backgroundColor) {
        drawRect((float) x, (float) y, (float) width, (float) height, backgroundColor.getRGB());
        drawRect((float) x, (float) y, (float) width, (float) (y + lineSize), lineColor.getRGB());
        drawRect((float) x, (float) (height - lineSize), (float) width, (float) height, lineColor.getRGB());
        drawRect((float) x, (float) (y + lineSize), (float) (x + lineSize), (float) (height - lineSize),
                lineColor.getRGB());
        drawRect((float) (width - lineSize), (float) (y + lineSize), (float) width, (float) (height - lineSize),
                lineColor.getRGB());
    }


    public static void doGlScissor(int x, int y, int width, int height) {
        int scaleFactor = 1;
        int k = mc.getGameSettings().getGuiScale();
        if (k == 0) {
            k = 1000;
        }

        while (scaleFactor < k && mc.getDisplayWidth() / (scaleFactor + 1) >= 320
                && mc.getDisplayHeight() / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        GL11.glPushMatrix();
        GL11.glScissor(x * scaleFactor, mc.getDisplayHeight() - (y + height) * scaleFactor, width * scaleFactor,
                height * scaleFactor);
    }

    public static void drawblock(double a, double a2, double a3, int a4, int a5, float a6) {
        float a7 = (float) (a4 >> 24 & 255) / 255.0F;
        float a8 = (float) (a4 >> 16 & 255) / 255.0F;
        float a9 = (float) (a4 >> 8 & 255) / 255.0F;
        float a10 = (float) (a4 & 255) / 255.0F;
        float a11 = (float) (a5 >> 24 & 255) / 255.0F;
        float a12 = (float) (a5 >> 16 & 255) / 255.0F;
        float a13 = (float) (a5 >> 8 & 255) / 255.0F;
        float a14 = (float) (a5 & 255) / 255.0F;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(a8, a9, a10, a7);
        drawOutlinedBoundingBox(new AxisAlignedBB(a, a2, a3, a + 1.0D, a2 + 1.0D, a3 + 1.0D));
        GL11.glLineWidth(a6);
        GL11.glColor4f(a12, a13, a14, a11);
        drawOutlinedBoundingBox(new AxisAlignedBB(a, a2, a3, a + 1.0D, a2 + 1.0D, a3 + 1.0D));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawRect(float left, float top, float right, float bottom, int color) {
        Gui.drawRect((int) left, (int) top, (int) right, (int) bottom, color);
    }

    public static void color(int color) {
        float f = (float) (color >> 24 & 255) / 255.0F;
        float f1 = (float) (color >> 16 & 255) / 255.0F;
        float f2 = (float) (color >> 8 & 255) / 255.0F;
        float f3 = (float) (color & 255) / 255.0F;
        GL11.glColor4f(f1, f2, f3, f);
    }

    public static int createShader(String shaderCode, int shaderType) throws Exception {
        byte shader = 0;

        int shader1;
        try {
            shader1 = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
            if (shader1 == 0) {
                return 0;
            }
        } catch (Exception var4) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            throw var4;
        }

        ARBShaderObjects.glShaderSourceARB(shader1, shaderCode);
        ARBShaderObjects.glCompileShaderARB(shader1);
        if (ARBShaderObjects.glGetObjectParameteriARB(shader1, '\u8b81') == 0) {
            throw new RuntimeException("Error creating shader:");
        } else {
            return shader1;
        }
    }

    public static void drawBorderRect(float x, float y, float x1, float y1, int color, float lwidth) {
        drawHLine(x, y, x1, y, lwidth, color);
        drawHLine(x1, y, x1, y1, lwidth, color);
        drawHLine(x, y1, x1, y1, lwidth, color);
        drawHLine(x, y1, x, y, lwidth, color);
    }

    public static void drawHLine(float x, float y, float x1, float y1, float width, int color) {
        float var11 = (float) (color >> 24 & 255) / 255.0F;
        float var6 = (float) (color >> 16 & 255) / 255.0F;
        float var7 = (float) (color >> 8 & 255) / 255.0F;
        float var8 = (float) (color & 255) / 255.0F;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var6, var7, var8, var11);
        GL11.glPushMatrix();
        GL11.glLineWidth(width);
        GL11.glBegin(3);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();
        GL11.glLineWidth(1.0F);
        GL11.glPopMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }

    public static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }

    public static void renderFive() {
        GL11.glPolygonOffset(1.0F, 2000000.0F);
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }

//	public static void renderFour() {
//		setColor(new Color(255, 255, 255));
//		GL11.glDepthMask(false);
//		GL11.glDisable(2929);
//		GL11.glEnable(10754);
//		GL11.glPolygonOffset(1.0F, -2000000.0F);
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
//	}

    public static void setColor(Color c) {
        GL11.glColor4d((float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F,
                (float) c.getBlue() / 255.0F, (float) c.getAlpha() / 255.0F);
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
//		WorldRenderer worldRenderer = tessellator.get

        tessellator.draw();

        tessellator.draw();

        tessellator.draw();
    }

    public static void drawBox1(AxisAlignedBB box) {
        if (box != null) {
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glEnd();
        }
    }

    public static void drawCompleteBox(AxisAlignedBB axisalignedbb, float width, int insideColor, int borderColor) {
        GL11.glLineWidth(width);
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        glColor(insideColor);
        drawBox(axisalignedbb);
        glColor(borderColor);
        drawOutlinedBox(axisalignedbb);
        drawCrosses(axisalignedbb);
        GL11.glDisable(2848);
        GL11.glDisable(2881);
    }

    public static void drawCrosses(AxisAlignedBB axisalignedbb, float width, int color) {
        GL11.glLineWidth(width);
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        glColor(color);
        drawCrosses(axisalignedbb);
        GL11.glDisable(2848);
        GL11.glDisable(2881);
    }

    public static void drawOutlineBox(AxisAlignedBB axisalignedbb, float width, int color) {
        GL11.glLineWidth(width);
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        glColor(color);
        drawOutlinedBox(axisalignedbb);
        GL11.glDisable(2848);
        GL11.glDisable(2881);
    }

    public static void drawOutlinedBox(AxisAlignedBB box) {
        if (box != null) {
            GL11.glBegin(3);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(3);
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glEnd();
        }
    }

    public static void drawCrosses(AxisAlignedBB box) {
        if (box != null) {
            GL11.glBegin(1);
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMaxX(), box.getMaxY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMaxZ());
            GL11.glVertex3d(box.getMinX(), box.getMaxY(), box.getMinZ());
            GL11.glVertex3d(box.getMinX(), box.getMinY(), box.getMinZ());
            GL11.glVertex3d(box.getMaxX(), box.getMinY(), box.getMaxZ());
            GL11.glEnd();
        }
    }

    public static void drawBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
//		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        tessellator.draw();
    }

    public static void drawOutlinedBlockESP(double x, double y, double z, float red, float green, float blue,
                                            float alpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawBlockESP(double x, double y, double z, float red, float green, float blue, float alpha,
                                    float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue,
                                         float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red,
                                             float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawSolidEntityESP(double x, double y, double z, double width, double height, float red,
                                          float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green,
                                     float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green,
                                     float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void startGlScissor(int x, int y, int width, int height) {
        int scaleFactor = new ScaledResolution(mc).getScaleFactor();
        GL11.glPushMatrix();
        glEnable(3089);
        GL11.glScissor((x * scaleFactor), (mc.getDisplayHeight() - (y + height) * scaleFactor),
                (width * scaleFactor), ((height + 14) * scaleFactor));
    }

    public static void stopGlScissor() {
        glDisable(3089);
        GL11.glPopMatrix();
    }

    private static void glColor(Color color) {
        GL11.glColor4f((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F,
                (float) color.getBlue() / 255.0F, (float) color.getAlpha() / 255.0F);
    }

    public static void drawFilledBox(AxisAlignedBB mask) {
        Tessellator tessellator = Tessellator.getInstance();
//		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        tessellator.draw();
    }

    public static void drawRoundedRect(float x, float y, float x1, float y1, int borderC, int insideC) {
        drawRect(x + 0.5F, y, x1 - 0.5F, y + 0.5F, insideC);
        drawRect(x + 0.5F, y1 - 0.5F, x1 - 0.5F, y1, insideC);
        drawRect(x, y + 0.5F, x1, y1 - 0.5F, insideC);
    }

    public static void circle(float x, float y, float radius, int fill) {
        arc(x, y, 0.0F, 360.0F, radius, fill);
    }

    public static void circle(float x, float y, float radius, Color fill) {
        arc(x, y, 0.0F, 360.0F, radius, fill);
    }

    public static void arc(float x, float y, float start, float end, float radius, int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arc(float x, float y, float start, float end, float radius, Color color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arcEllipse(float x, float y, float start, float end, float w, float h, int color) {
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);
        float temp = 0.0F;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }

        float var11 = (float) (color >> 24 & 255) / 255.0F;
        float var12 = (float) (color >> 16 & 255) / 255.0F;
        float var13 = (float) (color >> 8 & 255) / 255.0F;
        float var14 = (float) (color & 255) / 255.0F;
        Tessellator var15 = Tessellator.getInstance();
//		WorldRenderer var16 = var15.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        float i;
        float ldx;
        float ldy;
        if (var11 > 0.5F) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0F);
            GL11.glBegin(3);

            for (i = end; i >= start; i -= 4.0F) {
                ldx = (float) Math.cos((double) i * 3.141592653589793D / 180.0D) * w * 1.001F;
                ldy = (float) Math.sin((double) i * 3.141592653589793D / 180.0D) * h * 1.001F;
                GL11.glVertex2f(x + ldx, y + ldy);
            }

            GL11.glEnd();
            GL11.glDisable(2848);
        }

        GL11.glBegin(6);

        for (i = end; i >= start; i -= 4.0F) {
            ldx = (float) Math.cos((double) i * 3.141592653589793D / 180.0D) * w;
            ldy = (float) Math.sin((double) i * 3.141592653589793D / 180.0D) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }

        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void arcEllipse(float x, float y, float start, float end, float w, float h, Color color) {
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);
        float temp = 0.0F;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }

        Tessellator var9 = Tessellator.getInstance();
//		WorldRenderer var10 = var9.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F,
                (float) color.getBlue() / 255.0F, (float) color.getAlpha() / 255.0F);
        float i;
        float ldx;
        float ldy;
        if ((float) color.getAlpha() > 0.5F) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0F);
            GL11.glBegin(3);

            for (i = end; i >= start; i -= 4.0F) {
                ldx = (float) Math.cos((double) i * 3.141592653589793D / 180.0D) * w * 1.001F;
                ldy = (float) Math.sin((double) i * 3.141592653589793D / 180.0D) * h * 1.001F;
                GL11.glVertex2f(x + ldx, y + ldy);
            }

            GL11.glEnd();
            GL11.glDisable(2848);
        }

        GL11.glBegin(6);

        for (i = end; i >= start; i -= 4.0F) {
            ldx = (float) Math.cos((double) i * 3.141592653589793D / 180.0D) * w;
            ldy = (float) Math.sin((double) i * 3.141592653589793D / 180.0D) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }

        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float) (col1 >> 24 & 255) / 255.0F;
        float f1 = (float) (col1 >> 16 & 255) / 255.0F;
        float f2 = (float) (col1 >> 8 & 255) / 255.0F;
        float f3 = (float) (col1 & 255) / 255.0F;
        float f4 = (float) (col2 >> 24 & 255) / 255.0F;
        float f5 = (float) (col2 >> 16 & 255) / 255.0F;
        float f6 = (float) (col2 >> 8 & 255) / 255.0F;
        float f7 = (float) (col2 & 255) / 255.0F;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor,
                                         int borderColor) {
        rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        rectangle(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        rectangle(x, y, x + width, y1, borderColor);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        rectangle(x1 - width, y, x1, y1, borderColor);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void rectangle(double left, double top, double right, double bottom, int color) {
        double var11;
        if (left < right) {
            var11 = left;
            left = right;
            right = var11;
        }

        if (top < bottom) {
            var11 = top;
            top = bottom;
            bottom = var11;
        }

        float var111 = (float) (color >> 24 & 255) / 255.0F;
        float var6 = (float) (color >> 16 & 255) / 255.0F;
        float var7 = (float) (color >> 8 & 255) / 255.0F;
        float var8 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
//		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var6, var7, var8, var111);

        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawBorderedRect(double x2, double d2, double x22, double e2, float l1, int col1, int col2) {
        drawRect(x2, d2, x22, e2, col2);
        float f2 = (float) (col1 >> 24 & 255) / 255.0F;
        float f22 = (float) (col1 >> 16 & 255) / 255.0F;
        float f3 = (float) (col1 >> 8 & 255) / 255.0F;
        float f4 = (float) (col1 & 255) / 255.0F;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d(x2, d2);
        GL11.glVertex2d(x2, e2);
        GL11.glVertex2d(x22, e2);
        GL11.glVertex2d(x22, d2);
        GL11.glVertex2d(x2, d2);
        GL11.glVertex2d(x22, d2);
        GL11.glVertex2d(x2, e2);
        GL11.glVertex2d(x22, e2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawRect(double d, double e, double g, double h, int color) {
        int f3;
        if (d < g) {
            f3 = (int) d;
            d = g;
            g = f3;
        }

        if (e < h) {
            f3 = (int) e;
            e = h;
            h = f3;
        }

        float f31 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
//		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f31);

        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static Color blend(Color color1, Color color2, double ratio) {
        float r = (float) ratio;
        float ir = 1.0F - r;
        float[] rgb1 = new float[3];
        float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        Color color3 = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);
        return color3;
    }

    public static void drawRoundRect(double d, double e, double g, double h, int color) {
        drawRect(d + 1.0D, e, g - 1.0D, h, color);
        drawRect(d, e + 1.0D, d + 1.0D, h - 1.0D, color);
        drawRect(d + 1.0D, e + 1.0D, d + 0.5D, e + 0.5D, color);
        drawRect(d + 1.0D, e + 1.0D, d + 0.5D, e + 0.5D, color);
        drawRect(g - 1.0D, e + 1.0D, g - 0.5D, e + 0.5D, color);
        drawRect(g - 1.0D, e + 1.0D, g, h - 1.0D, color);
        drawRect(d + 1.0D, h - 1.0D, d + 0.5D, h - 0.5D, color);
        drawRect(g - 1.0D, h - 1.0D, g - 0.5D, h - 0.5D, color);
    }

    public static void pre() {
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }

    public static void post() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glColor3d(1.0D, 1.0D, 1.0D);
    }

    public void drawCircle(int x, int y, float radius, int color) {
        float alpha = (float) (color >> 24 & 255) / 255.0F;
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        boolean blend = GL11.glIsEnabled(3042);
        boolean line = GL11.glIsEnabled(2848);
        boolean texture = GL11.glIsEnabled(3553);
        if (!blend) {
            GL11.glEnable(3042);
        }

        if (!line) {
            GL11.glEnable(2848);
        }

        if (texture) {
            GL11.glDisable(3553);
        }

        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(9);

        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d((double) x + Math.sin((double) i * 3.141526D / 180.0D) * (double) radius,
                    (double) y + Math.cos((double) i * 3.141526D / 180.0D) * (double) radius);
        }

        GL11.glEnd();
        if (texture) {
            GL11.glEnable(3553);
        }

        if (!line) {
            GL11.glDisable(2848);
        }

        if (!blend) {
            GL11.glDisable(3042);
        }

    }

}

class R2DUtils {
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawRoundedRect(float x, float y, float x1, float y1, int borderC, int insideC) {
        enableGL2D();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        drawVLine(x *= 2.0F, (y *= 2.0F) + 1.0F, (y1 *= 2.0F) - 2.0F, borderC);
        drawVLine((x1 *= 2.0F) - 1.0F, y + 1.0F, y1 - 2.0F, borderC);
        drawHLine(x + 2.0F, x1 - 3.0F, y, borderC);
        drawHLine(x + 2.0F, x1 - 3.0F, y1 - 1.0F, borderC);
        drawHLine(x + 1.0F, x + 1.0F, y + 1.0F, borderC);
        drawHLine(x1 - 2.0F, x1 - 2.0F, y + 1.0F, borderC);
        drawHLine(x1 - 2.0F, x1 - 2.0F, y1 - 2.0F, borderC);
        drawHLine(x + 1.0F, x + 1.0F, y1 - 2.0F, borderC);
        drawRect(x + 1.0F, y + 1.0F, x1 - 1.0F, y1 - 1.0F, insideC);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        disableGL2D();
        Gui.drawRect(0, 0, 0, 0, 0);
    }

    public static void drawRect(double x2, double y2, double x1, double y1, int color) {
        enableGL2D();
        glColor(color);
        drawRect(x2, y2, x1, y1);
        disableGL2D();
    }

    private static void drawRect(double x2, double y2, double x1, double y1) {
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
    }

    public static void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 255) / 255.0F;
        float red = (float) (hex >> 16 & 255) / 255.0F;
        float green = (float) (hex >> 8 & 255) / 255.0F;
        float blue = (float) (hex & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawRect(float x, float y, float x1, float y1, int color) {
        enableGL2D();
        glColor(color);
        drawRect(x, y, x1, y1);
        disableGL2D();
    }

    public static void drawBorderedRect(float x, float y, float x1, float y1, float width, int borderColor) {
        enableGL2D();
        glColor(borderColor);
        drawRect(x + width, y, x1 - width, y + width);
        drawRect(x, y, x + width, y1);
        drawRect(x1 - width, y, x1, y1);
        drawRect(x + width, y1 - width, x1 - width, y1);
        disableGL2D();
    }

    public static void drawBorderedRect(float x, float y, float x1, float y1, int insideC, int borderC) {
        enableGL2D();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        drawVLine(x *= 2.0F, y *= 2.0F, y1 *= 2.0F, borderC);
        drawVLine((x1 *= 2.0F) - 1.0F, y, y1, borderC);
        drawHLine(x, x1 - 1.0F, y, borderC);
        drawHLine(x, x1 - 2.0F, y1 - 1.0F, borderC);
        drawRect(x + 1.0F, y + 1.0F, x1 - 1.0F, y1 - 1.0F, insideC);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        disableGL2D();
    }

    public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
        enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        glColor(topColor);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        glColor(bottomColor);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        disableGL2D();
    }

    public static void drawHLine(float x, float y, float x1, int y1) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }

        drawRect(x, x1, y + 1.0F, x1 + 1.0F, y1);
    }

    public static void drawVLine(float x, float y, float x1, int y1) {
        if (x1 < y) {
            float var5 = y;
            y = x1;
            x1 = var5;
        }

        drawRect(x, y + 1.0F, x + 1.0F, x1, y1);
    }

    public static void drawHLine(float x, float y, float x1, int y1, int y2) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }

        drawGradientRect(x, x1, y + 1.0F, x1 + 1.0F, y1, y2);
    }

    public static void drawRect(float x, float y, float x1, float y1) {
        GL11.glBegin(7);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
    }
}
