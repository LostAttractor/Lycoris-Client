package rbq.wtf.lycoris.client.module.Modules.World;

import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.value.ModeValue;

public class Teams extends Module {
    public static ModeValue mode;

    public Teams() {
        super("Teams", ModuleCategory.World,0);
        mode = new ModeValue("Mode", new String[]{"Base", "ArmorColor", "NameColor"}, 0, 2);
        this.addModeValue(mode);
    }
}
