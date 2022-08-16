package rbq.wtf.lycoris.client.wrapper.wrappers.annotation;

import rbq.wtf.lycoris.client.wrapper.MapEnum;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapMethods;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapMethods.class)
/**
 * Auto setup method
 */
public @interface WrapMethod {
    String mcpName();
    MapEnum targetMap();
    String customSrgName() default "none";
    String signature() default "none";
}
