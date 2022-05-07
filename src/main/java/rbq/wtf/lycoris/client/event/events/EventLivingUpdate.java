package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.event.entity.living.LivingEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventLivingUpdate implements Event {
    LivingEvent.LivingUpdateEvent event;

    public LivingEvent.LivingUpdateEvent getEvent() {
        return event;
    }

    public void setEvent(LivingEvent.LivingUpdateEvent event) {
        this.event = event;
    }

    public EventLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        this.event = event;
    }
}
