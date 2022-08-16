package rbq.wtf.lycoris.client.event.events;

import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventRender3D implements Event {
    float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
