package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventItemPickup implements Event {
    EntityItemPickupEvent event;

    public EventItemPickup(EntityItemPickupEvent event) {
        this.event = event;
    }

    public EntityItemPickupEvent getEvent() {
        return event;
    }

    public void setEvent(EntityItemPickupEvent event) {
        this.event = event;
    }
}
