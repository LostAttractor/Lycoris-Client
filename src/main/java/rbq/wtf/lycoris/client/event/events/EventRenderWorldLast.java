package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventRenderWorldLast implements Event {
    RenderWorldLastEvent event;

    public RenderWorldLastEvent getEvent() {
        return event;
    }

    public void setEvent(RenderWorldLastEvent event) {
        this.event = event;
    }

    public EventRenderWorldLast(RenderWorldLastEvent event) {
        this.event = event;
    }
}
