package com.gm.study.designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: xexgm
 */
public class Main {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user = new User("zhangsan", 11);
        for (String s : user) {
            System.out.println(s);
        }
        userList.add(user);

        for (User user1 : userList) {
            if (user1.getAge() == 11) {
                userList.add(new User("lisi", 12));
            }
        }
    }
}
