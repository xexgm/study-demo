package com.gm.study.designPattern.chain;

import com.gm.study.designPattern.chain.anotation.Length;
import com.gm.study.designPattern.chain.anotation.Max;
import com.gm.study.designPattern.chain.anotation.Min;

/**
 * @Author: xexgm
 */
public class User {

    @Length(4)
    private String name;

    @Max(20)
    @Min(30)
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
