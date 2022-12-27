package rbq.lycoris.client.wrapper.wrappers.render

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat", targetMap = MapEnum.VANILLA189)
class VertexFormat(obj: Any) : IWrapper(obj)