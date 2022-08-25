package rbq.wtf.lycoris.client.event

import rbq.wtf.lycoris.client.wrapper.wrappers.network.Packet

/**
 * Called in "runGameLoop"
 */
class LoopEvent : Event()

/**
 * Called in "runTick"
 */
class TickEvent : Event()


/**
 * Called when minecraft player will be updated
 * Called in "onLivingUpdate"
 */
class UpdateEvent : Event()

/**
 * Called in "onUpdateWalkingPlayer"
 *
 * @param eventState PRE or POST
 */
class MotionEvent(val eventState: EventState) : Event()

/**
 * Called when player moves
 * Called in "onUpdateWalkingPlayer"
 *
 * @param x motion
 * @param y motion
 * @param z motion
 */
class MoveEvent(var x: Double, var y: Double, var z: Double) : CancellableEvent() {
    var isSafeWalk = false

    fun zero() {
        x = 0.0
        y = 0.0
        z = 0.0
    }

    fun zeroXZ() {
        x = 0.0
        z = 0.0
    }
}

/**
 * Called when screen is going to be rendered
 */
class Render2DEvent(val partialTicks: Float) : Event()

/**
 * Called when world is going to be rendered
 */
class Render3DEvent(val partialTicks: Float) : Event()

/**
 * Called when user press a key once
 *
 * @param key Pressed key
 */
class KeyEvent(val key: Int) : Event()

/**
 * Called when send a packet
 */
class PacketSendEvent(val packet: Packet) : CancellableEvent()

/**
 * Called when receive a packet
 */
class PacketReceiveEvent(val packet: Packet) : CancellableEvent()