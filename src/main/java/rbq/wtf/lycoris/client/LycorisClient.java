package rbq.wtf.lycoris.client;



import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.manager.ModuleManager;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.transformer.TransformManager;
import rbq.wtf.lycoris.client.wrapper.Wrapper;
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil;


public class LycorisClient {

    public static boolean debug = false;
    public static boolean isVanilla = true;

    public static String game_version = "1.8.9";
    public ModuleManager moduleManager;
    public ClickGUI clickGUI;
    public static LycorisClient instance;
    public LycorisClient(){
        debug = false;

        System.out.println("[Lycoris Client] Init Client");
        instance = this;
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();
        System.out.println("[Lycoris Client] Init Wrapper");
        Wrapper.initWrapper();
        BridgeUtil.init();
        System.out.println("[Lycoris Client] Do TransFormer");
        InstrumentationImpl.init();
        TransformManager.init();
        TransformManager.doTransform();
        EventManager.register(this);
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ClickGUI getClickGUI() {
        return clickGUI;
    }
    @EventTarget
    public void EventKeyPress(EventKey e) {
        for(Module module : this.moduleManager.getModules()){
            System.out.println("Key" + e.getKey());
            if(module.getKey() == e.getKey()){
                module.toggle();
            }
        }
    }
}
