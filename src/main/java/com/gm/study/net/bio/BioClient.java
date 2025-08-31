package com.gm.study.net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author: xexgm
 * @date: 2025/8/31
 * des: bio 客户端
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8080));

        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write(("hello" + i).getBytes());
            // 冲一下马桶
            outputStream.flush();
        }
        socket.close();
    }
}
