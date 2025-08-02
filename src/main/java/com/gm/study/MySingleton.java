package com.gm.study;

/**
 * @Author: xexgm
 */
public class MySingleton {


    private static MySingleton singleton = new MySingleton();

    // 私有构造器
    private MySingleton() {

    }

    public static MySingleton getInstance() {
        return singleton;
    }
}
