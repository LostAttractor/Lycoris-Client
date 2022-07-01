package rbq.wtf.lycoris.client.manager;

import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.module.ModuleCategory;
import rbq.wtf.lycoris.client.module.Modules.Movement.KeepSprint;
import rbq.wtf.lycoris.client.module.Modules.Render.ClickGUI;
import rbq.wtf.lycoris.client.module.Modules.Render.HUD;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager(){
        modules.add(new KeepSprint());
        modules.add(new HUD());
        modules.add(new ClickGUI());
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
