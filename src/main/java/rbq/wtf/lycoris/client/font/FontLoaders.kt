package rbq.wtf.lycoris.client.font

import java.awt.Font

object FontLoaders {
    var default15 = FontRenderer(Font("default", Font.PLAIN, 15), true, true, false)
    var default16 = FontRenderer(Font("default", Font.PLAIN, 16), true, true, false)
    var default18 = FontRenderer(Font("default", Font.PLAIN, 18), true, true, false)
    var default20 = FontRenderer(Font("default", Font.PLAIN, 20), true, true, false)
    var default25 = FontRenderer(Font("default", Font.PLAIN, 25), true, true, false)
    var default30 = FontRenderer(Font("default", Font.PLAIN, 30), true, true, false)

    fun loadUnicode() {
        Thread {
            default15 = FontRenderer(Font("default", Font.PLAIN, 15), true, true, true)
            default16 = FontRenderer(Font("default", Font.PLAIN, 16), true, true, true)
            default18 = FontRenderer(Font("default", Font.PLAIN, 18), true, true, true)
            default20 = FontRenderer(Font("default", Font.PLAIN, 20), true, true, true)
            default25 = FontRenderer(Font("default", Font.PLAIN, 25), true, true, true)
            default30 = FontRenderer(Font("default", Font.PLAIN, 30), true, true, true)
        }.start()
    }
}