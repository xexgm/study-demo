package com.gm.study.net.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: xexgm
 * @date: 2025/8/31
 * des: bio 服务器
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        // 服务端socket，监听8080
        ServerSocket serverSocket = new ServerSocket(8080);
        // 阻塞accpet，等待客户端连接
        Socket accept = serverSocket.accept();
        // 拿到 socket 的 inputStream
        InputStream inputStream = accept.getInputStream();
        
        byte[] buffer = new byte[1024];
        int length;
        
        while ((length = inputStream.read(buffer)) != -1) {
            String message = new String(buffer, 0, length);
            System.out.println(message);
        }

        System.out.println("客户端断开连接");
    }
    
}
