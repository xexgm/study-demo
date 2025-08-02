package com.gm.study.ThreadPool;

/**
 * @author xexgm
 */
public interface RejectHandler {
    void reject(Runnable command, MyThreadPool threadPool);
}
