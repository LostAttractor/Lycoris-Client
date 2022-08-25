package rbq.wtf.lycoris.client.module.modules.combat

import rbq.wtf.lycoris.client.event.EventTarget
import rbq.wtf.lycoris.client.event.Render3DEvent
import rbq.wtf.lycoris.client.event.UpdateEvent
import rbq.wtf.lycoris.client.module.Module
import rbq.wtf.lycoris.client.module.ModuleCategory
import rbq.wtf.lycoris.client.module.ModuleInfo
import rbq.wtf.lycoris.client.utils.RandomUtils
import rbq.wtf.lycoris.client.utils.TimeUtils
import rbq.wtf.lycoris.client.value.BooleanValue
import rbq.wtf.lycoris.client.value.NumberValue
import rbq.wtf.lycoris.client.wrapper.wrappers.KeyBinding
import rbq.wtf.lycoris.client.wrapper.wrappers.init.Blocks
import kotlin.random.Random

@ModuleInfo(
    name = "AutoClicker",
    description = "Constantly clicks when holding down a mouse button.",
    category = ModuleCategory.Combat
)
class AutoClicker : Module() {
    val maxCPS: NumberValue = object : NumberValue("Max CPS", 8.0f, 1.0f, 20.0f, 0.5f) {
        override fun onChanged(oldValue: Float, newValue: Float) {
            val minCPS = minCPS.get()
            if (minCPS > newValue)
                set(minCPS)
        }
    }

    val minCPS: NumberValue = object : NumberValue("Min CPS", 8.0f, 1.0f, 20.0f, 0.5f) {
        override fun onChanged(oldValue: Float, newValue: Float) {
            val maxCPS = maxCPS.get()
            if (maxCPS < newValue)
                set(maxCPS)
        }
    }
    val left = BooleanValue("Left", true)
    val right = BooleanValue("Right", true)
    val jitter = BooleanValue("Jitter", false)

    private var rightDelay = TimeUtils.randomClickDelay(minCPS.get(), maxCPS.get())
    private var rightLastSwing = 0L
    private var leftDelay = TimeUtils.randomClickDelay(minCPS.get(), maxCPS.get())
    private var leftLastSwing = 0L

    private var blockBrokenDelay = 1000L / 20 * (6 + 2) // 6 ticks and 2 more, so autoclicker

    // won't click between breaking blocks for sure
    private var blockLastBroken = 0L
    private var isBreakingBlock = false
    private var wasBreakingBlock = false

    fun leftCanAutoClick(currentTime: Long): Boolean {
        return !isBreakingBlock
                && !(currentTime - blockLastBroken < blockBrokenDelay && mc.objectMouseOver != null &&
                mc.world!!.getBlockState(mc.objectMouseOver!!.blockPos).block.equals(Blocks.air)) //equals重写了，请勿替换为==
    }

    fun rightCanAutoClick(): Boolean {
        return !mc.player.isUsingItem
    }

    // BUG: There is no delay between breaking blocks in creative mode
    fun leftClick(currentTime: Long) {
        if (left.get() && mc.gameSettings.keyBindAttack.isKeyDown()) {
            isBreakingBlock = mc.playerController.curBlockDamageMP != 0F
            if (!isBreakingBlock && wasBreakingBlock) {
                blockLastBroken = currentTime
            }
            wasBreakingBlock = isBreakingBlock
            if (currentTime - leftLastSwing >= leftDelay && leftCanAutoClick(currentTime)) {
                KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode()) // Minecraft Click Handling

                leftLastSwing = currentTime
                blockLastBroken = 0L
                leftDelay = TimeUtils.randomClickDelay(minCPS.get(), maxCPS.get())
            }
        }
    }

    fun rightClick(currentTime: Long) {
        if (right.get() && mc.gameSettings.keyBindUseItem.isKeyDown() && currentTime - rightLastSwing >= rightDelay && rightCanAutoClick()) {
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode()) // Minecraft Click Handling

            rightLastSwing = currentTime
            rightDelay = TimeUtils.randomClickDelay(minCPS.get(), maxCPS.get())
        }
    }

    @EventTarget
    fun onRender(event: Render3DEvent) {
        val currentTime = System.currentTimeMillis()
        leftClick(currentTime)
        rightClick(currentTime)
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        if (jitter.get() && ((left.get() && mc.gameSettings.keyBindAttack.isKeyDown() && leftCanAutoClick(System.currentTimeMillis()))
                    || (right.get() && mc.gameSettings.keyBindUseItem.isKeyDown() && rightCanAutoClick()))
        ) {
            if (Random.nextBoolean()) mc.player.rotationYaw += if (Random.nextBoolean()) -RandomUtils.nextFloat(
                0F,
                1F
            ) else RandomUtils.nextFloat(0F, 1F)

            if (Random.nextBoolean()) {
                mc.player.rotationPitch += if (Random.nextBoolean()) -RandomUtils.nextFloat(
                    0F,
                    1F
                ) else RandomUtils.nextFloat(0F, 1F)

                // Make sure pitch is not going into unlegit values
                if (mc.player.rotationPitch > 90)
                    mc.player.rotationPitch = 90F
                else if (mc.player.rotationPitch < -90)
                    mc.player.rotationPitch = -90F
            }
        }
    }
}