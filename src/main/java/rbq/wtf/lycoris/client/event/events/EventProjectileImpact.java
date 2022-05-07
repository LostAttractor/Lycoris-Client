package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.event.entity.ProjectileImpactEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventProjectileImpact implements Event {
    ProjectileImpactEvent event;

    public ProjectileImpactEvent getEvent() {
        return event;
    }

    public void setEvent(ProjectileImpactEvent event) {
        this.event = event;
    }

    public EventProjectileImpact(ProjectileImpactEvent event) {
        this.event = event;
    }
}
