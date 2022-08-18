package rbq.wtf.lycoris.client.module;


import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.value.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

import java.util.ArrayList;

public class Module {
    public final ModuleCategory category;
    public final String name;

    public boolean state;
    public int key;

    private final ArrayList<BooleanValue> booleanValues = new ArrayList<>();
    private final ArrayList<NumberValue> numberValues = new ArrayList<>();
    private final ArrayList<ModeValue> modeValues = new ArrayList<>();
    private final ArrayList<ColorValue> colorValues = new ArrayList<>();
    private final ArrayList<TextValue> textValues = new ArrayList<>();

    protected final Client client = Client.instance;
    protected final Minecraft mc = client.mc;

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

    public ArrayList<Value> getValues() {
        ArrayList<Value> Values = new ArrayList<>();
        Values.addAll(booleanValues);
        Values.addAll(modeValues);
        Values.addAll(numberValues);
        Values.addAll(colorValues);
        Values.addAll(textValues);
        return Values;
    }


    public ArrayList<BooleanValue> getBooleanValues() {
        return booleanValues;
    }

    public ArrayList<ModeValue> getModeValues() {
        return modeValues;
    }

    public ArrayList<NumberValue> getNumberValues() {
        return numberValues;
    }

    public ArrayList<TextValue> getTextValues() {
        return textValues;
    }

    public ArrayList<ColorValue> getColorValues() {
        return colorValues;
    }

    public void addNumberValue(NumberValue value) {
        value.setModule(this);
        this.numberValues.add(value);
    }

    public void addModeValue(ModeValue value) {
        value.setModule(this);
        this.modeValues.add(value);
    }

    public void addBooleanValue(BooleanValue value) {
        value.setModule(this);
        this.booleanValues.add(value);
    }

    public void addColorValue(ColorValue value) {
        value.setModule(this);
        this.colorValues.add(value);
    }

    public void addTextValue(TextValue value) {
        value.setModule(this);
        this.textValues.add(value);
    }

    public boolean isState() {
        return state;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public void toggle() {
        setState(!state);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
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

    public String getName() {
        return name;
    }
}
