package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper

@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat", targetMap = MapEnum.VANILLA189)
class VertexFormat(obj: Any) : IWrapper(obj)