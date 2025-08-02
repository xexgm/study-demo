package com.gm.study.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xexgm
 */
public class Main {
    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(2, 4,
                1, TimeUnit.SECONDS, new ArrayBlockingQueue(2),
                new DiscardRejectHandler());

        for (int i = 0; i < 10; i++) {
            final int fi = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 当前任务 " + fi);
            });
        }

        System.out.println("主线程没有被阻塞");
    }
}
