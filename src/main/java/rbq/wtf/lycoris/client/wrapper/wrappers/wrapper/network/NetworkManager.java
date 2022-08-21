package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapMethod;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.NetworkManager", targetMap = MapEnum.VANILLA189)
public class NetworkManager {
    @WrapClass(mcpName = "net.minecraft.network.NetworkManager", targetMap = MapEnum.VANILLA189)
    public static Class<?> NetworkManagerClass;

    @WrapMethod(mcpName = "sendPacket", targetMap = MapEnum.VANILLA189)
    public static Method sendPacket;

    @WrapMethod(mcpName = "channelRead0", targetMap = MapEnum.VANILLA189)
    public static Method channelRead0;
}
