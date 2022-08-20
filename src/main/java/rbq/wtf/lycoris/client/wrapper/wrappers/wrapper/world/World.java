package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.world;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block.state.IBlockState;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.util.BlockPos;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.world.World", targetMap = MapEnum.VANILLA189)
public class World extends IWrapper {
    @WrapMethod(mcpName = "getBlockState", targetMap = MapEnum.VANILLA189)
    public static Method getBlockState;

    public World(Object obj) {
        super(obj);
    }

    public IBlockState getBlockState(BlockPos p_getBlockState_1_) {
        return new IBlockState(invoke(getBlockState, p_getBlockState_1_.getWrapObject()));
    }
}
