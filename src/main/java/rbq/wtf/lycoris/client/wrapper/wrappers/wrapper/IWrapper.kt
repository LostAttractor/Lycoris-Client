package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper

import rbq.wtf.lycoris.client.utils.Logger
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

open class IWrapper(val wrapObject: Any) {

    protected fun getField(targetField: Field): Any? {
        return try {
            targetField.get(wrapObject)
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Field: " + e.message)
        }
    }

    protected fun setField(targetField: Field, value: Any?) {
        try {
            targetField[wrapObject] = if (value is IWrapper) value.wrapObject else value
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected operator fun invoke(targetMethod: Method, vararg args: Any?): Any? {
        return try {
            val varargs: Array<Any?> = arrayOf(*args)
            for (i in varargs.indices) {
                if (varargs[i] is IWrapper) varargs[i] = (varargs[i] as IWrapper).wrapObject
            }
            targetMethod.invoke(wrapObject, *varargs)
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Invoke Method: " + e.message)
        }
    }

    val isNull: Boolean
        get() = Objects.isNull(wrapObject)

    override fun equals(other: Any?): Boolean { //只会判断WrapObject
        return if (other is IWrapper) wrapObject == other.wrapObject else wrapObject == other
    }

    override fun hashCode(): Int {
        return wrapObject.hashCode()
    }
}