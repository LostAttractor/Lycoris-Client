package rbq.wtf.lycoris.client.value;

import rbq.wtf.lycoris.client.module.Module;

public class NumberValue extends Value<Float> {
    private String name;
    private float value;
    private float increase;
    private float min;
    private float max;
    private Module module;

    public NumberValue(String Name, float Value, float Min, float Max, float Increase, Module module) {
        this.name = Name;
        this.value = Value;
        this.min = Min;
        this.max = Max;
        this.increase = Increase;
        this.module = module;
        module.addValue(this);
    }

    @Override
    public Module getModule() {
        return this.module;
    }

    @Override
    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public void setValue(Float value) {
        this.value = value;
    }

    public float getIncrease() {
        return increase;
    }

    public void setIncrease(float increase) {
        this.increase = increase;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

}
