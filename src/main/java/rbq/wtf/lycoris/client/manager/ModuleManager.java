package rbq.wtf.lycoris.client.manager;

import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.module.Modules.Combat.*;
import rbq.wtf.lycoris.client.module.Modules.Movement.*;
import rbq.wtf.lycoris.client.module.Modules.Player.ChestStealer;
import rbq.wtf.lycoris.client.module.Modules.Player.NoFall;
import rbq.wtf.lycoris.client.module.Modules.Player.Timer;
import rbq.wtf.lycoris.client.module.Modules.Render.*;
import rbq.wtf.lycoris.client.module.Modules.World.Scaffold;
import rbq.wtf.lycoris.client.module.Modules.World.Teams;
import rbq.wtf.lycoris.client.module.Modules.World.TestModule1;
import rbq.wtf.lycoris.client.module.Modules.World.TestModule2;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager(){

        modules.add(new HUD());
        modules.add(new ClickGUI());
        modules.add(new ESP());
        modules.add(new NameTags());
        modules.add(new FutaESP());

        modules.add(new AntiBot());
        modules.add(new TpAura());
        modules.add(new Aimbot());
        modules.add(new RageBot());
        modules.add(new Teams());
        modules.add(new Hitbox());
        modules.add(new KillAura());
        modules.add(new Reach());
        modules.add(new Criticals());

        modules.add(new Scaffold());

        modules.add(new Fly());
        modules.add(new KeepSprint());
        modules.add(new FreeCam());

        modules.add(new Speed());
        modules.add(new NoFall());


        modules.add(new ChestStealer());
        modules.add(new Timer());
        for (Module m : modules) {
            System.out.println(m.Name);
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls) continue;
            return m;
        }
        return null;
    }

    public Module getModuleByName(String name) {
        for (Module m : modules) {
            if (!m.getName().equalsIgnoreCase(name)) continue;
            return m;
        }
        return null;
    }

    public List<Module> getModulesInType(ModuleCategory t) {
        ArrayList<Module> output = new ArrayList<Module>();
        for (Module m : modules) {
            if (m.getCategory() != t)
                continue;
            output.add(m);
        }
        return output;
    }

}
