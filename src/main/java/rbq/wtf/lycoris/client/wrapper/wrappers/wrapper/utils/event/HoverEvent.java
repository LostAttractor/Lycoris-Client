package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.event.HoverEvent",targetMap = MapEnum.VANILLA189)
public class HoverEvent extends IWrapper {
    public HoverEvent(Object obj) {
        super(obj);
    }
}