package rbq.wtf.lycoris.client.event.events;

import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventPostUpdate implements Event {

    public EventPostUpdate(float pitch) {
        //Memory prevPitch and Pitch for rotation animation
        EventPreUpdate.RPPITCH = EventPreUpdate.RPITCH;
       EventPreUpdate.RPITCH = pitch;
    }
}
