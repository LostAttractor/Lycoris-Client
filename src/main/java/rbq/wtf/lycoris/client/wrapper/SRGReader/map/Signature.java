package rbq.wtf.lycoris.client.wrapper.SRGReader.map;

public class Signature {
    Class<?>[] args;
    Class<?> ret;

    public Signature(Class<?>[] args, Class<?> ret) {
        this.args = args;
        this.ret = ret;
    }

    public Class<?> getRet() {
        return ret;
    }

    public Class<?>[] getArgs() {
        return args;
    }
}
