package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;


import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.WorldRenderer", targetMap = MapEnum.VANILLA189)
public class WorldRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.WorldRenderer", targetMap = MapEnum.VANILLA189)
    public static Class WorldRendererClass;
    @WrapMethod(mcpName = "begin", targetMap = MapEnum.VANILLA189)
    public static Method begin;
    @WrapMethod(mcpName = "pos", targetMap = MapEnum.VANILLA189)
    public static Method pos;
    @WrapMethod(mcpName = "endVertex", targetMap = MapEnum.VANILLA189)
    public static Method endVertex;
    @WrapMethod(mcpName = "color", targetMap = MapEnum.VANILLA189, signature = "(FFFF)Lnet/minecraft/client/renderer/WorldRenderer;")
    public static Method color_4f;
    @WrapMethod(mcpName = "color", targetMap = MapEnum.VANILLA189, signature = "(IIII)Lnet/minecraft/client/renderer/WorldRenderer;")
    public static Method color_4I;
    @WrapMethod(mcpName = "putColorMultiplier", targetMap = MapEnum.VANILLA189)
    public static Method putColorMultiplier;
    @WrapMethod(mcpName = "tex", targetMap = MapEnum.VANILLA189)
    public static Method tex;

    public WorldRenderer(Object obj) {
        super(obj);
    }

    public void begin(int glMode, VertexFormat format) {
        invoke(begin, glMode, format.getWrapObject());
    }

    public WorldRenderer pos(double x, double y, double z) {
        invoke(pos, x, y, z);
        return this;
    }

    public void endVertex() {
        invoke(endVertex);
    }

    public WorldRenderer color(float red, float green, float blue, float alpha) {
        invoke(color_4f, red, green, blue, alpha);
        return this;
    }

    public WorldRenderer color(int i1, int i2, int i3, int i4) {
        invoke(color_4I, i1, i2, i3, i4);
        return this;
    }

    public WorldRenderer tex(double u2, double v1) {
        invoke(tex, u2, v1);
        return this;
    }
}