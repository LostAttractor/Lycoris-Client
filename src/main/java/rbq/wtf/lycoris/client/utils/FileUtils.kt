package rbq.wtf.lycoris.client.utils

import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

object FileUtils {
    @Throws(IOException::class)
    fun readFileByPath(path: Path): String {
        val stream = Files.newInputStream(path)
        val reader = BufferedReader(InputStreamReader(stream))
        return reader.lines().collect(Collectors.joining(System.lineSeparator()))
    }

    @Throws(IOException::class)
    fun readFile(file: File): String {
        val stream = file.inputStream()
        val reader = BufferedReader(InputStreamReader(stream))
        return reader.lines().collect(Collectors.joining(System.lineSeparator()))
    }

    @Throws(IOException::class)
    fun checkFileHash(file: File, hash: String, hashAlgorithm: MathUtils.HashAlgorithm) = MathUtils.getHashCode(readFile(file), hashAlgorithm) == hash
}