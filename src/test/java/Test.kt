abstract class Value<T> {
    var value: T? = null
    fun get(): T? {
        return value
    }

    fun set(value: T) {
        this.value = value
    }
}

class NumberValue<T : Number?>(a: Float?) : Value<T>()
class BoolValue(a: Boolean?) : Value<Boolean?>()
class Test {
    var a = NumberValue<Float>(1f)
    var valueList: List<Value<*>> = ArrayList()
    val numberValue: List<BoolValue>
        get() {
            val numberValues: MutableList<BoolValue> = ArrayList()
            for (value in valueList) {
                if (value is BoolValue) numberValues.add(value)
            }
            return numberValues
        }
}