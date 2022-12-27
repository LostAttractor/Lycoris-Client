package rbq.lycoris.client.wrapper.wrappers

import rbq.lycoris.client.wrapper.IWrapper

abstract class EnumWrapper(obj: Any) : IWrapper(obj) {
    companion object {
        fun values(): Array<Enum<*>> {
            return emptyArray()
        }
    }
}