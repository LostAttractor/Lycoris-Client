package rbq.wtf.lycoris.client.utils

import java.io.File

class OnlineResource(val file: File) {
    companion object {
        //const val resourceURL = "http://lycorisresource.lostattractor.net"
        const val resourceURL = "https://raw.githubusercontent.com/LostAttractor/SRGMaps/main"
    }

    init {
        val hash = WebUtils.getContextString("$resourceURL/${file.name}.md5")
        var needDownload = true
        if (file.exists()) {
            if (FileUtils.checkFileHash(file, hash, MathUtils.HashAlgorithm.MD5)) needDownload = false
        }
        if (needDownload) WebUtils.downloadFileWithHashCheck(
            "$resourceURL/${file.name}",
            file,
            hash,
            MathUtils.HashAlgorithm.MD5
        )
    }
}