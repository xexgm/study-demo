package com.gm.study.rateLimiter.tokenBucket;

import com.gm.study.rateLimiter.tokenBucket.limit.TokenLimitAnnotation;
import org.springframework.stereotype.Service;

/**
 * des: 测试服务类，用于验证TokenLimit注解功能
 */
@Service
public class TestService {

    @TokenLimitAnnotation(interfaceName = "testInterface", capacity = 10, rate = 1, initTokens = 5)
    public String limitedMethod() {
        return "Method executed successfully";
    }

    public String unlimitedMethod() {
        return "Unlimited method executed successfully";
    }
}