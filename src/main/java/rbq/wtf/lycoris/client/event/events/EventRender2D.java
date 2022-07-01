package rbq.wtf.lycoris.client.event.events;

import net.minecraft.client.gui.ScaledResolution;
import rbq.wtf.lycoris.client.event.api.EventManager;
import rbq.wtf.lycoris.client.event.api.events.Event;

public class EventRender2D implements Event {
    ScaledResolution scaledResolution;
    float partialTicks;
    public EventRender2D(ScaledResolution scaledResolution,float partialTicks) {
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
