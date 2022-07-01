package rbq.wtf.lycoris.client;


import rbq.wtf.lycoris.agent.LycorisAgent;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGui;
import rbq.wtf.lycoris.client.manager.ModuleManager;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.transformer.MainTransformer;
import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.transformer.OBFMap;


public class LycorisClient {

    public static boolean debug = false;
    public static boolean isVanilla = true;

    public static String game_version = "1.8.9";
    public ModuleManager moduleManager;
    public ClickGui clickGUI;
    public static LycorisClient instance;
    public LycorisClient() {
        instance = this;
        moduleManager = new ModuleManager();
        clickGUI = new ClickGui();
        EventManager.register(this);
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ClickGui getClickGUI() {
        return clickGUI;
    }

    @EventTarget
    public void EventKeyPress(EventKey e) {
        for(Module module : this.moduleManager.getModules()){
            if(module.getKey() == e.getKey()){
                module.toggle();
            }
        }
    }
}
