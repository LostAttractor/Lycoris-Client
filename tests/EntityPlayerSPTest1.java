package tests;

public class EntityPlayerSPTest1 extends Entity {
    @Override
    public void moveEntity(double x, double y, double z) {
        EventMove event = new EventMove(x,y,z);
        EventManager.call(event);
        super.moveEntity(event.x, event.y, event.z);
    }
}

class Entity {
    public void moveEntity(double x, double y, double z) {
        System.out.println("moveEntity123");
    }
}

interface Event {

}

class EventMove implements Event {
    double x, y, z;

    public EventMove(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

final class EventManager {
    public static Event call(Event event) {
        return event;
    }
}