package com.gm.study.ThreadPool;

/**
 * @Author: xexgm
 * @Date: 2025/1/28
 * @Description:
 */
public class DiscardRejectHandler implements RejectHandler {
    @Override
    public void reject(Runnable command, MyThreadPool threadPool) {
        threadPool.blockingQueue.poll();
        threadPool.execute(command);
    }
}
