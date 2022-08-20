package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.block;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.block.Block", targetMap = MapEnum.VANILLA189)
public class Block extends IWrapper {
    public Block(Object obj) {
        super(obj);
    }
}
