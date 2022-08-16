package rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat;



import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.WrapClass;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapClasses {
    WrapClass[] value();
}
