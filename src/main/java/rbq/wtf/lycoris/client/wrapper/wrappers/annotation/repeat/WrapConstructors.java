package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapConstructor;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapConstructors {
    WrapConstructor[] value();
}
