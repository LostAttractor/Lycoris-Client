package rbq.wtf.lycoris.client.gui.ClickGUI.Component;


import org.lwjgl.opengl.GL11;
import rbq.wtf.lycoris.client.gui.ClickGUI.Utils.RenderUtil;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.value.ColorValue;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.DefaultVertexFormats;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.GlStateManager;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.Tessellator;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.WorldRenderer;

import java.awt.*;

public class ColorValueComponent extends Component {


    private static final int MARGIN = 3;
    private static final int SLIDER_THICKNESS = 8;
    private static final float SELECTOR_WIDTH = 1.0f;
    private static final float HALF_WIDTH = 0.5f;
    private static final float OUTLINE_WIDTH = 0.5f;
    private boolean expanded;
    private float hue;
    private float saturation;
    private float brightness;
    private float alpha;
    private boolean colorSelectorDragging;
    private boolean hueSelectorDragging;
    private boolean alphaSelectorDragging;
    public static Tessellator tessellator = Tessellator.getInstance();
    public static WorldRenderer worldrenderer = tessellator.getWorldRenderer();


    public ColorValue value;
    private float x;
    private float y;

    public ColorValueComponent(ColorValue Value) {
        tessellator = Tessellator.getInstance();
        worldrenderer = tessellator.getWorldRenderer();
        this.value = Value;
        this.hue = value.getHSB()[0];
        this.saturation = value.getHSB()[1];
        this.brightness = value.getHSB()[2];
        setHeight(100);
        setWidth(11 - 80);
    }

