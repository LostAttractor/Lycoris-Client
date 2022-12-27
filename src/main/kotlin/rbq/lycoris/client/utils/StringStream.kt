package rbq.lycoris.client.utils

class StringStream(private val buf: String) {
    private var count = 0
    fun read(): String {
        val r = buf[count].toString()
        count++
        return r
    }

    fun available(): Boolean {
        return count + 1 != buf.length
    }
}