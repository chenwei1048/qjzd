package com.qjzd.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author:
 * @Description:
 * @Date Create on 17:08 2018/11/20
 * @MOdifyBy:
 * @parameter
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface MyOperation {
    String value() default "";//默认为空，因为名字是value，实际操作中可以不写"value="
}
