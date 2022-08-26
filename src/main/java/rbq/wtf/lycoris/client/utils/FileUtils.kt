package rbq.wtf.lycoris.client.utils

import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

object FileUtils {
    @Throws(IOException::class)
    fun readFileString(file: File): String {
        return String(readFileByte(file))
        //val reader = BufferedReader(InputStreamReader(stream))
        //return reader.lines().collect(Collectors.joining(System.lineSeparator()))
    }

    @Throws(IOException::class)
    fun readFileByte(file: File): ByteArray {
        val stream = file.inputStream()
        val byteStream = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var count: Int
        while (stream.read(buffer, 0, 1024).also { count = it } != -1) {
            byteStream.write(buffer, 0, count)
        }
        return byteStream.toByteArray()
    }

    @Throws(IOException::class)
    fun writeFile(file: File, bytes: ByteArray) {
        val stream = file.outputStream()
        stream.write(bytes)
        stream.close()
    }

    @Throws(IOException::class)
    fun checkFileHash(file: File, hash: String, hashAlgorithm: MathUtils.HashAlgorithm) = MathUtils.getHashCode(readFileByte(file), hashAlgorithm) == hash
}