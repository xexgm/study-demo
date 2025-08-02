package com.gm.study.designPattern.iterator.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Consumer;

import com.gm.study.designPattern.iterator.User;

/**
 * @Author: xexgm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // 一个简单的解析文件的逻辑
        // 现在不光要 打印这个user，还要把它放到list里
        List<User> userList = new ArrayList<>();


        // 存在两个问题
        // 1.每次执行函数式接口的操作，都要调用一下 readAllUser，不够优雅
        // 2.把 File 都读进来了，如果 File非常大，是不是会发生 OOM
        //readAllUser((user) -> {
        //    System.out.println(user);
        //    userList.add(user);
        //});

        UserFile userFile = new UserFile(new File("demo.user"));
        for (User user : userFile) {
            System.out.println(user);
        }

        //RandomAccessFile

    }

    // jdk8 提供的，函数式接口，传一个函数，对 user进行操作
    private static void readAllUser(Consumer<User> userConsumer) throws IOException {
        List<String> lines = Files.readAllLines(new File("demo.user").toPath());
        for (String line : lines) {
            String substring = line.substring(1, line.length() - 1);
            String[] split = substring.split(",");
            String name = split[0];
            int age = Integer.parseInt(split[1]);
            User user = new User(name, age);
            userConsumer.accept(user);
        }
    }
}
