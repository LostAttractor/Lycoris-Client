package rbq.lycoris.client

import rbq.lycoris.client.event.EventManager

class Client {
    companion object {
        @JvmField
        var eventManager = EventManager()
    }
}