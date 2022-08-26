package rbq.wtf.lycoris.client.utils

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.util.stream.Collectors

object WebUtils {
    fun getContextByte(url: String): ByteArray {
        Logger.debug("Try to Get WebContext: $url")
        val connection = createConnection(url)
        val stream = connection.inputStream
        val byteStream = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var count: Int
        while (stream.read(buffer, 0, 1024).also { count = it } != -1) {
            byteStream.write(buffer, 0, count)
        }
        stream.close();
        return byteStream.toByteArray()
        //val reader = BufferedReader(InputStreamReader(connection.inputStream))
        //return reader.lines().collect(Collectors.joining(System.lineSeparator()))
    }

    fun getContextString(url: String): String {
        return String(getContextByte(url))
    }

    @Throws(IOException::class)
    fun downloadFileWithHashCheck(url: String, file: File, hashCode: String, hashMode: MathUtils.HashAlgorithm) {
        for (i in 1..10) {
            Logger.info("Try to download ${file.name}...")
            val context = getContextByte(url)
            val contextHash = MathUtils.getHashCode(context, hashMode)
            Logger.info("Checking Hash...\nInput Hash:$hashCode, Downloaded File Hash: $contextHash, Hash algorithm:${hashMode.modeName}")
            if (contextHash == hashCode) {
                FileUtils.writeFile(file, context)
                Logger.info("Download ${file.name} Successful")
                return
            } else {
                Logger.warning("HashCode Check Failed! Trying times: $i")
            }
        }
        throw FileNotFoundException("Failed to Download File:${file.name}, HashCheck Failed!")
    }

    @Throws(IOException::class)
    fun downloadFileNIO(url: String, file: File) {
        Logger.info("Try to download ${file.name}...")
        val connection = createConnection(url)
        val rbc: ReadableByteChannel = Channels.newChannel(connection.inputStream)
        val fos = FileOutputStream(file)
        fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
        fos.close()
        rbc.close()
        Logger.info("Download ${file.name} Successful")
    }

    private fun createConnection(url: String): HttpURLConnection {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 5 * 1000;
        connection.setRequestProperty(
            "User-Agent",
            "\tMozilla/5.0 (Windows NT 10.0; Win64; x64; WebView/3.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.22000"
        )
        return connection
    }
}