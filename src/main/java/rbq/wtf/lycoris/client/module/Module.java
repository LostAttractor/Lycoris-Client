package rbq.wtf.lycoris.client.module;


import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.value.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Module {
    public final ModuleCategory category;
    public final String name;
    protected final Client client = Client.instance;
    protected final Minecraft mc = client.mc;
    private final List<BooleanValue> booleanValues = new ArrayList<>();
    private final List<NumberValue> numberValues = new ArrayList<>();
    private final List<ModeValue> modeValues = new ArrayList<>();
    private final List<ColorValue> colorValues = new ArrayList<>();
    private final List<TextValue> textValues = new ArrayList<>();
    public boolean state;
    public int key;

    public Module(String Name, ModuleCategory Category, int Key) {
        this.category = Category;
        this.name = Name;
        // this.state = false;
        this.key = Key;
    }


    public void onEnable() {

    }

    public void onDisable() {

    }

    public List<Value<?>> getValues() {
        List<Value<?>> Values = new ArrayList<>();
        Values.addAll(booleanValues);
        Values.addAll(modeValues);
        Values.addAll(numberValues);
        Values.addAll(colorValues);
        Values.addAll(textValues);
        return Values;
    }


    public List<BooleanValue> getBooleanValues() {
        return booleanValues;
    }

    public List<ModeValue> getModeValues() {
        return modeValues;
    }

    public List<NumberValue> getNumberValues() {
        return numberValues;
    }

    public List<TextValue> getTextValues() {
        return textValues;
    }

    public List<ColorValue> getColorValues() {
        return colorValues;
    }

    public void addValue(Value<?> value) {
        if (value instanceof NumberValue)
            this.numberValues.add((NumberValue) value);
        else if (value instanceof ModeValue)
            this.modeValues.add((ModeValue) value);
        else if (value instanceof BooleanValue)
            this.booleanValues.add((BooleanValue) value);
        else if (value instanceof ColorValue)
            this.colorValues.add((ColorValue) value);
        else if (value instanceof TextValue)
            this.textValues.add((TextValue) value);
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if (state) {
            this.onEnable();
            EventManager.register(this);
        } else {
            this.onDisable();
            EventManager.unregister(this);
        }
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public void toggle() {
        setState(!state);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }
}
