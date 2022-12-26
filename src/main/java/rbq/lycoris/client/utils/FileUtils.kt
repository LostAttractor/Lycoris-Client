package rbq.lycoris.client.utils

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

object FileUtils {
    @JvmStatic
    @Throws(IOException::class)
    fun readFileString(file: File): String {
        return String(readFileByte(file))
        //val reader = BufferedReader(InputStreamReader(stream))
        //return reader.lines().collect(Collectors.joining(System.lineSeparator()))
    }

    @Throws(IOException::class)
    @JvmStatic
    fun readFileByte(file: File): ByteArray {
        val stream = file.inputStream()
        val byteStream = getByteArrayOutputStream(stream)
        return byteStream.toByteArray()
    }

    fun getByteArrayOutputStream(stream: InputStream): ByteArrayOutputStream {
        val byteStream = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var count: Int
        while (stream.read(buffer, 0, 1024).also { count = it } != -1) {
            byteStream.write(buffer, 0, count)
        }
        return byteStream
    }

    @Throws(IOException::class)
    @JvmStatic
    fun writeFile(file: File, bytes: ByteArray) {
        val stream = file.outputStream()
        stream.write(bytes)
        stream.close()
    }

    @Throws(IOException::class)
    @JvmStatic
    fun checkFileHash(file: File, hash: String, hashAlgorithm: MathUtils.HashAlgorithm) =
        MathUtils.getHashCode(readFileByte(file), hashAlgorithm) == hash
}