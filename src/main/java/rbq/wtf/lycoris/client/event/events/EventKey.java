package rbq.wtf.lycoris.client.event.events;

import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventKey implements Event {
    int key;

    public EventKey(int key) {
        this.key = key;
        EventManager.call(this);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}