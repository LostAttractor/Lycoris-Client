package rbq.wtf.lycoris.client.wrapper.wrappers.annotation;

import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.WrapperClasses;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapperClasses.class)
public @interface WrapperClass {
    String mcpName();
    String targetMap();
}
