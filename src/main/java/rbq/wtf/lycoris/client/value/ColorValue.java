package rbq.wtf.lycoris.client.value;

import rbq.wtf.lycoris.client.module.Module;

import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ColorValue extends Value<Color> {
    private Color awtvalue;
    private Integer value;
    private String name;
    private Module module;
    public boolean rainbow;


    public ColorValue(String Name, Color col) {
        name = Name;
        awtvalue = col;
        value = col.getRGB();
        module = null;
    }


    public ColorValue(String Name, Integer val) {
        name = Name;
        value = val;
        module = null;
    }


    @Override
    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public Module getModule() {
        return module;
    }

    public void setName(String n) {
        name = n;
    }


    public String getName() {
        return name;
    }


    public void setValue(Color value) {
        awtvalue = value;
        this.value = value.getRGB();
    }

    public void setState(Color value) {
        awtvalue = value;
    }

    public Color getValue() {
        return awtvalue;
    }


    public Color getState() {
        return awtvalue;
    }


    public Color getValueState() {
        return awtvalue;
    }

    public Color getValue(float offset) {

        if (rainbow) {
            final float[] hsb = new float[3];
            Color.RGBtoHSB(awtvalue.getRed(), awtvalue.getGreen(), awtvalue.getBlue(), hsb);
            hsb[0] = (hsb[0] - offset * 0.001f) % 73;
            return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
        } else {
            return awtvalue;
        }
    }

    public Color getValue(float offset, int realpha) {
        if (rainbow) {
            final float[] hsb = new float[3];
            Color.RGBtoHSB(awtvalue.getRed(), awtvalue.getGreen(), awtvalue.getBlue(), hsb);
            hsb[0] = (hsb[0] - offset * 0.001f) % 73;
            Color cc = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
            return new Color(cc.getRed(), cc.getGreen(), cc.getBlue(), realpha);
        } else {
            return awtvalue;
        }
    }


    public Color getValueR(float offset) {
        if (rainbow) {
            final float[] hsb = new float[3];
            Color.RGBtoHSB(awtvalue.getRed(), awtvalue.getGreen(), awtvalue.getBlue(), hsb);
            hsb[0] = (hsb[0] + offset * 0.001f) % 73;
            return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
        } else {
            return awtvalue;
        }
    }

    public Color getValueR(float offset, int realAlpha) {
        if (rainbow) {
            final float[] hsb = new float[3];
            Color.RGBtoHSB(awtvalue.getRed(), awtvalue.getGreen(), awtvalue.getBlue(), hsb);
            hsb[0] = (hsb[0] + offset * 0.001f) % 73;
            Color cc = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
            return new Color(cc.getRed(), cc.getGreen(), cc.getBlue(), realAlpha);
        } else {
            return awtvalue;
        }
    }


    public int getRed() {
        return awtvalue.getRed();
    }

    public int getGreen() {
        return awtvalue.getGreen();
    }

    public int getBlue() {
        return awtvalue.getBlue();
    }

    public int getAlpha() {
        return awtvalue.getAlpha();
    }


    public float[] getHSB() {
        if (value == null) return new float[]{0.0F, 0.0F, 0.0F};
        float[] hsbValues = new float[3];

        float saturation, brightness;
        float hue;

        int cMax = max(value >>> 16 & 0xFF, value >>> 8 & 0xFF);
        if ((value & 0xFF) > cMax) cMax = value & 0xFF;

        int cMin = min(value >>> 16 & 0xFF, value >>> 8 & 0xFF);
        if ((value & 0xFF) < cMin) cMin = value & 0xFF;

        brightness = (float) cMax / 255.0F;
        saturation = cMax != 0 ? (float) (cMax - cMin) / (float) cMax : 0;

        if (saturation == 0) {
            hue = 0;
        } else {
            float redC = (float) (cMax - (value >>> 16 & 0xFF)) / (float) (cMax - cMin), // @off
                    greenC = (float) (cMax - (value >>> 8 & 0xFF)) / (float) (cMax - cMin),
                    blueC = (float) (cMax - (value & 0xFF)) / (float) (cMax - cMin); // @on

            hue = ((value >>> 16 & 0xFF) == cMax ?
                    blueC - greenC :
                    (value >>> 8 & 0xFF) == cMax ? 2.0F + redC - blueC : 4.0F + greenC - redC) / 6.0F;

            if (hue < 0) hue += 1.0F;
        }

        hsbValues[0] = hue;
        hsbValues[1] = saturation;
        hsbValues[2] = brightness;
        return hsbValues;
    }
}
