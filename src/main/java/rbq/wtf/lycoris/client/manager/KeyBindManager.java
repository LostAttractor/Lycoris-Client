package rbq.wtf.lycoris.client.manager;

import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.EventTarget;
import rbq.wtf.lycoris.client.event.events.EventKey;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;

public class KeyBindManager {
    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final Client client = Client.instance;

    public KeyBindManager() {
        EventManager.register(this);
    }

    @EventTarget
    public void EventKeyPress(EventKey e) {
        for (Module module : client.moduleManager.getModules()) {
//            Logger.log("Key Pressed: " + e.getKey(), "Key");
            if (module.getKey() == e.getKey()) {
                module.toggle();
            }
        }
    }
}
