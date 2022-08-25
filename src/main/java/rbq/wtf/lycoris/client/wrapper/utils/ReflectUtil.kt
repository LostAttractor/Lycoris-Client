package rbq.wtf.lycoris.client.wrapper.utils

import rbq.wtf.lycoris.client.wrapper.IWrapper
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

object ReflectUtil {
    private fun getField(targetObject: Any, targetField: Field): Any? {
        var target: Any = targetObject
        return try {
            target = if (target is IWrapper) target.wrapObject else target
            targetField[target]
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Field: " + e.message)
        }
    }

    fun getFieldStatic(targetField: Field): Any? { // For static Field
        return try {
            targetField[null]
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Static Field: " + e.message)
        }
    }

    private fun setField(targetObject: Any, targetField: Field, value: Any?) {
        var target: Any = targetObject
        var values: Any? = value
        try {
            target = if (target is IWrapper) target.wrapObject else target
            values = if (values is IWrapper) values.wrapObject else values
            targetField[target] = values
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setFieldStatic(targetField: Field, value: Any?) {
        var values: Any? = value
        try {
            values = if (values is IWrapper) values.wrapObject else values
            targetField[null] = values
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private operator fun invoke(targetObject: Any, targetMethod: Method, vararg args: Any?): Any? {
        var target: Any = targetObject
        val varargs: Array<Any?> = arrayOf(*args)
        return try {
            target = if (target is IWrapper) target.wrapObject else target
            for (i in varargs.indices) {
                if (varargs[i] is IWrapper) varargs[i] = (varargs[i] as IWrapper).wrapObject
            }
            targetMethod.invoke(target, *varargs)
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Invoke Method: " + e.message)
        }
    }

    fun invokeStatic(targetMethod: Method, vararg args: Any?): Any? {
        val varargs: Array<Any?> = arrayOf(*args)
        return try {
            for (i in varargs.indices) {
                if (varargs[i] is IWrapper) varargs[i] = (args[i] as IWrapper).wrapObject
            }
            targetMethod.invoke(null, *varargs)
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Invoke Static Method: " + e.message)
        }
    }

//    fun construction(classIn: Class<*>, vararg args: Any): Any? {
//        return try {
//            val arg: Array<Class<*>?> = arrayOfNulls(args.size)
//            for (i in args.indices) {
//                arg[i] = args[i].javaClass
//            }
//            val constructor = classIn.getConstructor(*arg)
//            constructor.newInstance(*args)
//        } catch (e: NoSuchMethodException) {
//            e.printStackTrace()
//            throw java.lang.NullPointerException("Can't get Construction Class: " + e.message)
//        } catch (e: InvocationTargetException) {
//            e.printStackTrace()
//            throw java.lang.NullPointerException("Can't get Construction Class: " + e.message)
//        } catch (e: InstantiationException) {
//            e.printStackTrace()
//            throw java.lang.NullPointerException("Can't get Construction Class: " + e.message)
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//            throw java.lang.NullPointerException("Can't get Construction Class: " + e.message)
//        }
//    }

    @Deprecated("")
    fun construction(classIn: Class<*>, vararg args: Any?): Any {
        return try {
            val varargs: Array<Class<*>?> = arrayOfNulls(args.size)
            for (i in args.indices) {
                varargs[i] = args[i]?.javaClass
            }
            val constructor = classIn.getConstructor(*varargs)
            constructor.newInstance(*args)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
            throw NullPointerException("Can't get Construction Class: " + e.message)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            throw NullPointerException("Can't get Construction Class: " + e.message)
        } catch (e: InstantiationException) {
            e.printStackTrace()
            throw NullPointerException("Can't get Construction Class: " + e.message)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            throw NullPointerException("Can't get Construction Class: " + e.message)
        }
    }

    fun construction(constructor: Constructor<*>, vararg args: Any?): Any {
        return try {
            constructor.newInstance(*args)
        } catch (e: Exception) {
            e.printStackTrace()
            throw NullPointerException("Can't get Construction Class: " + e.message)
        }
    }
}