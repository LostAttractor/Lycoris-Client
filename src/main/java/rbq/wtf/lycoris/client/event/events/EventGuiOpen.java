package rbq.wtf.lycoris.client.event.events;

import net.minecraftforge.client.event.GuiOpenEvent;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventGuiOpen implements Event {
    GuiOpenEvent event;
    public EventGuiOpen (GuiOpenEvent event){
        this.event = event;
    }

    public GuiOpenEvent getEvent() {
        return event;
    }

    public void setEvent(GuiOpenEvent event) {
        this.event = event;
    }
}
