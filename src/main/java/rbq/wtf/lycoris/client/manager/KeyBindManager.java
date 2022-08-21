package rbq.wtf.lycoris.client.manager;

import rbq.wtf.lycoris.client.Client;
import rbq.wtf.lycoris.client.event.EventTarget;
import rbq.wtf.lycoris.client.event.KeyEvent;
import rbq.wtf.lycoris.client.event.Listenable;
import rbq.wtf.lycoris.client.module.Module;
import rbq.wtf.lycoris.client.utils.Logger;

public class KeyBindManager implements Listenable {

    public KeyBindManager() {

    }

    @EventTarget
    public void EventKeyPress(KeyEvent e) {
        for (Module module : Client.moduleManager.getModules()) {
            Logger.debug("Key Pressed: " + e.getKey(), "Key");
            if (module.getKey() == e.getKey()) {
                module.toggle();
            }
        }
    }

    @Override
    public boolean handleEvents() {
        return true;
    }
}
