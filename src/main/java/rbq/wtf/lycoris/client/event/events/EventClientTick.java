package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventClientTick  implements Event {
    TickEvent.ClientTickEvent event ;
    public EventClientTick(TickEvent.ClientTickEvent event) {
        this.event = event;
    }

    public TickEvent.ClientTickEvent getEvent() {
        return event;
    }

    public void setEvent(TickEvent.ClientTickEvent event) {
        this.event = event;
    }
}