package com.github.xeniale.backendexamples.testing.nio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kshekhovtsova on 18.06.2015.
 */
public class NioMain {
    private static final String PATH = "C:\\Users\\kshekhovtsova\\Documents\\file.txt";
    private static final int CNT = 10;
    public static void main(String[] args) {
        List<Long> times = new ArrayList<>();
        NioFileReader nioReader = new NioFileReader();
        NonNioFileReader nonNioReader = new NonNioFileReader();
        for (int i = 0; i < CNT; i++) {
            times.add(nonNioReader.readFile(PATH));
        }
        System.out.print("middle time: " + countHalfValue(times));
    }

    private static long countHalfValue(List<Long> values) {
        long sum = 0L;
        for(Long value : values) {
           sum += value;
        }
        return sum/values.size();
    }
 }
