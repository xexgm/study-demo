package com.gm.study.designPattern.chain;

/**
 * @Author: xexgm
 */
public class Main {
    public static void main(String[] args) throws Exception {
        User tom = new User("tom", 25);
        Validator validator = new Validator();
        validator.validate(tom);
    }
}
