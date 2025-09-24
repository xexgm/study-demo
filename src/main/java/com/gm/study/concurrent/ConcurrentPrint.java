package com.gm.study.concurrent;

/**
 * @author: xexgm
 * @date: 2025/9/24
 * des: 三个线程轮流打印 ABC，打印十组，然后退出
 */
public class ConcurrentPrint {

    // ABC 三个数
    static int count = 3;
    // 轮次计数
    static int num = 1;
    // 锁对象
    static Object lock = new Object();

    public static void main(String[] args) {
        PrintThread a = new PrintThread('A', 1);
        PrintThread b = new PrintThread('B', 2);
        PrintThread c = new PrintThread('C', 0);

        new Thread(a,"printA").start();
        new Thread(b,"printB").start();
        new Thread(c,"printC").start();

    }

    // A -> 1, B -> 2, C -> 0
    static class PrintThread implements Runnable {

        // 打印的字符
        char printObject;

        // 标识是否是自己的回合
        int targetNum;

        public PrintThread(char printObject, int targetNum) {
            this.printObject = printObject;
            this.targetNum = targetNum;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 抢锁
                synchronized (lock) {
                    // 判断是否是自己的回合
                    while (num % count != targetNum) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // 属于自己的回合，打印

                    // 如果为C，就换行
                    if (num % count == 0) {
                        System.out.println(printObject);
                    } else {
                        System.out.print(printObject);
                    }
                    num ++;
                    lock.notifyAll();
                }
            }
        }
    }


}

