package rbq.wtf.lycoris.client.utils

import kotlin.math.abs

class TranslateUtils(var x: Float, var y: Float) {
    private var lastMS: Long

    init {
        lastMS = System.currentTimeMillis()
    }

    fun interpolate(targetX: Float, targetY: Float, smoothing: Float) {
        val currentMS = System.currentTimeMillis()
        val delta = currentMS - lastMS
        lastMS = currentMS
        val deltaX = (abs((targetX - x)) * smoothing).toInt()
        val deltaY = (abs((targetY - y)) * smoothing).toInt()
        x = calculateCompensation(targetX, x, delta, deltaX)
        y = calculateCompensation(targetY, y, delta, deltaY)
    }

    private fun calculateCompensation(target: Float, current: Float, delta: Long, speed: Int): Float {
        var current = current
        var delta = delta
        val diff = current - target
        if (delta < 1L) {
            delta = 1L
        }
        if (diff > speed.toFloat()) {
            val dell =
                if ((speed.toLong() * delta / 16L).toDouble() < 0.25) 0.5 else (speed.toLong() * delta / 16L).toDouble()
            if ((current.toDouble() - dell).toFloat().also { current = it } < target) {
                current = target
            }
        } else if (diff < (-speed).toFloat()) {
            val dell =
                if ((speed.toLong() * delta / 16L).toDouble() < 0.25) 0.5 else (speed.toLong() * delta / 16L).toDouble()
            if ((current.toDouble() + dell).toFloat().also { current = it } > target) {
                current = target
            }
        } else {
            current = target
        }
        return current
    }
}