package rbq.wtf.lycoris.client.module.Modules.Combat

import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.utils.TimeUtils
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.NumberValue

class AutoClicker : Module("AutoClicker", ModuleCategory.Combat, 0) {
    val maxCPS : NumberValue = object : NumberValue("Max CPS", 8.0f, 1.0f, 20.0f, 0.5f, this) {
        override fun onChanged(oldValue: Float, newValue: Float) {
            val minCPS = minCPS.get()
            if (minCPS > newValue)
                set(minCPS)
        }
    }

    val minCPS : NumberValue = object : NumberValue("Min CPS", 8.0f, 1.0f, 20.0f, 0.5f, this) {
        override fun onChanged(oldValue: Float, newValue: Float) {
            val maxCPS = maxCPS.get()
            if (maxCPS < newValue)
                set(maxCPS)
        }
    }
    val Left = BooleanValue("Left", true, this)
    val Right = BooleanValue("Right", true, this)
    val Jitter = BooleanValue("Jitter", false, this)

    private var rightDelay = TimeUtils.randomClickDelay(minCPS.get(), maxCPS.get())
    private var rightLastSwing = 0L
    private var leftDelay = TimeUtils.randomClickDelay(minCPS.get(), maxCPS.get())
    private var leftLastSwing = 0L

    private var blockBrokenDelay = 1000L / 20 * (6 + 2) // 6 ticks and 2 more, so autoclicker
    // won't click between breaking blocks for sure
    private var blockLastBroken = 0L
    private var isBreakingBlock = false
    private var wasBreakingBlock = false

//    fun leftCanAutoClick(currentTime: Long): Boolean {
//        return !isBreakingBlock
//                && !(currentTime - blockLastBroken < blockBrokenDelay &&
//                mc.objectMouseOver != null && mc.objectMouseOver!!.blockPos != null && mc.theWorld != null &&
//                mc.theWorld.getBlockState(mc.objectMouseOver.blockPos).block != Blocks.air)
//    }
//
//    fun rightCanAutoClick(): Boolean {
//        return !mc.thePlayer!!.isUsingItem
//    }
}