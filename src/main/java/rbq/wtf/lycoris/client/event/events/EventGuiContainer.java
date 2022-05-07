package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.client.event.GuiContainerEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventGuiContainer implements Event {
    GuiContainerEvent event;

    public EventGuiContainer (GuiContainerEvent event) {
        this.event = event;
    }

    public GuiContainerEvent getEvent() {
        return event;
    }

    public void setEvent(GuiContainerEvent event) {
        this.event = event;
    }
}
