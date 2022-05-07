package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.fml.common.gameevent.InputEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventKey implements Event {
    int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public EventKey(int key) {
        this.key = key;
    }

}
