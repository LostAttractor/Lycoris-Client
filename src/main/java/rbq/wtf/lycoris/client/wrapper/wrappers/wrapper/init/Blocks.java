package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.init;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block.Block;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.init.Blocks", targetMap = MapEnum.VANILLA189)
public class Blocks extends IWrapper {
    @WrapField(mcpName = "air", targetMap = MapEnum.VANILLA189)
    public static Field air;

    public Blocks(Object obj) {
        super(obj);
    }

    public static Block getAir() {
        return new Block(ReflectUtil.getFieldStatic(air));
    }
}
