package rbq.wtf.lycoris.client.value

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.utils.Logger
import java.awt.Color
import java.util.*

abstract class Value<T>(val name: String, protected var value: T) {

    var isSupported = true

    fun get() = value
    fun set(newValue: T) {
        if (newValue == value)
            return

        val oldValue = get()

        try {
            if (canChange(oldValue, newValue)) {
                onChange(oldValue, newValue)
                changeValue(newValue)
                onChanged(oldValue, newValue)
                Client.configManager.saveConfig(Client.configManager.valuesConfig)
            }
        } catch (e: Exception) {
            Logger.error("${e.javaClass.name} (${e.message}) [$oldValue >> $newValue]", "ValueSystem ($name)")
        }
    }

    open fun changeValue(value: T) {
        this.value = value
    }

    abstract fun toJson(): JsonElement?
    abstract fun fromJson(element: JsonElement)

    protected open fun canChange(oldValue: T, newValue: T): Boolean {
        return true
    }

    protected open fun onChange(oldValue: T, newValue: T) {}
    protected open fun onChanged(oldValue: T, newValue: T) {}
}

/**
 * Bool value represents a value with a boolean
 */
open class BooleanValue(name: String, value: Boolean) : Value<Boolean>(name, value) {

    override fun toJson() = JsonPrimitive(value)

    override fun fromJson(element: JsonElement) {
        if (element.isJsonPrimitive)
            value = element.asBoolean || element.asString.equals("true", ignoreCase = true)
    }

}

/**
 * Float value represents a value with a float
 */
open class NumberValue(
    name: String,
    value: Float,
    val minimum: Float,
    val maximum: Float,
    val increase: Float
) : Value<Float>(name, value) {
    override fun toJson() = JsonPrimitive(value)

    override fun fromJson(element: JsonElement) {
        if (element.isJsonPrimitive)
            value = element.asFloat
    }

    override fun canChange(oldValue: Float, newValue: Float): Boolean {
        return if (value in minimum..maximum) {
            true
        } else {
            Logger.error("Value out of bounds: ${value} in ${minimum}..${maximum}")
            false
        }
    }
}


open class ModeValue(name: String, private val modes: Array<String>, selection: Int) :
    Value<Int>(name, selection) {
    val modeName: String
        get() = modes[value]

    operator fun contains(string: String?): Boolean {
        return Arrays.stream(modes).anyMatch { s: String -> s.equals(string, ignoreCase = true) }
    }

    override fun toJson() = JsonPrimitive(modeName)

    override fun fromJson(element: JsonElement) {
        if (element.isJsonPrimitive) changeValue(modes.indexOf(element.asString))
    }

    override fun canChange(oldValue: Int, newValue: Int): Boolean {
        return if (value in modes.indices) {
            true
        } else {
            Logger.error("Value out of bounds: $value in ${modes.indices}")
            false
        }
    }

    fun incrementSelection() {
        set(if (get() + 1 in modes.indices) get() + 1 else 0)
    }
}

open class TextValue(name: String, value: String) : Value<String>(name, value) {
    override fun toJson() = JsonPrimitive(value)

    override fun fromJson(element: JsonElement) {
        if (element.isJsonPrimitive) value = element.asString
    }
}

open class ColorValue(name: String, value: Color) : Value<Color>(name, value) {

    override fun toJson() = JsonPrimitive(value.rgb)

    override fun fromJson(element: JsonElement) {
        if (element.isJsonPrimitive) value = Color(element.asInt)
    }

    fun getHSB(): FloatArray {
        return Color.RGBtoHSB(value.red, value.green, value.blue, null)
    }
}