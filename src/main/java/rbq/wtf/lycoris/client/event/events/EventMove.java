package rbq.wtf.lycoris.client.event.events;

import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventMove implements Event {
    public double x, y, z;

    public EventMove(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
