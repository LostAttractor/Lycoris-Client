package rbq.wtf.lycoris.client.utils

import java.awt.Color

object ColorUtils {
    fun getRainbow(offset: Float, saturation: Float, alpha: Float): Color {
        return Color.getHSBColor((255F - offset * 0.001f) % 73, saturation, alpha)
    }
}