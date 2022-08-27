package rbq.wtf.lycoris.client.wrapper.wrappers.multiplayer

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.world.World

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient", targetMap = MapEnum.VANILLA189)
class WorldClient(obj: Any) : World(obj)