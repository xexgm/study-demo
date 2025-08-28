package com.gm.study.rateLimiter.tokenBucket;

import java.lang.reflect.Method;

import com.gm.study.rateLimiter.tokenBucket.limit.RateLimiterManager;
import com.gm.study.rateLimiter.tokenBucket.limit.TokenLimitAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author: xexgm
 * @date: 2025/8/29
 * des: 令牌桶限流器织入类
 */
@Component
@Aspect
public class TokenLimiterAspect {

    @Around("@annotation(com.gm.study.rateLimiter.tokenBucket.limit.TokenLimitAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 拿到方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 拿到方法
        Method method = signature.getMethod();
        // 拿到注解
        TokenLimitAnnotation annotation = method.getAnnotation(TokenLimitAnnotation.class);

        int capacity = annotation.capacity();
        int initTokens = annotation.initTokens();
        String interfaceName = annotation.interfaceName();
        int rate = annotation.rate();

        boolean res = RateLimiterManager.doLimit(interfaceName, capacity, rate, initTokens);
        if (!res) {
            throw new RuntimeException("接口访问过于频繁");
        }

        return joinPoint.proceed();
    }
}
