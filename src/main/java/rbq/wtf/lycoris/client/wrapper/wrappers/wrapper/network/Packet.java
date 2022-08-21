package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
public class Packet extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.network.Packet", targetMap = MapEnum.VANILLA189)
    public static Class<?> PacketClass;

    public Packet(Object obj) {
        super(obj);
    }
}
