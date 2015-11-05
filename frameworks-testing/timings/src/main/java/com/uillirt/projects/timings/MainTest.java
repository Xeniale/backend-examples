package com.uillirt.projects.timings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vimpelcom.util.Timings;

/**
 * Created by kshekhovtsova on 14.07.2015.
 */
public class MainTest {
    private static Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {
        try {
            superMethod();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void superMethod() throws Exception {
        Timings timings = new Timings("testing supermethos", 1L, LOGGER);
        timings.takeReadings("before sleep");
        Thread.sleep(1000L);
        timings.takeReadings("after sleep");
        timings.report();
    }
}
