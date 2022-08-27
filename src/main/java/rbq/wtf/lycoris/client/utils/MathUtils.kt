package rbq.wtf.lycoris.client.utils

import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.security.MessageDigest
import kotlin.math.roundToInt

object MathUtils {
    fun round(num: Float, increment: Float): Float {
        val v = (num / increment).roundToInt() * increment
        var bd = BigDecimal(v.toDouble())
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toFloat()
    }

    fun round(num: Double, increment: Double): Double {
        val v = (num / increment).roundToInt() * increment
        var bd = BigDecimal(v)
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toDouble()
    }

    @Throws(IOException::class)
    fun checkContextHash(context: String, hash: String, hashAlgorithm: HashAlgorithm) =
        MathUtils.getHashCode(context, hashAlgorithm) == hash

    fun getHashCode(input: String, mode: HashAlgorithm): String {
        val digest = MessageDigest.getInstance(mode.modeName)
        digest.update(input.toByteArray(charset("utf8")))
        return BigInteger(1, digest.digest()).toString(digest.digestLength)
    }

    fun getHashCode(input: ByteArray, mode: HashAlgorithm): String {
        val digest = MessageDigest.getInstance(mode.modeName)
        digest.update(input)
        return BigInteger(1, digest.digest()).toString(digest.digestLength)
    }

    enum class HashAlgorithm(val modeName: String) {
        MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA512("SHA-512")
    }
}