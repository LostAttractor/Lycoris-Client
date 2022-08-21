import rbq.wtf.lycoris.client.event.EventManager;
import rbq.wtf.lycoris.client.event.PacketSendEvent;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.network.Packet;

public class test {
    public void onUpdateWalkingPlayer(Packet packet) {
        final PacketSendEvent event = new PacketSendEvent(packet);
        Client.eventManager.callEvent(event);

        if(event.isCancelled())
            return;

        //Client.eventManager.callEvent(new MotionEvent(EventState.PRE));
        System.out.println("HelloWorld");
//        if (event.isCanceled())
//            return;
//        System.out.println("HelloWorld");
    }
}

class Client {
    public static EventManager eventManager = new EventManager();
}
