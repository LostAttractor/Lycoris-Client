package rbq.lycoris.client.utils

import rbq.lycoris.client.Client

object Logger {
    @JvmStatic
    fun info(msg: String) {
        log(msg, LogLevel.INFO)
    }

    @JvmStatic
    fun info(msg: String, category: String) {
        log(msg, category, LogLevel.INFO)
    }

    @JvmStatic
    fun warning(msg: String) {
        log(msg, LogLevel.WARNING)
    }

    @JvmStatic
    fun warning(msg: String, category: String) {
        log(msg, category, LogLevel.WARNING)
    }

    @JvmStatic
    fun error(msg: String) {
        log(msg, LogLevel.ERROR)
    }

    @JvmStatic
    fun error(msg: String, category: String) {
        log(msg, category, LogLevel.ERROR)
    }

    @JvmStatic
    fun debug(msg: String) {
        log(msg, LogLevel.DEBUG)
    }

    @JvmStatic
    fun debug(msg: String, category: String) {
        log(msg, category, LogLevel.DEBUG)
    }

    @JvmStatic
    private fun log(msg: String, level: LogLevel) {
        if (Client.enabledLog && (Client.showDebugLevelLog || level != LogLevel.DEBUG)) {
            println("[Lycoris] [$level] $msg")
        }
    }

    @JvmStatic
    private fun log(msg: String, category: String, level: LogLevel) {
        if (Client.enabledLog && (Client.showDebugLevelLog || level != LogLevel.DEBUG)) {
            println("[Lycoris] [$category] [$level] $msg")
        }
    }

    enum class LogLevel {
        INFO, WARNING, ERROR, DEBUG
    }
}