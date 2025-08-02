package com.gm.study.designPattern.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author: xexgm
 */
public class User implements Iterable<String> {

    private String name;

    private int age;

    @Override
    public Iterator<String> iterator() {
        return new UserIte();
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    private class UserIte implements Iterator<String> {

        // 要迭代的东西的数量，这里迭代两个属性
        int count = 2;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public String next() {
            // 要获取下一个东西，count先--
            count --;

            if (count == 1) {
                return User.this.name;
            }
            if (count == 0) {
                return User.this.age + "";
            }
            // 迭代完了，返回什么呢？点回方法的定义看一下啊
            throw new NoSuchElementException();
        }
    }

}
