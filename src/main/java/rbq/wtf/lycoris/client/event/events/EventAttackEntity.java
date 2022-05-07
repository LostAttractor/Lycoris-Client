package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.event.entity.player.AttackEntityEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventAttackEntity implements Event {
    AttackEntityEvent event;

    public AttackEntityEvent getEvent() {
        return event;
    }

    public void setEvent(AttackEntityEvent event) {
        this.event = event;
    }

    public EventAttackEntity(AttackEntityEvent event) {
        this.event = event;
    }
}
