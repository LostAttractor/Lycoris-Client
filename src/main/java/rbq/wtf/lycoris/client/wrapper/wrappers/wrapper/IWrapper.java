package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class IWrapper {
    private final Object object;

    public IWrapper(Object obj) {
        this.object = obj;
    }

    public Object getWrapObject() {
        return object;
    }

    protected Object getField(Field targetField) {
        try {
            return targetField.get(getWrapObject());
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("Can't get Field: " + e.getMessage());
        }
    }

    protected void setField(Field targetField, Object value) {
        try {
            value = value instanceof IWrapper ?
                    ((IWrapper) value).getWrapObject() : value;
            targetField.set(getWrapObject(), value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Object invoke(Method targetMethod, Object... args) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof IWrapper)
                    args[i] = ((IWrapper) args[i]).getWrapObject();
            }
            return targetMethod.invoke(getWrapObject(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("Can't get Invoke Method: " + e.getMessage());
        }
    }

    public boolean isNull() {
        return Objects.isNull(object);
    }

    public boolean equals(IWrapper wrapper) {
        return Objects.equals(object, wrapper.object);
    }
}
