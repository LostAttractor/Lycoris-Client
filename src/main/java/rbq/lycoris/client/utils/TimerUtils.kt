package rbq.lycoris.client.utils

object TimeUtils {
    fun randomDelay(minDelay: Int, maxDelay: Int): Long {
        return RandomUtils.nextInt(minDelay, maxDelay).toLong()
    }

    fun randomClickDelay(minCPS: Float, maxCPS: Float): Long {
        return (Math.random() * (1000 / minCPS - 1000 / maxCPS + 1) + 1000 / maxCPS).toLong()
    }
}