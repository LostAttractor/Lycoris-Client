package rbq.wtf.lycoris.client.wrapper.SRGReader.map;

public class Signature {
    private final Class<?>[] args;
    private final Class<?> returnType;

    public Signature(Class<?>[] args, Class<?> returnType) {
        this.args = args;
        this.returnType = returnType;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public Class<?>[] getArgs() {
        return args;
    }
}
