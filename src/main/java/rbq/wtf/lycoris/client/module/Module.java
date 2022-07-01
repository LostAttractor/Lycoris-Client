package rbq.wtf.lycoris.client.module;


import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.ModeValue;
import rbq.wtf.lycoris.client.value.NumberValue;
import rbq.wtf.lycoris.client.value.Value;

import java.util.ArrayList;

public class Module {
    public boolean State;
    public ModuleCategory Category;
    public String Name;
    public int Key;
    private ArrayList<BooleanValue> BooleanValues;
    private ArrayList<NumberValue> NumberValues;
    private ArrayList<ModeValue> ModeValues;
    public Module(String Name, ModuleCategory Category, int Key) {
        this.BooleanValues = new ArrayList();
        this.NumberValues = new ArrayList();
        this.ModeValues = new ArrayList();
        this.Category = Category;
        this.Name = Name;
        this.State = false;
        this.Key = Key;
    }


    public void onEnable (){

    }

    public void onDisable (){

    }

    public ArrayList<Value> getValues() {
        ArrayList<Value> Values = new ArrayList<>();
        Values.addAll(BooleanValues);
        Values.addAll(ModeValues);
        Values.addAll(NumberValues);
        return Values;
    }



    public ArrayList<BooleanValue> getBooleanValues() {
        return BooleanValues;
    }

    public ArrayList<ModeValue> getModeValues() {
        return ModeValues;
    }

    public ArrayList<NumberValue> getNumberValues() {
        return NumberValues;
    }

    public void addNumberValue(NumberValue value) {
        value.setModule(this);
        this.NumberValues.add(value);
    }
    public void addModeValue(ModeValue value) {
        value.setModule(this);
        this.ModeValues.add(value);
    }
    public void addBooleanValue(BooleanValue value) {
        value.setModule(this);
        this.BooleanValues.add(value);
    }
    public boolean isState() {
        return State;
    }

    public ModuleCategory getCategory() {
        return Category;
    }

    public void toggle(){
        setState(!State);
    }

    public void setKey(int key) {
        Key = key;
    }

    public int getKey() {
        return Key;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setState(boolean state) {
        State = state;
        if (state) {
            this.onEnable();
            EventManager.register(this);
        } else {
            this.onDisable();
            EventManager.unregister(this);
        }
    }

    public String getName() {
        return Name;
    }
}
