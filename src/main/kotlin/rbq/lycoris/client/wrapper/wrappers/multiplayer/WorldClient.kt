package rbq.lycoris.client.wrapper.wrappers.multiplayer

import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.wrappers.world.World

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient", targetMap = MapEnum.VANILLA189)
class WorldClient(obj: Any) : World(obj)