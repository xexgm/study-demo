package com.gm.study.designPattern.decorator.fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;

/**
 * @Author: xexgm
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("pom.xml");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedFileInputStream bufferedFileInputStream = new BufferedFileInputStream(new FileInputStream(file));
             RecordReadCountStream recordReadCountStream = new RecordReadCountStream(new BufferedFileInputStream(new FileInputStream(file)))) {

            long start = Instant.now().toEpochMilli();

            while (true) {
                //int read = fileInputStream.read();
                int bufferRead = bufferedFileInputStream.read();
                int recordRead = recordReadCountStream.read();

                if (recordRead != bufferRead) {
                    throw new RuntimeException();
                }

                if (recordRead == -1) {
                    break;
                }
            }

            System.out.println("用时: " + (Instant.now().toEpochMilli()-start) + " 毫秒" + " ，调用了 " + recordReadCountStream.getReadCount() + " 次 read() 方法");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
