package rbq.wtf.lycoris.client.utils

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

object FileUtils {
    @Throws(IOException::class)
    fun readFileByPath(path: Path): String {
        val stream = Files.newInputStream(path)
        val bos = ByteArrayOutputStream()
        val buffer = ByteArray(512000)
        var length: Int
        while (stream.read(buffer).also { length = it } != -1) {
            bos.write(buffer, 0, length)
        }
        return bos.toString()
    }
}