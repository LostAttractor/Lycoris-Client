package rbq.wtf.lycoris.client.module.Modules.World;

import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.ModeValue;

public class TestModule2 extends Module {
    public static ModeValue mode;

    public TestModule2() {
        super("TestModule2", ModuleCategory.World,0);
        mode = new ModeValue("Mode", new String[]{"Base", "ArmorColor", "NameColor"}, 0, 2);
        this.addModeValue(mode);
    }
}
