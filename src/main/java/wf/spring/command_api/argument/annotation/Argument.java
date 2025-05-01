package wf.spring.command_api.argument.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Argument {
    String name();
    String type() default "string";
    boolean obligatorily() default true;
}
