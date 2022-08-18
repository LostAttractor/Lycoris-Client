package rbq.wtf.lycoris.client;


import rbq.wtf.lycoris.agent.instrument.impl.InstrumentationImpl;
import rbq.wtf.lycoris.client.gui.ClickGUI.ClickGUI;
import rbq.wtf.lycoris.client.manager.CommandManager;
import rbq.wtf.lycoris.client.manager.ConfigManager;
import rbq.wtf.lycoris.client.manager.KeyBindManager;
import rbq.wtf.lycoris.client.manager.ModuleManager;
import rbq.wtf.lycoris.client.transformer.TransformManager;
import rbq.wtf.lycoris.client.utils.Logger;
import rbq.wtf.lycoris.client.wrapper.Wrapper;
import rbq.wtf.lycoris.client.wrapper.bridge.BridgeUtil;


public class Client {
    public static Client instance;

    public static boolean isVanilla = true;
    public static String game_version = "1.8.9";
    public static final boolean enabledLog = true;
    public static final boolean showDebugLevelLog = true;

    public final ModuleManager moduleManager;
    public final ConfigManager configManager;
    public final CommandManager commandManager;
    public final KeyBindManager keyBindManager;

    public ClickGUI clickGUI;

    public Client() {
        instance = this;
        Logger.log("Start Initialize Client");
        Wrapper.init();
        BridgeUtil.init();
        InstrumentationImpl.init();
        TransformManager.init();
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        commandManager = new CommandManager();
        keyBindManager = new KeyBindManager();
        clickGUI = new ClickGUI();
        Logger.log("Client Initialized Successful");
    }
}
