package rbq.wtf.lycoris.client.value;


import rbq.wtf.lycoris.client.module.Module;

public abstract class Value<T> {
    public abstract Module getModule();

    public abstract void setModule(Module module);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract T getValue();

    public abstract void setValue(T value);

}
