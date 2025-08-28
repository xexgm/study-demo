package com.gm.study.rateLimiter.tokenBucket;

/**
 * @author: xexgm
 * @date: 2025/8/28
 */
public class Main {

    @Limit(interfaceName = "main", capacity = 100)
    public static void main(String[] args) {
        RateLimiterManager.doLimit("main");
    }

}
