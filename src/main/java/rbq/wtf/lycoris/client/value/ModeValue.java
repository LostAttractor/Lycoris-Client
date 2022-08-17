package rbq.wtf.lycoris.client.value;

import rbq.wtf.lycoris.client.module.Module;

public class ModeValue extends Value<String> {
    private int selection;
    private int selectionAmount;
    private String[] modes;
    private String name;
    private Module module;

    public ModeValue(String Name, String[] Modes, int Selection, int Amount) {
        this.name = Name;
        this.modes = Modes;
        this.selection = Selection;
        this.selectionAmount = Amount;
        this.module = null;
    }

    public ModeValue(String Name, String[] Modes, int Selection, int Amount, Module m) {
        this.name = Name;
        this.modes = Modes;
        this.selection = Selection;
        this.selectionAmount = Amount;
        this.module = m;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public Module getModule() {
        return this.module;
    }

    @Override
    public String getValue() {
        return this.modes[selection];
    }

    @Override
    public void setValue(String s) {
        if (getCurrentSelectionName().equalsIgnoreCase(s)) {
            return;
        }
        setCurrentSelectionName(s);
    }

    public void setModes(String[] modes) {
        this.modes = modes;
    }

    public String[] getModes() {
        return modes;
    }

    public void setCurrentSelectionName(String nm) {
        int i = 0;
        for (String mode : this.modes) {
            if (mode.equalsIgnoreCase(nm)) {
                setSelection(i);
                break;
            }
        }

    }

    public void setState(String s) {
        for (int i = 0; i < this.modes.length - 1; i++) {
            if (this.modes[i].equalsIgnoreCase(s)) {
                setSelection(i);
                break;
            }
        }
    }

    public void setValueState(String s) {
        for (int i = 0; i < this.modes.length - 1; i++) {
            if (this.modes[i].equalsIgnoreCase(s)) {
                setSelection(i);
                break;
            }
        }
    }

    public void incrementSelection() {
        this.selection = this.selection < this.selectionAmount ? ++this.selection : 0;
    }

    public void setSelectionAmount(int amount) {
        this.selectionAmount = amount;
    }

    public void setSelection(int index) {
        this.selection = index;
    }

    public String getCurrentSelectionName() {
        return this.modes[this.selection];
    }

    public int getSelectionAmount() {
        return this.selectionAmount;
    }

}
