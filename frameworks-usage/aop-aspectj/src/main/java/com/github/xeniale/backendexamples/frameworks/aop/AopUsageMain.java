package com.github.xeniale.backendexamples.frameworks.aop;

import com.github.xeniale.backendexamples.frameworks.aop.service.AopTestService;
import com.github.xeniale.backendexamples.testing.nio.TestClass;

/**
 * Created by kshekhovtsova on 13.12.2015.
 */
public class AopUsageMain {

    private static final AopTestService testService = new AopTestService();
    private static final TestClass testClass = new TestClass();

    public static void main(String[] args) throws Exception {
        System.out.println("AopUsageMain started");
        testService.sleepMethod(2000);
        System.out.println("AopUsageMain stopping");
        testClass.test();
    }
}
