package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventLeftClickBlock implements Event {
    PlayerInteractEvent.LeftClickBlock event;

    public PlayerInteractEvent.LeftClickBlock getEvent() {
        return event;
    }

    public void setEvent(PlayerInteractEvent.LeftClickBlock event) {
        this.event = event;
    }

    public EventLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        this.event = event;
    }
}
