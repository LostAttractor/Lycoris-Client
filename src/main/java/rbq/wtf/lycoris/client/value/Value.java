package rbq.wtf.lycoris.client.value;


import rbq.wtf.lycoris.client.module.Module;

public abstract class Value<T> {
    public abstract void setModule(Module module);
    public abstract Module getModule();

    public abstract void setName(String name);
    public abstract String getName();


    public abstract void setValue(T value);
    public abstract T getValue();

}
