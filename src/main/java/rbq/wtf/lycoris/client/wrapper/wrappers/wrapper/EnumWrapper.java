package rbq.wtf.lycoris.client.wrapper.wrappers.wrapper;

public abstract class EnumWrapper extends IWrapper {
    public EnumWrapper(Object obj) {
        super(obj);
    }

    public static Enum[] values() {
        return new Enum[0];
    }
}