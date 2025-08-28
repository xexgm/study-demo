package com.gm.study.rateLimiter.tokenBucket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TokenLimitTest {

    @Autowired
    private TestService testService;

    @Test
    public void testAnnotationLimiting() throws InterruptedException {
        // 先执行几次，应该成功
        for (int i = 0; i < 5; i++) {
            try {
                String result = testService.limitedMethod();
                assertEquals("Method executed successfully", result);
            } catch (RuntimeException e) {
                fail("不应该在这个时候被限流: " + e.getMessage());
            }
        }

        // 再执行更多次，应该触发限流
        boolean rateLimited = false;
        for (int i = 0; i < 100; i++) {
            try {
                testService.limitedMethod();
            } catch (RuntimeException e) {
                if (e.getMessage().contains("接口访问过于频繁")) {
                    rateLimited = true;
                    break;
                }
            }
        }

        assertTrue(rateLimited, "应该触发限流");
    }

    @Test
    public void testUnlimitedMethod() {
        // 不限流的方法应该始终可以执行
        for (int i = 0; i < 20; i++) {
            String result = testService.unlimitedMethod();
            assertEquals("Unlimited method executed successfully", result);
        }
    }
}