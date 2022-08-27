package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapObject
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass

@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetMap = MapEnum.VANILLA189)
class DefaultVertexFormats(obj: Any) : IWrapper(obj) {
    companion object {
        @WrapObject(mcpName = "POSITION_TEX_COLOR", targetMap = MapEnum.VANILLA189)
        lateinit var POSITION_TEX_COLOR: Any

        @WrapObject(mcpName = "POSITION", targetMap = MapEnum.VANILLA189)
        lateinit var POSITION: Any

        @WrapObject(mcpName = "POSITION_COLOR", targetMap = MapEnum.VANILLA189)
        lateinit var POSITION_COLOR: Any

        @JvmStatic
        fun POSITION(): VertexFormat {
            return VertexFormat(POSITION)
        }

        @JvmStatic
        fun POSITION_COLOR(): VertexFormat {
            return VertexFormat(POSITION_COLOR)
        }

        @JvmStatic
        fun POSITION_TEX_COLOR(): VertexFormat {
            return VertexFormat(POSITION_COLOR)
        }
    }
}