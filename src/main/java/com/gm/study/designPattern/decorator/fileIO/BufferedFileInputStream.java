package com.gm.study.designPattern.decorator.fileIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: xexgm
 */
public class BufferedFileInputStream extends InputStream {

    // 缓冲区的大小，可以通过构造函数传进来,这里就直接简写了
    private final byte[] buffer = new byte[8192];

    private final FileInputStream fileInputStream;

    // 指向可读的位置，初始化为-1
    private int position = -1;

    // 当前缓冲区中实际的内容大小
    private int capacity = -1;

    public BufferedFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        // 如果缓冲区可读，返回内容
        if (bufferCanRead()) {
            return readFromBuffer();
        }
        // 缓冲区不可读，刷新缓冲区
        refreshBuffer();
        // 判断是否可读，如果不可读，返回-1
        if (!bufferCanRead()) {
            return -1;
        }
        // 已经可读，返回内容
        return readFromBuffer();
    }

    private int readFromBuffer() {
        // & 上 0xff,表示0 - 255的数，因为 FileInputStream 的read方法，返回的就是 0-255的数
        return buffer[position++] & 0xff;
    }

    private void refreshBuffer() throws IOException {
        // 将内容读到缓冲区中，的实际大小
        capacity = fileInputStream.read(buffer);
        position = 0;
    }

    private boolean bufferCanRead() {
        // 如果实际内容大小没有，则不可读
        if (capacity == -1) {
            return false;
        }

        // 如果position已经读到头了，就不可读
        if (position == capacity) {
            return false;
        }

        return true;
    }

}
