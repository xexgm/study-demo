package com.gm.study.ScheduleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: xexgm
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
        Schedule scheduleService = new Schedule();
        scheduleService.schedule(() -> {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter)+ " 这是100ms一次");
        }, 100);

        Thread.sleep(1000);

        scheduleService.schedule(() -> {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " 这是200ms一次");
        }, 200);
    }
}
