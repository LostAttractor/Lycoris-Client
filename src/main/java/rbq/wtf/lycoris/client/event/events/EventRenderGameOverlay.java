package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventRenderGameOverlay implements Event {
    RenderGameOverlayEvent.Text event;

    public RenderGameOverlayEvent.Text getEvent() {
        return event;
    }

    public void setEvent(RenderGameOverlayEvent.Text event) {
        this.event = event;
    }

    public EventRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        this.event = event;
    }
}
