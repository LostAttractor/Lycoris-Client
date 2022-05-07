package rbq.wtf.lycoris.client.wrapper;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {

    public static MethodHandles.Lookup lookup;

    public static void setField(final String string, final String obfName, final Object instance, final Object newValue, final boolean isFinal){
        final Field strField = ReflectionHelper.findField((Class)instance.getClass(), new String[] { string, obfName });
        strField.setAccessible(true);
        try {
            if (isFinal) {
                final Field modField = Field.class.getDeclaredField("modifiers");
                modField.setAccessible(true);
                modField.setInt(strField, strField.getModifiers() & 0xFFFFFFEF);
            }
            strField.set(instance, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setField(final String string, final String obfName, final Class targetClass, final Object instance, final Object newValue, final boolean isFinal) {
        final Field strField = ReflectionHelper.findField(targetClass, new String[] { string, obfName });
        strField.setAccessible(true);
        try {
            if (isFinal) {
                final Field modField = Field.class.getDeclaredField("modifiers");
                modField.setAccessible(true);
                modField.setInt(strField, strField.getModifiers() & 0xFFFFFFEF);
            }
            strField.set(instance, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setField(final Class targetClass, final Object instance, final Object newValue, final boolean isFinal,String... arrstring) {
        final Field strField = ReflectionHelper.findField(targetClass, arrstring);
        strField.setAccessible(true);
        try {
            if (isFinal) {
                final Field modField = Field.class.getDeclaredField("modifiers");
                modField.setAccessible(true);
                modField.setInt(strField, strField.getModifiers() & 0xFFFFFFEF);
            }
            strField.set(instance, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getField(final String field, final String obfName, final Object instance) {
        final Field fField = ReflectionHelper.findField((Class)instance.getClass(),
                isNotObfuscated()? new String[] { obfName }: new String[] { field }

        );
        fField.setAccessible(true);
        try {
            return fField.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getField(final String field, final String obfName, final Class clazz) {
        final Field fField = ReflectionHelper.findField(clazz, new String[] { field, obfName });
        fField.setAccessible(true);
        try {
            return fField.get(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getField(Class class_, String... arrstring) {
        for (Field field : class_.getDeclaredFields()) {
            field.setAccessible(true);
            for (String string : arrstring) {
                if (!field.getName().equals(string)) {
                    continue;
                }
                return field;
            }
        }
        return null;
    }/*
    public static Object invoke(final Object target, final String methodName, final String obfName, final Class[] methodArgs, final Object[] args) {
        final Class clazz = target.getClass();
        final Method method = ReflectionHelper.findMethod(clazz, //target,
                methodName ,obfName , target.getClass());
        //, methodArgs);
        method.setAccessible(true);
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
    public static Object invoke(final Object target, final String methodName, final String obfName, final Class[] methodArgs, final Object[] args) {
        final Class<?> clazz = target.getClass();
        try {
            final Method method = target.getClass().getDeclaredMethod(isNotObfuscated()? obfName :methodName ,methodArgs);// target,

            //, methodArgs,target.getClass());
            method.setAccessible(true);

            return method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNotObfuscated() {
        try { return (Minecraft.class.getDeclaredField("instance") != null);
        } catch (Exception ex) { return false; }
    }

    public static MethodHandles.Lookup lookup() {
        return lookup;
    }
}
