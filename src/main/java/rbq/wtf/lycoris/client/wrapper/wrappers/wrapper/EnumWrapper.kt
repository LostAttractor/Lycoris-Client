package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper

abstract class EnumWrapper(obj: Any) : IWrapper(obj) {
    companion object {
        fun values(): Array<Enum<*>> {
            return emptyArray()
        }
    }
}