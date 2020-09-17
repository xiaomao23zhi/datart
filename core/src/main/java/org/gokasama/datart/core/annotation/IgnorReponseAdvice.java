package org.gokasama.datart.core.annotation;

import java.lang.annotation.*;

/**
 * @author ka wujia@chinamobile.com
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnorReponseAdvice {
}
