package rbq.wtf.lycoris.client.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object WebUtils {
    operator fun get(url: String): String {
        try {
            val con = URL(url).openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            con.setRequestProperty(
                "User-Agent",
                "\tMozilla/5.0 (Windows NT 10.0; Win64; x64; WebView/3.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.22000"
            )
            val `in` = BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?
            val response = StringBuilder()
            while (`in`.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            `in`.close()
            return response.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}