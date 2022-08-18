package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.potion;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.utils.ReflectUtil;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = MapEnum.VANILLA189)
public class Potion extends IWrapper{

    @WrapField(mcpName = "blindness", targetMap = MapEnum.VANILLA189)
    public static Field blindness;

    public Potion(Object obj) {
        super(obj);
    }

    public static Potion getBlindness() {
        return new Potion(ReflectUtil.getField(blindness, null));
    }
}
