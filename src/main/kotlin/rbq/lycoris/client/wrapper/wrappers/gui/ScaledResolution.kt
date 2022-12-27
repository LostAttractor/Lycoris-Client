package rbq.lycoris.client.wrapper.wrappers.gui

import rbq.lycoris.client.wrapper.IWrapper
import rbq.lycoris.client.wrapper.MapEnum
import rbq.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.lycoris.client.wrapper.annotation.WrapField
import rbq.lycoris.client.wrapper.annotation.WrapperClass
import rbq.lycoris.client.wrapper.utils.ReflectUtil
import rbq.lycoris.client.wrapper.wrappers.Minecraft
import java.lang.reflect.Field

@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution", targetMap = MapEnum.VANILLA189)
class ScaledResolution(obj: Any) : IWrapper(obj) {
    constructor(mc: Minecraft) : this(ReflectUtil.construction(wrapClass, mc.wrapObject))

    val scaledWidth: Int
        get() = getField(Companion.scaledWidth) as Int
    val scaledHeight: Int
        get() = getField(Companion.scaledHeight) as Int
    val scaledWidth_double: Double
        get() = getField(scaledWidthD) as Double
    val scaledHeight_double: Double
        get() = getField(scaledHeightD) as Double
    val scaleFactor: Int
        get() = getField(Companion.scaleFactor) as Int

    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        @WrapField(mcpName = "scaledWidthD", targetMap = MapEnum.VANILLA189)
        lateinit var scaledWidthD: Field

        @WrapField(mcpName = "scaledHeightD", targetMap = MapEnum.VANILLA189)
        lateinit var scaledHeightD: Field

        @WrapField(mcpName = "scaledWidth", targetMap = MapEnum.VANILLA189)
        lateinit var scaledWidth: Field

        @WrapField(mcpName = "scaledHeight", targetMap = MapEnum.VANILLA189)
        lateinit var scaledHeight: Field

        @WrapField(mcpName = "scaleFactor", targetMap = MapEnum.VANILLA189)
        lateinit var scaleFactor: Field
    }
}