package com.github.xeniale.backendexamples.frameworks.rx.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kshekhovtsova on 23.07.2015.
 */
public class Nameless {
    private static Map<Integer, NamelessInterface> METHODS_MAPPING;
    private static final Logger LOGGER = LoggerFactory.getLogger(Nameless.class);

    public Nameless() {
        METHODS_MAPPING = new HashMap<Integer, NamelessInterface>(){{
            put(1, (String) -> {method1(String);});
            put(2, (String) -> {method2(String);});
        }};
    }

    public void run(int p, String param) {
        METHODS_MAPPING.get(p).invoke(param);
    }

    private void method1(String param) {
        String threadInfo = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
        LOGGER.info("method1 invoked: {}. Info: {}", param, threadInfo);
    }

    private void method2(String param) {
        String threadInfo = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
        LOGGER.info("method2 invoked: {}. Info: {}", param, threadInfo);
    }
}
