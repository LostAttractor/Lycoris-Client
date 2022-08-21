package rbq.wtf.lycoris.client

import rbq.wtf.lycoris.client.event.EventManager

class Client {
    companion object {
        @JvmField
        var eventManager = EventManager()
    }
}