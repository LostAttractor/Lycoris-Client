package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapEnum;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapEnums {
    WrapEnum[] value();
}