    @Override
    public void updateComponent(float X, float Y, int mouseX, int mouseY) {
        this.x = X + 305;
        this.y = Y + 10;
        FontLoaders.default20.drawString(value.getName(),
                X,
                Y,
                new Color(255, 255, 255).getRGB());

        float x2 = this.x;
        float y2 = this.y;
        float width = this.getWidth();
        float height = this.getHeight();
        int black = new Color(0, 0, 0).getRGB();
//        RenderUtil.drawRect(x2 - 0.5f, y2 - 0.5f, (x2 + width) + 0.5f, (y2 + height) + 0.5f, black);
        int guiAlpha = (int) 255;
        int color = this.value.getValue().getRGB();
        int colorAlpha = color >> 24 & 0xFF;
        int minAlpha = Math.min(guiAlpha, colorAlpha);

        if (colorAlpha < 255) {
            drawCheckeredBackground(x2, y2, x2 + width, y2 + height);
        }

        int newColor = new Color(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, minAlpha).getRGB();
//        drawGradientRect(x2, y2, x2 + width, y2 + height, newColor, darker(newColor, 0.6f));
//        if (this.isExpanded()) {
        float hueSelectorY;
        float hueSliderYDif;
        float alphaSliderBottom;
        float hueSliderRight;
        GL11.glTranslated(0.0, 0.0, 3.0);
        float expandedX = this.getExpandedX();
        float expandedY = this.getExpandedY();
        float expandedWidth = this.getExpandedWidth();
        float expandedHeight = this.getExpandedHeight();
//            RenderUtil.drawRect(expandedX, expandedY, expandedX + expandedWidth, expandedY + expandedHeight, black);
//            RenderUtil.drawRect(expandedX + 0.5f, expandedY + 0.5f, (expandedX + expandedWidth) - 0.5f, (expandedY + expandedHeight) - 0.5f, getColor(0x39393B));
//            RenderUtil.drawRect(expandedX + 1.0f, expandedY + 1.0f, expandedX + expandedWidth - 1.0f, expandedY + expandedHeight - 1.0f, getColor(0x232323));
        float colorPickerSize = expandedWidth - 9.0f - 8.0f;
        float colorPickerLeft = expandedX + 3.0f;
        float colorPickerTop = expandedY + 3.0f;
        float colorPickerRight = colorPickerLeft + colorPickerSize;
        float colorPickerBottom = colorPickerTop + colorPickerSize;
        int selectorWhiteOverlayColor = new Color(255, 255, 255, Math.min(guiAlpha, 180)).getRGB();
        if ((float) mouseX <= colorPickerLeft || (float) mouseY <= colorPickerTop || (float) mouseX >= colorPickerRight || (float) mouseY >= colorPickerBottom) {
            this.colorSelectorDragging = false;
        }
        RenderUtil.drawRect(colorPickerLeft - 0.5f, colorPickerTop - 0.5f, colorPickerRight + 0.5f, colorPickerBottom + 0.5f, getColor(0));
        this.drawColorPickerRect(colorPickerLeft, colorPickerTop, colorPickerRight, colorPickerBottom);
        float hueSliderLeft = this.saturation * (colorPickerRight - colorPickerLeft);
        float alphaSliderTop = (1.0f - this.brightness) * (colorPickerBottom - colorPickerTop);
        if (this.colorSelectorDragging) {
            hueSliderRight = colorPickerRight - colorPickerLeft;
            alphaSliderBottom = (float) mouseX - colorPickerLeft;
            this.saturation = alphaSliderBottom / hueSliderRight;
            hueSliderLeft = alphaSliderBottom;
            hueSliderYDif = colorPickerBottom - colorPickerTop;
            hueSelectorY = (float) mouseY - colorPickerTop;
            this.brightness = 1.0f - hueSelectorY / hueSliderYDif;
            alphaSliderTop = hueSelectorY;
            this.updateColor(Color.HSBtoRGB(this.hue, this.saturation, this.brightness), false);
        }
        hueSliderRight = colorPickerLeft + hueSliderLeft - 0.5f;
        alphaSliderBottom = colorPickerTop + alphaSliderTop - 0.5f;
        hueSliderYDif = colorPickerLeft + hueSliderLeft + 0.5f;
        hueSelectorY = colorPickerTop + alphaSliderTop + 0.5f;
//            RenderUtil.drawRect(hueSliderRight - 0.5f, alphaSliderBottom - 0.5f, hueSliderRight, hueSelectorY + 0.5f, black);
        RenderUtil.drawRect(hueSliderYDif, alphaSliderBottom - 0.5f, hueSliderYDif + 0.5f, hueSelectorY + 0.5f, black);
        RenderUtil.drawRect(hueSliderRight, alphaSliderBottom - 0.5f, hueSliderYDif, alphaSliderBottom, black);
        RenderUtil.drawRect(hueSliderRight, hueSelectorY, hueSliderYDif, hueSelectorY + 0.5f, black);
        RenderUtil.drawRect(hueSliderRight, alphaSliderBottom, hueSliderYDif, hueSelectorY, selectorWhiteOverlayColor);
        hueSliderLeft = colorPickerRight + 3.0f;
        hueSliderRight = hueSliderLeft + 8.0f;
        if ((float) mouseX <= hueSliderLeft || (float) mouseY <= colorPickerTop || (float) mouseX >= hueSliderRight || (float) mouseY >= colorPickerBottom) {
            this.hueSelectorDragging = false;
        }
        hueSliderYDif = colorPickerBottom - colorPickerTop;
        hueSelectorY = (1.0f - this.hue) * hueSliderYDif;
        if (this.hueSelectorDragging) {
            float inc = (float) mouseY - colorPickerTop;
            this.hue = 1.0f - inc / hueSliderYDif;
            hueSelectorY = inc;
            this.updateColor(Color.HSBtoRGB(this.hue, this.saturation, this.brightness), false);
        }
        RenderUtil.drawRect(hueSliderLeft - 0.5f, colorPickerTop - 0.5f, hueSliderRight + 0.5f, colorPickerBottom + 0.5f, black);
        float hsHeight = colorPickerBottom - colorPickerTop;
        float alphaSelectorX = hsHeight / 5.0f;
        float asLeft = colorPickerTop;
        int i2 = 0;
        while ((float) i2 < 5.0f) {
            boolean last = (float) i2 == 4.0f;
            drawGradientRect(hueSliderLeft, asLeft, hueSliderRight, asLeft + alphaSelectorX, getColor(Color.HSBtoRGB(1.0f - 0.2f * (float) i2, 1.0f, 1.0f)), getColor(Color.HSBtoRGB(1.0f - 0.2f * (float) (i2 + 1), 1.0f, 1.0f)));
            if (!last) {
                asLeft += alphaSelectorX;
            }
            ++i2;
        }
        float hsTop = colorPickerTop + hueSelectorY - 0.5f;
        float asRight = colorPickerTop + hueSelectorY + 0.5f;
        RenderUtil.drawRect(hueSliderLeft - 0.5f, hsTop - 0.5f, hueSliderLeft, asRight + 0.5f, black);
        RenderUtil.drawRect(hueSliderRight, hsTop - 0.5f, hueSliderRight + 0.5f, asRight + 0.5f, black);
        RenderUtil.drawRect(hueSliderLeft, hsTop - 0.5f, hueSliderRight, hsTop, black);
        RenderUtil.drawRect(hueSliderLeft, asRight, hueSliderRight, asRight + 0.5f, black);
        RenderUtil.drawRect(hueSliderLeft, hsTop, hueSliderRight, asRight, selectorWhiteOverlayColor);
        alphaSliderTop = colorPickerBottom + 3.0f;
        alphaSliderBottom = alphaSliderTop + 8.0f;
        if ((float) mouseX <= colorPickerLeft || (float) mouseY <= alphaSliderTop || (float) mouseX >= colorPickerRight || (float) mouseY >= alphaSliderBottom) {
            this.alphaSelectorDragging = false;
        }
        int z2 = Color.HSBtoRGB(this.hue, this.saturation, this.brightness);
        int r2 = z2 >> 16 & 0xFF;
        int g2 = z2 >> 8 & 0xFF;
        int b2 = z2 & 0xFF;
        hsHeight = colorPickerRight - colorPickerLeft;
        alphaSelectorX = this.alpha * hsHeight;
        if (this.alphaSelectorDragging) {
            asLeft = (float) mouseX - colorPickerLeft;
            this.alpha = asLeft / hsHeight;
            alphaSelectorX = asLeft;
            this.updateColor(new Color(r2, g2, b2, (int) (this.alpha * 255.0f)).getRGB(), true);
        }
//            RenderUtil.drawRect(colorPickerLeft, alphaSliderTop, colorPickerRight, alphaSliderBottom, black);
        drawCheckeredBackground(colorPickerLeft, alphaSliderTop, colorPickerRight, alphaSliderBottom);
        drawGradientRect(colorPickerLeft, alphaSliderTop, colorPickerRight, alphaSliderBottom, true, new Color(r2, g2, b2, 0).getRGB(), new Color(r2, g2, b2, Math.min(guiAlpha, 255)).getRGB());
        asLeft = colorPickerLeft + alphaSelectorX - 0.5f;
        asRight = colorPickerLeft + alphaSelectorX + 0.5f;
        RenderUtil.drawRect(asLeft - 0.5f, alphaSliderTop, asRight + 0.5f, alphaSliderBottom, black);
        RenderUtil.drawRect(asLeft, alphaSliderTop, asRight, alphaSliderBottom, selectorWhiteOverlayColor);
        GL11.glTranslated(0.0, 0.0, -3.0);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            float expandedX = this.getExpandedX();
            float expandedY = this.getExpandedY();
            float expandedWidth = this.getExpandedWidth();
            float expandedHeight = this.getExpandedHeight();
            float colorPickerSize = expandedWidth - 9.0f - 8.0f;
            float colorPickerLeft = expandedX + 3.0f;
            float colorPickerTop = expandedY + 3.0f;
            float colorPickerRight = colorPickerLeft + colorPickerSize;
            float colorPickerBottom = colorPickerTop + colorPickerSize;
            float alphaSliderTop = colorPickerBottom + 3.0f;
            float alphaSliderBottom = alphaSliderTop + 8.0f;
            float hueSliderLeft = colorPickerRight + 3.0f;
            float hueSliderRight = hueSliderLeft + 8.0f;
            this.colorSelectorDragging = !this.colorSelectorDragging && (float) mouseX > colorPickerLeft && (float) mouseY > colorPickerTop && (float) mouseX < colorPickerRight && (float) mouseY < colorPickerBottom;
            this.alphaSelectorDragging = !this.alphaSelectorDragging && (float) mouseX > colorPickerLeft && (float) mouseY > alphaSliderTop && (float) mouseX < colorPickerRight && (float) mouseY < alphaSliderBottom;
            this.hueSelectorDragging = !this.hueSelectorDragging && (float) mouseX > hueSliderLeft && (float) mouseY > colorPickerTop && (float) mouseX < hueSliderRight && (float) mouseY < colorPickerBottom;
        }
    }

    @Override
    public void mouseReleased(int mouseButton) {
        if (this.hueSelectorDragging) {
            this.hueSelectorDragging = false;
        } else if (this.colorSelectorDragging) {
            this.colorSelectorDragging = false;
        } else if (this.alphaSelectorDragging) {
            this.alphaSelectorDragging = false;
        }
    }

    private void updateColor(final int hex, final boolean hasAlpha) {
        if (hasAlpha) {
            this.value.setValue(new Color(hex));
        } else {
            this.value.setValue(new Color(hex >> 16 & 0xFF, hex >> 8 & 0xFF, hex & 0xFF, (int) (this.alpha * 255.0f)));
        }
    }

    private static void drawCheckeredBackground(float x, float y, float x2, float y2) {
//        RenderUtil.drawRect(x, y, x2, y2, getColor(16777215));
        for (boolean offset = false; y < y2; ++y) {
            for (float x1 = x + (float) ((offset = !offset) ? 1 : 0); x1 < x2; x1 += 2.0F) {
                if (x1 <= x2 - 1.0F) {
                    RenderUtil.drawRect(x1, y, x1 + 1.0F, y + 1.0F, getColor(8421504));
                }
            }
        }
    }

    public static int darker(int color, float factor) {
        int r = (int) ((color >> 16 & 0xFF) * factor);
        int g = (int) ((color >> 8 & 0xFF) * factor);
        int b = (int) ((color & 0xFF) * factor);
        int a = color >> 24 & 0xFF;
        return (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF | (a & 0xFF) << 24;
    }

    public void drawGradientRect(float left, float top, float right, float bottom, int startColor, int endColor) {
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR());
        worldrenderer.pos(right, top, 0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, 0).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public void drawGradientRect(double left, double top, double right, double bottom, boolean sideways, int startColor, int endColor) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        RenderUtil.color(startColor);
        if (sideways) {
            GL11.glVertex2d(left, top);
            GL11.glVertex2d(left, bottom);
            RenderUtil.color(endColor);
            GL11.glVertex2d(right, bottom);
            GL11.glVertex2d(right, top);
        } else {
            GL11.glVertex2d(left, top);
            RenderUtil.color(endColor);
            GL11.glVertex2d(left, bottom);
            GL11.glVertex2d(right, bottom);
            RenderUtil.color(startColor);
            GL11.glVertex2d(right, top);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public float getExpandedX() {
        return this.x + 11 - 80.333336f;
    }


    public float getExpandedY() {
        return this.y + 5;
    }


    public float getExpandedWidth() {
        float right = x + 11;
        return right - this.getExpandedX();
    }

    public float getExpandedHeight() {
        return this.getExpandedWidth();
    }

    private void drawColorPickerRect(float left, float top, float right, float bottom) {
        int hueBasedColor = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
        drawGradientRect(left, top, right, bottom, true, getColor(16777215), hueBasedColor);
        drawGradientRect(left, top, right, bottom, 0, getColor(0));
    }

    public static int getColor(int color) {
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        int a = (int) 255;
        return (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF | (a & 0xFF) << 24;
    }
}
