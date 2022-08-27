package rbq.wtf.lycoris.client.wrapper.wrappers.render

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.Entity
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.renderer.EntityRenderer", targetMap = MapEnum.VANILLA189)
class EntityRenderer(obj: Any) : IWrapper(obj) {

    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapMethod(mcpName = "renderWorldPass", targetMap = MapEnum.VANILLA189)
        lateinit var renderWorldPass: Method

        @WrapMethod(mcpName = "getMouseOver", targetMap = MapEnum.VANILLA189)
        lateinit var getMouseOver: Method

        @WrapField(mcpName = "pointedEntity", targetMap = MapEnum.VANILLA189)
        lateinit var pointedEntity: Field

        @WrapMethod(mcpName = "disableLightmap", targetMap = MapEnum.VANILLA189)
        lateinit var disableLightmap: Method

        @WrapMethod(mcpName = "enableLightmap", targetMap = MapEnum.VANILLA189)
        lateinit var enableLightmap: Method

        @WrapMethod(mcpName = "setupCameraTransform", targetMap = MapEnum.VANILLA189)
        lateinit var setupCameraTransform: Method

        @WrapField(mcpName = "mc", targetMap = MapEnum.VANILLA189)
        lateinit var mc: Field
    }

    fun enableLightmap() {
        invoke(enableLightmap)
    }

    fun disableLightmap() {
        invoke(disableLightmap)
    }

    fun setupCameraTransform(partialTicks: Float, pass: Int) {
        invoke(setupCameraTransform, partialTicks, pass)
    }

    var pointedEntity: Entity?
        get() = getField(Companion.pointedEntity)?.let { Entity(it) }
        set(entity) {
            setField(Companion.pointedEntity, entity?.wrapObject)
        }
}