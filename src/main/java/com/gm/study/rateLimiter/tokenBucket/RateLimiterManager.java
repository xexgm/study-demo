package com.gm.study.rateLimiter.tokenBucket;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xexgm
 * @date 2025/8/28
 * des: 令牌桶限流器管理者
 */
public class RateLimiterManager {


    /** limiterName -> limiter **/
    private static ConcurrentHashMap<String, TokenLimiter> tokenLimiterMap = new ConcurrentHashMap<>();

    // 获取接口名称，通过接口名称拿限流器，同时做限流
    public static boolean doLimit(String limiterName) {
        TokenLimiter rateLimiter = tokenLimiterMap.computeIfAbsent(limiterName, key -> new TokenLimiter(limiterName));
        return rateLimiter.limit();
    }


}
