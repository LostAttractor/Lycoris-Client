package rbq.wtf.lycoris.client.module

import org.lwjgl.input.Keyboard

@Retention(AnnotationRetention.RUNTIME)
annotation class ModuleInfo(
    val name: String, val description: String, val category: ModuleCategory,
    val keyBind: Int = Keyboard.CHAR_NONE, val saveState: Boolean = true, val array: Boolean = true
)
