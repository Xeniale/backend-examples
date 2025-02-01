package com.github.xeniale.backendexamples.testing.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by kshekhovtsova on 18.06.2015.
 */
public class NioFileReader extends AbstractFileReader {
    @Override
    long readFile(String filePath) {
        long t1 = 0L;
        long t2 = 0L;
        StringBuilder builder = new StringBuilder();
        try (RandomAccessFile aFile = new RandomAccessFile(filePath, "r");
             FileChannel inChannel = aFile.getChannel();) {
            t1 = System.nanoTime();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            buffer.load();
            for (int i = 0; i < buffer.limit(); i++) {
                builder.append((char) buffer.get());
            }
            buffer.clear();
            t2 = System.nanoTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = (t2-t1)/1000000;
        System.out.println("Nio time: " + time);
        return time;
    }
}
