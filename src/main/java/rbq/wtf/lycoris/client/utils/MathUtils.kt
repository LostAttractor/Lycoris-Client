package rbq.wtf.lycoris.client.utils

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

object MathUtils {
    fun round(num: Float, increment: Float): Float {
        val v = (num / increment).roundToInt() * increment
        var bd = BigDecimal(v.toDouble())
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toFloat()
    }

    fun round(num: Double, increment: Double): Double {
        val v = (num / increment).roundToInt() * increment
        var bd = BigDecimal(v)
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toDouble()
    }
}