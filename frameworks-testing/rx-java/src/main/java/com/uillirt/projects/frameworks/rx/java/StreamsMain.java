package com.uillirt.projects.frameworks.rx.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by kshekhovtsova on 27.07.2015.
 */
public class StreamsMain {

    public static void main(String[] args) {
        /* public Stream<String> lines() {
        Iterator<String> iter = new Iterator<String>() {
            String nextLine = null;

            @Override
            public boolean hasNext() {
                if (nextLine != null) {
                    return true;
                } else {
                    try {
                        nextLine = readLine();
                        return (nextLine != null);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
            }

            @Override
            public String next() {
                if (nextLine != null || hasNext()) {
                    String line = nextLine;
                    nextLine = null;
                    return line;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }*/

//        ElementIterator iter = new ElementIterator();
//        Stream<String> stringStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
//                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
//        stringStream.forEach(System.out::println);
        try {
            Files.write(Paths.get("C:\\Users\\kshekhovtsova\\Desktop\\tempor\\tmp.txt"), new ElementIterable());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
