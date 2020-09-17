package org.gokasama.datart.core.annotation;


import java.lang.annotation.*;

/**
 * @author ka wujia@chinamobile.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auditing {

    // Operation
    String operation() default "";
}
