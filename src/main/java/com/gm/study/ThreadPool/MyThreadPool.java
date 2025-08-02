package com.gm.study.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xexgm
 */
public class MyThreadPool {
    private int corePoolSize;
    private int maxPoolSize;
    private int timeout;
    private TimeUnit timeUnit;
    public BlockingQueue<Runnable> blockingQueue;
    private RejectHandler rejectHandler;

    public MyThreadPool(int corePoolSize, int maxPoolSize, int timeout,
                        TimeUnit timeUnit, BlockingQueue blockingQueue,
                        RejectHandler rejectHandler) {
        this.corePoolSize = corePoolSize;
        this. maxPoolSize = maxPoolSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.blockingQueue = blockingQueue;
        this.rejectHandler = rejectHandler;
    }

    List<Runnable> coreThreadList = new ArrayList<>();
    List<Runnable> supportThreadList = new ArrayList<>();

    // 我们判断 thread list 中有多少个 线程，没有达到，就创建
    public void execute(Runnable command) {
        // 判断核心线程数和添加线程，不是原子的，有线程安全问题
        synchronized (MyThreadPool.class) {
            if (coreThreadList.size() < corePoolSize) {
                Thread thread = new coreThread();
                coreThreadList.add(thread);
                thread.start();
            }
            // 加入阻塞队列成功
            if (blockingQueue.offer(command)) {
                return;
            }
            // 加入阻塞队列失败
            if (supportThreadList.size() + coreThreadList.size() < maxPoolSize) {
                Thread thread = new supportThread();
                supportThreadList.add(thread);
                thread.start();
            }
        }
        // command 扔入阻塞队列失败，重新处理 command
        if (!blockingQueue.offer(command)) {
            // 确实是满了
            rejectHandler.reject(command, this);
        }
    }

    class coreThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // take为空，阻塞在这里
                try {
                    Runnable command = blockingQueue.take();
                    command.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class supportThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // take为空，阻塞在这里
                try {
                    Runnable command = blockingQueue.poll(timeout, timeUnit);
                    if (command == null) break;
                    command.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 辅助线程结束，打个日志
            System.out.println(Thread.currentThread().getName() + " 辅助线程结束");
        }
    }
}
