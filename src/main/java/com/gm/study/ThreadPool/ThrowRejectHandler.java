package com.gm.study.ThreadPool;

/**
 * @author xexgm
 */
public class ThrowRejectHandler implements RejectHandler {
    @Override
    public void reject(Runnable command, MyThreadPool threadPool) {
        throw new RuntimeException("阻塞队列已经满了");
    }
}
