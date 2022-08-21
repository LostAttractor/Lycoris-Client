package rbq.wtf.lycoris.client.module;


import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.Listenable;
import rbq.wtf.lycoris.client.utils.MinecraftInstance;
import rbq.wtf.lycoris.client.value.*;

import java.util.ArrayList;
import java.util.List;

public class Module extends MinecraftInstance implements Listenable {
    public final ModuleCategory category;
    public final String name;
    private final List<Value<?>> values = new ArrayList<>();
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
        return values;
    }


    public List<BooleanValue> getBooleanValues() {
        List<BooleanValue> booleanValues = new ArrayList<>();
        for (Value<?> value : values) {
            if (value instanceof BooleanValue)
                booleanValues.add((BooleanValue) value);
        }
        return booleanValues;
    }

    public List<ModeValue> getModeValues() {
        List<ModeValue> modeValues = new ArrayList<>();
        for (Value<?> value : values) {
            if (value instanceof ModeValue)
                modeValues.add((ModeValue) value);
        }
        return modeValues;
    }

    public List<NumberValue> getNumberValues() {
        List<NumberValue> numberValues = new ArrayList<>();
        for (Value<?> value : values) {
            if (value instanceof NumberValue)
                numberValues.add((NumberValue) value);
        }
        return numberValues;
    }

    public List<TextValue> getTextValues() {
        List<TextValue> textValues = new ArrayList<>();
        for (Value<?> value : values) {
            if (value instanceof TextValue)
                textValues.add((TextValue) value);
        }
        return textValues;
    }

    public List<ColorValue> getColorValues() {
        List<ColorValue> colorValues = new ArrayList<>();
        for (Value<?> value : values) {
            if (value instanceof ColorValue)
                colorValues.add((ColorValue) value);
        }
        return colorValues;
    }

    public void addValue(Value<?> value) {
        values.add(value);
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if (state) {
            this.onEnable();
            Client.eventManager.registerListener(this);
        } else {
            this.onDisable();
            Client.eventManager.unregisterListener(this);
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

    @Override
    public boolean handleEvents() {
        return true;
    }
}
