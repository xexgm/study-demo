package com.gm.study.rateLimiter.tokenBucket.limit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: xexgm
 * @date: 2025/8/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenLimitAnnotation {

    String interfaceName();

    int capacity() default 1000;

    int rate() default 200;

    int initTokens() default 1000;

}
