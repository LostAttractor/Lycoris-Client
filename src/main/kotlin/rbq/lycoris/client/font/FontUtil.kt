package rbq.lycoris.client.font

import rbq.lycoris.client.utils.MinecraftInstance
import rbq.lycoris.client.wrapper.wrappers.util.ResourceLocation
import java.awt.Font
import java.awt.FontFormatException
import java.io.IOException

object FontUtil : MinecraftInstance() {
    @JvmStatic
    fun getFontFromTTF(fontLocation: ResourceLocation?, fontSize: Float, fontType: Int): Font {
        return try {
            Font.createFont(fontType, mc.resourceManager.getResource(fontLocation!!).inputStream).deriveFont(fontSize)
        } catch (e: FontFormatException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}