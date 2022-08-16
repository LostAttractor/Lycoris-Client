package rbq.wtf.lycoris.client.wrapper.wrappers.annotation;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapFields;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapFields.class)
/**
 * Auto setup field
 */
public @interface WrapField {
    String mcpName();
    MapEnum targetMap();
    String customSrgName() default "none";
}
