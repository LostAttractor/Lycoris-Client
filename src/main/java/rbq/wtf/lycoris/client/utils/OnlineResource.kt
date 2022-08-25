import rbq.wtf.lycoris.client.utils.FileUtils
import rbq.wtf.lycoris.client.utils.MathUtils
import rbq.wtf.lycoris.client.utils.WebUtils
import java.io.File

class OnlineResource(val file: File) {
    companion object {
        const val resourceURL = "https://srgmaps.vercel.app"
    }

    init {
        val hash = WebUtils.getContext("$resourceURL/${file.name}.md5")
        var needDownload = true
        if (file.exists()) {
            if (FileUtils.checkFileHash(file, hash, MathUtils.HashAlgorithm.MD5)) needDownload = false
        }
        if (needDownload) WebUtils.downloadFileWithHashCheck("$resourceURL/${file.name}", file, hash, MathUtils.HashAlgorithm.MD5)
    }
}

class OnlineContext(val file: File) {
    companion object {
        const val resourceURL = "https://srgmaps.vercel.app"
    }

    lateinit var context : String

    init {
        val hash = WebUtils.getContext("$resourceURL/${file.name}.md5")
        var needDownload = true
        if (file.exists()) {
            val fileContext = FileUtils.readFile(file)
            if (MathUtils.checkContextHash(fileContext, hash, MathUtils.HashAlgorithm.MD5)) {
                needDownload = false
                context = fileContext
            }
        }
        if (needDownload) {
            context = WebUtils.downloadFileWithHashCheck("$resourceURL/${file.name}", file, hash, MathUtils.HashAlgorithm.MD5)
        }
    }
}