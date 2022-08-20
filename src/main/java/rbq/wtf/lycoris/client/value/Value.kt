package rbq.wtf.lycoris.client.value

import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.utils.Logger
import java.awt.Color

abstract class Value<T>(val name: String, protected var value: T, module: Module) {

    init {
        fun module() = this
        module.addValue(module());
    }

    fun get() = value
    fun set(newValue: T) {
        if (newValue == value)
            return

        val oldValue = get()

        try {
            onChange(oldValue, newValue)
            changeValue(newValue)
            onChanged(oldValue, newValue)
            //TODO 保存配置文件
        } catch (e: Exception) {
            Logger.error("${e.javaClass.name} (${e.message}) [$oldValue >> $newValue]", "ValueSystem ($name)")
        }
    }

    open fun changeValue(value: T) {
        this.value = value
    }

    protected open fun onChange(oldValue: T, newValue: T) {}
    protected open fun onChanged(oldValue: T, newValue: T) {}
}

open class BooleanValue(name: String, value: Boolean, module: Module) : Value<Boolean>(name, value, module)

open class NumberValue(
    name: String,
    value: Float,
    val minimum: Float,
    val maximum: Float,
    val increase: Float,
    module: Module
) : Value<Float>(name, value, module)

open class ModeValue(name: String, val modes: Array<String>, selection: Int, module: Module) :
    Value<Int>(name, selection, module) {
    var modeName: String = modes[selection]

    override fun onChanged(oldValue: Int, newValue: Int) {
        modeName = modes[newValue]
    }

    fun incrementSelection() {
        set(if (get() + 1 < modes.size) get() + 1 else 0)
    }
}

open class TextValue(name: String, value: String, module: Module) : Value<String>(name, value, module)

open class ColorValue(name: String, value: Color, module: Module) : Value<Color>(name, value, module) {
    fun getHSB(): FloatArray {
        val hsbValues = FloatArray(3)
        val saturation: Float
        val brightness: Float
        var hue: Float
        var cMax = (value.rgb ushr 16 and 0xFF).coerceAtLeast(value.rgb ushr 8 and 0xFF)
        if (value.rgb and 0xFF > cMax) cMax = value.rgb and 0xFF
        var cMin = (value.rgb ushr 16 and 0xFF).coerceAtMost(value.rgb ushr 8 and 0xFF)
        if (value.rgb and 0xFF < cMin) cMin = value.rgb and 0xFF
        brightness = cMax.toFloat() / 255.0f
        saturation = if (cMax != 0) (cMax - cMin).toFloat() / cMax.toFloat() else 0F
        if (saturation == 0f) {
            hue = 0f
        } else {
            val redC = (cMax - (value.rgb ushr 16 and 0xFF)).toFloat() / (cMax - cMin).toFloat()
            // @off
            val greenC = (cMax - (value.rgb ushr 8 and 0xFF)).toFloat() / (cMax - cMin).toFloat()
            val blueC = (cMax - (value.rgb and 0xFF)).toFloat() / (cMax - cMin).toFloat() // @on
            hue =
                (if (value.rgb ushr 16 and 0xFF == cMax) blueC - greenC else if (value.rgb ushr 8 and 0xFF == cMax) 2.0f + redC - blueC else 4.0f + greenC - redC) / 6.0f
            if (hue < 0) hue += 1.0f
        }
        hsbValues[0] = hue
        hsbValues[1] = saturation
        hsbValues[2] = brightness
        return hsbValues
    }
}