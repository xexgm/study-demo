package com.gm.study.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: xexgm
 * @date: 2025/8/31
 * des: 使用 java nio 实现一个多路复用的服务器
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        // 打开一个 ServerSocketChannel 一条大马路
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置非阻塞
        ssc.configureBlocking(false);
        // ssc 绑定监听的端口
        ssc.bind(new InetSocketAddress("localhost", 8080));

        // 打开一个 selector
        Selector selector = Selector.open();
        // 要注册 selector，selector监听端口下连接事件，ACCEPT表示服务端接受客户端的连接
        // serverSocketChannel 只能监听一种事件，也就是 Accept，它没有读写能力
        // 所有的读写能力，都是由 socketChannel 提供的（小马路）
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // selector 监听多个channel，阻塞住。
            // ssc 是只有 Accept 的能力，但是它绑定的 selector，这个 selector是可以绑定多个channel的
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // 处理完这个key后，要把他从 迭代器里移除，这样才知道，这个key是被处理完了
                // 如果不remove调key，那它会一直留在 iterator中，要确保每次的事件，只被处理一次
                iterator.remove();

                // 如果是可连接事件
                if (key.isAcceptable()) {
                    // 如果是可连接事件，那 selectionKey 对应一个 ssc
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    // 通过 accpet，拿到客户端的 socketChannel
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);

                    // 把该 socketChannel 注册到 selector上，selector就可以监听该 channel 了
                    // 监听 socketChannel 的读事件
                    client.register(selector, SelectionKey.OP_READ);
                }

                // 通过 可连接事件，把 客户端的 socketChannel 注册到 selector 的可读事件，
                // 这样就可以处理读了（可以让其他的 selector，去监听读写事件，Netty就是，它的workerGroup，性能好）
                if (key.isReadable()) {
                    // 如果这个 selectionKey 是可读的，那它一定是 SocketChannel，因为 ssc 只能处理可连接
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 分配一个 byteBuffer，socketChannel的read，要读进 byteBuffer
                    // byteBuffer 是字节数组的封装而已
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    // 往 byteBuffer 里读
                    int length = channel.read(byteBuffer);

                    // 如果读到的 length 为-1，表示客户端关闭
                    if (length == -1) {
                        channel.close();
                    } else {
                        // byteBuffer 在 被channel往里写后，是一个写入状态，要把它转为 读取状态
                        byteBuffer.flip();
                        // 把 byteBuffer 里的字节，读出来,bytebuffer 还有多少字节
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        // 放到 字节数组 bytes 里
                        byteBuffer.get(bytes);

                        System.out.println(new String(bytes));
                    }

                }
            }
        }
    }

}
