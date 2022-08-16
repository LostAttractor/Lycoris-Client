package rbq.wtf.lycoris.client.event.events;


import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.events.Event;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.ScaledResolution;

public class EventRender2D implements Event {

    float partialTicks;
    ScaledResolution scaledResolution;
    public EventRender2D(ScaledResolution scaledResolution,float partialTicks)
    {
        this.scaledResolution = scaledResolution;
        this.partialTicks = partialTicks;
        EventManager.call(this);
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }

    public void setScaledResolution(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
}
