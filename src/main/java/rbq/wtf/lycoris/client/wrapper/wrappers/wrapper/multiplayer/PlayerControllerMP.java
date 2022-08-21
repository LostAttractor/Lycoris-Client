package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.multiplayer;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.PlayerControllerMP", targetMap = MapEnum.VANILLA189)
public class PlayerControllerMP extends IWrapper {

    @WrapField(mcpName = "curBlockDamageMP", targetMap = MapEnum.VANILLA189)
    public static Field curBlockDamageMP;
    public PlayerControllerMP(Object obj) {
        super(obj);
    }

    public float getCurBlockDamageMP() {
        return (float) getField(curBlockDamageMP);
    }
}
