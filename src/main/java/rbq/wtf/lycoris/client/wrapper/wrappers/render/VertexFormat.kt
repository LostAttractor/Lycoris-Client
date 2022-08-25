package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.IWrapper

@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.VertexFormat", targetMap = MapEnum.VANILLA189)
class VertexFormat(obj: Any) : IWrapper(obj)