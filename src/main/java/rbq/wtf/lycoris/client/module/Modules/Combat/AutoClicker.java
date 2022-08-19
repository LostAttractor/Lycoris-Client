package rbq.wtf.lycoris.client.module.Modules.Combat;

import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.BooleanValue;
import rbq.wtf.lycoris.client.value.NumberValue;

public class AutoClicker extends Module {
    public final NumberValue maxCPS = new NumberValue("Max CPS", 8.0F, 1.0F, 20.0F, 0.5F, this);
    public final NumberValue minCPS = new NumberValue("Min CPS", 8.0F, 1.0F, 20.0F, 0.5F, this);
    public final BooleanValue Left = new BooleanValue("Left", true, this);
    public final BooleanValue Right = new BooleanValue("Right", true, this);
    public final BooleanValue Jitter = new BooleanValue("Jitter", false, this);

    public AutoClicker() {
        super("AutoClicker", ModuleCategory.Combat, 0);
    }
}
