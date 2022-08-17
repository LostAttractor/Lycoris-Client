package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;

import java.awt.image.BufferedImage;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture", targetMap = MapEnum.VANILLA189)
public class DynamicTexture extends AbstractTexture {
    @WrapClass(mcpName = "net.minecraft.client.renderer.texture.DynamicTexture", targetMap = MapEnum.VANILLA189)
    public static Class DynamicTextureClass;

    public DynamicTexture(Object obj) {
        super(obj);
    }

    public DynamicTexture(BufferedImage bufferedImage) {
        super(ReflectUtil.construction(DynamicTextureClass, bufferedImage));
    }

    public DynamicTexture(int textureWidth, int textureHeight) {
        super(ReflectUtil.construction(DynamicTextureClass, textureWidth, textureHeight));
    }
}
