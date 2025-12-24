package com.gm.study.net.serialize;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @Author: xexgm
 * des: 序列化与反序列化
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // 创建一个User对象，赋值属性
        User gm = new User("xexgm", 22);
        // 序列化
        byte[] bytes = serializeUser2(gm);
        // 创建文件
        File file = new File("user.gm");
        // 写入文件
        Files.write(file.toPath(), bytes);
        System.out.println(file.length());

        // 从文件中读取
        byte[] readAllBytes = Files.readAllBytes(file.toPath());
        // 反序列化
        User user = deserializeUser2(readAllBytes);
        // 打印
        System.out.println(user);
    }

    // 序列化方法 ->  自定义的序列化协议（JSON是标准的序列化协议）
    private static byte[] serializeUser(User user) {
        // User对象里，有一个 String 的name，有一个 int 的age
        // name的字符长度是不固定的

        // 获取name的字节数组
        byte[] nameBytes = user.getName().getBytes(StandardCharsets.UTF_8);
        // 分配 缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES + nameBytes.length);
        // 往缓冲区里塞东西
        byteBuffer.putInt(user.getAge());
        byteBuffer.put(nameBytes);
        // 返回字节数组
        return byteBuffer.array();
    }

    // 反序列化方法
    private static User deserializeUser(byte[] bytes) {
        /**
         * 使用 ByteBuffer.wrap() 的原因：
         *   1. 不是为了改变数据的存储位置（数据始终在堆中）
         *   2. 而是为了提供便捷的读取 API，自动管理读取位置、处理字节序、类型转换等
         *   3. 相当于给原始字节数组套了一个"智能读取器"
         *
         *   这就像你有一本书（字节数组），ByteBuffer 就是一个书签（position），帮你记住读到哪里了，而不需要你手动记录页码。
         */
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        // 先读取 int
        int age = byteBuffer.getInt();
        // 再读取字节数组剩下的内容
        byte[] nameBytes = new byte[byteBuffer.remaining()];
        // 把缓冲区中的数据，读入字节数组
        byteBuffer.get(nameBytes);
        User user = new User();
        user.setAge(age);
        user.setName(new String(nameBytes, StandardCharsets.UTF_8));
        return user;
    }


    // 固定长度前缀，解决粘包拆包
    private static byte[] serializeUser2(User user) {
        // User对象里，有一个 String 的name，有一个 int 的age
        // name的字符长度是不固定的

        // 获取name的字节数组
        byte[] nameBytes = user.getName().getBytes(StandardCharsets.UTF_8);
        // 分配 缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES*2 + nameBytes.length);
        // 往缓冲区里塞东西
        byteBuffer.putInt(user.getAge());
        // 先写不定长String的长度，再写内容
        byteBuffer.putInt(nameBytes.length);
        byteBuffer.put(nameBytes);
        // 返回字节数组
        return byteBuffer.array();
    }

    private static User deserializeUser2(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        // 先读取 int
        int age = byteBuffer.getInt();
        int stringLength = byteBuffer.getInt();
        // 把缓冲区中的数据，读入字节数组
        byte[] nameBytes = new byte[stringLength];
        byteBuffer.get(nameBytes);
        User user = new User();
        user.setAge(age);
        user.setName(new String(nameBytes, StandardCharsets.UTF_8));
        return user;
    }
}
