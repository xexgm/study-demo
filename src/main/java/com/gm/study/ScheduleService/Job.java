package com.gm.study.ScheduleService;

/**
 * @Author: xexgm
 */
public class Job implements Comparable<Job>{

    private Runnable task;

    private long startTime;

    private int delay;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }



    @Override
    public int compareTo(Job o) {
        return Long.compare(this.getStartTime(), o.getStartTime());
    }
}
