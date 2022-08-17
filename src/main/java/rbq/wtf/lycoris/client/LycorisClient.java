package rbq.wtf.lycoris.client;


import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.manager.CommandManager;
import rbq.wtf.lycoris.client.manager.ConfigManager;
import rbq.wtf.lycoris.client.manager.ModuleManager;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.transformer.TransformManager;
import rbq.wtf.lycoris.client.utils.Logger;
import rbq.wtf.lycoris.client.wrapper.Wrapper;
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil;


public class LycorisClient {
    public static LycorisClient instance;

    public static boolean isVanilla = true;
    public static String game_version = "1.8.9";

    public final ModuleManager moduleManager;
    public final ConfigManager configManager;
    public final CommandManager commandManager;

    public ClickGUI clickGUI;

    public LycorisClient() {
        instance = this;
        Logger.log("Start Initialize Client");
        Wrapper.init();
        BridgeUtil.init();
        InstrumentationImpl.init();
        TransformManager.init();
        EventManager.register(this);
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        commandManager = new CommandManager();
        clickGUI = new ClickGUI();
        Logger.log("Client Initialized Successful");
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ClickGUI getClickGUI() {
        return clickGUI;
    }

    @EventTarget
    public void EventKeyPress(EventKey e) {
        for (Module module : this.moduleManager.getModules()) {
//            Logger.log("Key Pressed: " + e.getKey(), "Key");
            if (module.getKey() == e.getKey()) {
                module.toggle();
            }
        }
    }
}
