package com.gm.study.rateLimiter.tokenBucket;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xexgm
 * @date: 2025/8/28
 * des: 令牌桶限流器，考虑的维度：1.本服务（目前单机）所有接口，2.本服务（目前单机）单个接口
 * 如果要对分布式环境，应该用分布式缓存来做令牌的容器
 * 维度1: 把限流器做进拦截器
 * 维度2: 单接口所有流量共享一个限流器
 */
public class TokenLimiter implements RateLimiter{

    // 创建静态的定时器
    private static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);


    // 令牌最大容量
    Integer capacity;

    // 补充令牌的速率 rate 个/s
    Integer rate;

    // 当前令牌数, 需要初始化
    final AtomicInteger tokens;

    // 接口限流器名称
    String limiterName;

    public TokenLimiter(String limiterName) {
        this.capacity = 1000;
        this.rate = 200;
        this.tokens = new AtomicInteger(1000);
        this.limiterName = limiterName;

        addToken();
    }

    public TokenLimiter(int capacity, int rate, int initTokens, String limiterName) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = new AtomicInteger(initTokens);
        this.limiterName = limiterName;

        addToken();
    }

    // ture -> 放行, false -> 拦截
    @Override
    public boolean limit() {
        while (true) {
            int current = this.tokens.get();
            // 无令牌 拦截
            if (current <= 0) {
                return false;
            }

            // 有令牌 放行
            if (this.tokens.compareAndSet(current, current-1)) {
                return true;
            }
        }
    }

    private void addToken() {
        // 启动定时任务，补充令牌
        SCHEDULER.scheduleAtFixedRate(() -> {
                int current = tokens.get();
                tokens.compareAndSet(current, Math.min(capacity, current + rate));
            }, 0, 1, TimeUnit.SECONDS);
    }

}
