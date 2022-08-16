package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapField;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapFields {
    WrapField[] value();
}
