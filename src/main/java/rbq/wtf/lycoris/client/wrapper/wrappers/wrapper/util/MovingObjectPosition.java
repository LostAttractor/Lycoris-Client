package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.MovingObjectPosition", targetMap = MapEnum.VANILLA189)
public class MovingObjectPosition extends IWrapper {

    @WrapField(mcpName = "blockPos", targetMap = MapEnum.VANILLA189)
    public static Field blockPos;

    public MovingObjectPosition(Object obj) {
        super(obj);
    }

    public BlockPos getBlockPos() {
        return new BlockPos(getField(blockPos));
    }
}
