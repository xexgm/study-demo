package com.gm.study.designPattern.decorator.fileIO;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: xexgm
 */
public class RecordReadCountStream extends InputStream {


    private int readCount = 0;

    private final InputStream inputStream;

    public RecordReadCountStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    @Override
    public int read() throws IOException {
        int read = inputStream.read();
        readCount++;
        return read;
    }

    public int getReadCount() {
        return readCount;
    }
}
