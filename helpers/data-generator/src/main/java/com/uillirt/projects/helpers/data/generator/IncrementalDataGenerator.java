package com.uillirt.projects.helpers.data.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kshekhovtsova on 06.11.2015.
 */
public class IncrementalDataGenerator {
    public static void main(String[] args) throws IOException {
//        for (String arg : args) {
//            System.out.println("------- " + arg + " -------");
//        }
        if (args.length == 1 && "help".equalsIgnoreCase(args[0])) {
            System.out.println("Help.\n Arguments: filename, text to generate, start iterator num, end iterator num");
        } else if (args.length == 4) {
            Path path = Paths.get(args[0]);
            File file = path.toFile();
            if (file.exists()) {
                file.delete();
            }
            int start = Integer.valueOf(args[2]);
            int end = Integer.valueOf(args[3]);
            String data = args[1];
            List<String> generatedDataList = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                generatedDataList.add(String.format(data, i));
            }
            Files.write(path, generatedDataList, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } else {
            System.err.println("Invalid arguments. Try running with 'help' argument.");
        }
    }
}
