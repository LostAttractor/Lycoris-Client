package rbq.wtf.lycoris.client.wrapper.wrappers.annotation;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapEnums;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapFields;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapEnums.class)
/**
 * Auto setup Enum
 */
public @interface WrapEnum {
    String mcpName();
    MapEnum targetMap();
    String customSrgName() default "none";
}
