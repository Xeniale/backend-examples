package com.uillirt.projects.testing.nio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kshekhovtsova on 18.06.2015.
 */
public class NonNioFileReader extends AbstractFileReader {
    @Override
    long readFile(String filePath) {
        long t1 = 0L;
        long t2 = 0L;
        StringBuilder builder = new StringBuilder();
        String sCurrentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            t1 = System.nanoTime();
            while ((sCurrentLine = br.readLine()) != null) {
                builder.append(sCurrentLine);
            }
            t2 = System.nanoTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = (t2-t1)/1000000;
        System.out.println("Non nio time: " + time);
        return time;
    }

}
