package com.exercise.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@RequestMapping(method = {RequestMethod.POST})
public @interface PostMapping {
    String value() default "";
}
