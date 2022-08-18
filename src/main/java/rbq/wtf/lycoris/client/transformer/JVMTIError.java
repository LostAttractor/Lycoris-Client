package rbq.wtf.lycoris.client.transformer;

public enum JVMTIError {
    JVMTI_ERROR_NONE(0),
    JVMTI_ERROR_INVALID_THREAD(10),
    JVMTI_ERROR_INVALID_THREAD_GROUP(11),
    JVMTI_ERROR_INVALID_PRIORITY(12),
    JVMTI_ERROR_THREAD_NOT_SUSPENDED(13),
    JVMTI_ERROR_THREAD_SUSPENDED(14),
    JVMTI_ERROR_THREAD_NOT_ALIVE(15),
    JVMTI_ERROR_INVALID_OBJECT(20),
    JVMTI_ERROR_INVALID_CLASS(21),
    JVMTI_ERROR_CLASS_NOT_PREPARED(22),
    JVMTI_ERROR_INVALID_METHODID(23),
    JVMTI_ERROR_INVALID_LOCATION(24),
    JVMTI_ERROR_INVALID_FIELDID(25),
    JVMTI_ERROR_NO_MORE_FRAMES(31),
    JVMTI_ERROR_OPAQUE_FRAME(32),
    JVMTI_ERROR_TYPE_MISMATCH(34),
    JVMTI_ERROR_INVALID_SLOT(35),
    JVMTI_ERROR_DUPLICATE(40),
    JVMTI_ERROR_NOT_FOUND(41),
    JVMTI_ERROR_INVALID_MONITOR(50),
    JVMTI_ERROR_NOT_MONITOR_OWNER(51),
    JVMTI_ERROR_INTERRUPT(52),
    JVMTI_ERROR_INVALID_CLASS_FORMAT(60),
    JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION(61),
    JVMTI_ERROR_FAILS_VERIFICATION(62),
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED(63),
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED(64),
    JVMTI_ERROR_INVALID_TYPESTATE(65),
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED(66),
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED(67),
    JVMTI_ERROR_UNSUPPORTED_VERSION(68),
    JVMTI_ERROR_NAMES_DONT_MATCH(69),
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED(70),
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED(71),
    JVMTI_ERROR_UNMODIFIABLE_CLASS(79),
    JVMTI_ERROR_NOT_AVAILABLE(98),
    JVMTI_ERROR_MUST_POSSESS_CAPABILITY(99),
    JVMTI_ERROR_NULL_POINTER(100),
    JVMTI_ERROR_ABSENT_INFORMATION(101),
    JVMTI_ERROR_INVALID_EVENT_TYPE(102),
    JVMTI_ERROR_ILLEGAL_ARGUMENT(103),
    JVMTI_ERROR_NATIVE_METHOD(104),
    JVMTI_ERROR_CLASS_LOADER_UNSUPPORTED(106),
    JVMTI_ERROR_OUT_OF_MEMORY(110),
    JVMTI_ERROR_ACCESS_DENIED(111),
    JVMTI_ERROR_WRONG_PHASE(112),
    JVMTI_ERROR_INTERNAL(113),
    JVMTI_ERROR_UNATTACHED_THREAD(115),
    JVMTI_ERROR_INVALID_ENVIRONMENT(116),
    JVMTI_ERROR_MAX(116);

    private int value;

    JVMTIError(int value) {
        this.value = value;
    }

    public static String parse(int value) {
        for (JVMTIError error : JVMTIError.values()) {
            if (error.getValue() == value) {
                return firstLetterString(error.name().replace("JVMTI_ERROR_NONE", "Successful").replaceAll("JVMTI_ERROR_", "").replaceAll("_", " ").toLowerCase()) + "(" + (value) + ")";
            }
        }
        return "Unknown error(" + value + ")";
    }

    public static String firstLetterString(String s) {
        String result = " ";
        String[] value = s.split(" ");
        for (String item : value) {
            String firstLetter = item.substring(0, 1).toUpperCase();
            String nextLetter = item.substring(1);
            result = result.concat(firstLetter.concat(nextLetter) + " ");
        }
        return result.trim();
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + " : " + value;
    }
}
