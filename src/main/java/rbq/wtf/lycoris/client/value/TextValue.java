package rbq.wtf.lycoris.client.value;

import rbq.wtf.lycoris.client.module.Module;

public class TextValue extends Value<String> {
    private String name;
    private Module module;
    private String value;

    public TextValue(String name, String value, Module module) {
        this.name = name;
        this.module = module;
        this.value = value;
        module.addValue(this);
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
    public Module getModule() {
        return module;
    }

    @Override
    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
