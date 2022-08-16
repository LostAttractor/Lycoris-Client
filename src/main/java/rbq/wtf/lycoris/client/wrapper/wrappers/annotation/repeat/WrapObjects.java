package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapObject;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapObjects {
    WrapObject[] value();
}
