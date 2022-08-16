package rbq.wtf.lycoris.client.wrapper.wrappers.annotation;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapConstructors;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapConstructors.class)
public @interface WrapConstructor {
    String targetMap();
    Class<?>[] signature() default {};
}
