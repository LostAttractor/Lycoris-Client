package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapperClass;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapperClasses {
    WrapperClass[] value();
}
