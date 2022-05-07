package rbq.wtf.lycoris.client.event.events;

import rbq.wtf.lycoris.client.event.api.events.Event;
import rbq.wtf.lycoris.client.event.api.events.callables.EventCancellable;
import rbq.wtf.lycoris.client.utils.Connection;

public class EventPacket extends EventCancellable {
    Object packet;
    Connection.Side side;

    public Object getPacket() {
        return packet;
    }

    public void setPacket(Object packet) {
        this.packet = packet;
    }

    public Connection.Side getSide() {
        return side;
    }

    public void setSide(Connection.Side side) {
        this.side = side;
    }

    public EventPacket(Object packet, Connection.Side side) {
        this.packet = packet;
        this.side = side;
    }
}
