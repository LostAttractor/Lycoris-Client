package rbq.wtf.lycoris.event.rbq.wtf.lycoris.client.event

open class Event

open class CancellableEvent : Event() {

    /**
     * Let you know if the event is cancelled
     *
     * @return state of cancel
     */
    var isCancelled: Boolean = false
        private set

    /**
     * Allows you to cancel an event
     */
    fun cancelEvent() {
        isCancelled = true
    }

}

enum class EventState {
    PRE, POST
}