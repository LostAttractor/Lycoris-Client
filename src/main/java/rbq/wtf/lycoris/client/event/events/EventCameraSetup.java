package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventCameraSetup implements Event {
    EntityViewRenderEvent.CameraSetup event;

    public EntityViewRenderEvent.CameraSetup getEvent() {
        return event;
    }

    public void setEvent(EntityViewRenderEvent.CameraSetup event) {
        this.event = event;
    }

    public EventCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        this.event = event;
    }
}
