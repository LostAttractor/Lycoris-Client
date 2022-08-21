package rbq.wtf.lycoris.client.wrapper.wrappers.utils;

import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectUtil {
    private static Object getField(Object targetObject, Field targetField) {
        try {
            targetObject = targetObject instanceof IWrapper ?
                    ((IWrapper) targetObject).getWrapObject() : targetObject;
            return targetField.get(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldStatic(Field targetField) { // For static Field
        try {
            return targetField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void setField(Object targetObject, Field targetField, Object value) {
        try {
            targetObject = targetObject instanceof IWrapper ?
                    ((IWrapper) targetObject).getWrapObject() : targetObject;
            value = value instanceof IWrapper ?
                    ((IWrapper) value).getWrapObject() : value;
            targetField.set(targetObject, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object invoke(Object targetObject, Method targetMethod, Object... args) {
        try {
            targetObject = targetObject instanceof IWrapper ?
                    ((IWrapper) targetObject).getWrapObject() : targetObject;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof IWrapper)
                    args[i] = ((IWrapper) args[i]).getWrapObject();
            }
            return targetMethod.invoke(targetObject, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeStatic(Method targetMethod, Object... args) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof IWrapper)
                    args[i] = ((IWrapper) args[i]).getWrapObject();
            }
            return targetMethod.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static Object construction(Class<?> classIn, Object... args) {
        try {
            Class<?>[] arg = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                arg[i] = args[i].getClass();
            }
            Constructor<?> constructor = classIn.getConstructor(arg);
            return constructor.newInstance(args);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object construction(Constructor<?> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
