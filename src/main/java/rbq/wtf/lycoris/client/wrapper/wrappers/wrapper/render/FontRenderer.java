package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer", targetMap = MapEnum.VANILLA189)
public class FontRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.FontRenderer", targetMap = MapEnum.VANILLA189)
    public static Class<?> FontRendererClass;

    public FontRenderer(Object obj) {
        super(obj);
    }
}