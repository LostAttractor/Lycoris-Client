package rbq.wtf.lycoris.client.value;

import rbq.wtf.lycoris.client.module.Module;

public class BooleanValue extends Value<Boolean> {
    private Boolean value;
    private String name;
    private Module module;

    public BooleanValue(String name, boolean state, Module module) {
        this.name = name;
        this.value = state;
        this.module = module;
        module.addBooleanValue(this);
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

}
