import rbq.wtf.lycoris.client.event.EventManager;
import rbq.wtf.lycoris.client.event.EventState;
import rbq.wtf.lycoris.client.event.MotionEvent;

public class test {
    public void onUpdateWalkingPlayer() {
        Client.eventManager.callEvent(new MotionEvent(EventState.PRE));
        System.out.println("HelloWorld");
//        if (event.isCanceled())
//            return;
//        System.out.println("HelloWorld");
    }
}

class Client {
    public static EventManager eventManager = new EventManager();
}
