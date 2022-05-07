package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.client.event.MouseEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventMouse implements Event {
    MouseEvent event;

    public EventMouse (MouseEvent event) {
        this.event = event;
    }

    public MouseEvent getEvent() {
        return event;
    }

    public void setEvent(MouseEvent event) {
        this.event = event;
    }
}
