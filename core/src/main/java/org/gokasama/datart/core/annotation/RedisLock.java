package org.gokasama.datart.core.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ka uwjia@chinamobile.com
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    //
    String name() default "";

    //
    String key() default "";

    //
    long waitTime() default 5000;

    //
    long leaseTime() default 10000;

    //
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
