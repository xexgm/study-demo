package com.gm.study.ScheduleService;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: xexgm
 */
public class Schedule {

     ExecutorService executorService = Executors.newFixedThreadPool(6);

    Trigger trigger = new Trigger();

    void schedule(Runnable task, int delay) {
        Job job = new Job();
        job.setTask(task);
        job.setDelay(delay);
        job.setStartTime(System.currentTimeMillis() + delay);
        trigger.queue.offer(job);
        trigger.wakeUp();
    }

    // 拿到任务队列里的任务，任务开启时间如果小于当前时间，则阻塞
    class Trigger {
        PriorityQueue<Job> queue = new PriorityQueue<>();

        Thread thread = new Thread(() -> {
            while (true) {
                while (queue.isEmpty()) {
                    LockSupport.park();
                }
                // 判断队列头是否可以开启执行
                Job peek = queue.peek();
                if (peek.getStartTime() - System.currentTimeMillis() < 0) {
                    // 多线程环境下，可以新加入任务
                    Job poll = queue.poll();
                    executorService.execute(poll.getTask());
                    // 提交下一次执行的任务
                    Job job = new Job();
                    job.setTask(poll.getTask());
                    job.setDelay(poll.getDelay());
                    job.setStartTime(System.currentTimeMillis() + poll.getDelay());
                    queue.offer(job);
                } else {
                    LockSupport.parkUntil(peek.getStartTime());
                }
            }
        });

        // 开启线程
        {
            thread.start();
        }

        void wakeUp() {
            LockSupport.unpark(thread);
        }
    }
}
