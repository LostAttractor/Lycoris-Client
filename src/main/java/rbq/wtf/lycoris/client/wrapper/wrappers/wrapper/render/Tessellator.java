package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Tessellator extends IWrapper {
    @WrapMethod(mcpName = "getInstance", targetMap = MapEnum.VANILLA189)
    public static Method getInstance;
    @WrapField(mcpName = "worldRenderer", targetMap = MapEnum.VANILLA189)
    public static Field worldRenderer;
    @WrapMethod(mcpName = "draw", targetMap = MapEnum.VANILLA189)
    public static Method draw;

    public Tessellator(Object obj) {
        super(obj);
    }

    public static Tessellator getInstance() {
        return new Tessellator(ReflectUtil.invokeStatic(getInstance));
    }

    public WorldRenderer getWorldRenderer() {
        return new WorldRenderer(getField(worldRenderer));
    }

    public void draw() {
        invoke(draw);
    }
}
