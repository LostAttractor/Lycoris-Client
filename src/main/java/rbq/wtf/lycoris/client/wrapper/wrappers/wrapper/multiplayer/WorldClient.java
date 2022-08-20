package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.multiplayer;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.EntityPlayer;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.world.World;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient", targetMap = MapEnum.VANILLA189)
public class WorldClient extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.multiplayer.WorldClient", targetMap = MapEnum.VANILLA189)
    public static Class<?> worldClientClass;

    public WorldClient(Object obj) {
        super(obj);
    }

    public World getWorldInstance() {
        return new World(getWrapObject());
    }
}