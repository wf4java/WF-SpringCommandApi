package wf.spring.command_api.annotation;



import wf.spring.command_api.argument.annotation.Argument;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandHandle {

    String group() default "default";

    String command();

    Argument[] arguments();

}
