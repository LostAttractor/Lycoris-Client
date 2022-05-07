package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventPlayerTick implements Event {
    TickEvent.PlayerTickEvent event;

    public TickEvent.PlayerTickEvent getEvent() {
        return event;
    }

    public void setEvent(TickEvent.PlayerTickEvent event) {
        this.event = event;
    }

    public EventPlayerTick(TickEvent.PlayerTickEvent event) {
        this.event = event;
    }
}
