package com.github.xeniale.backendexamples.frameworks.aop.service;

/**
 * Created by kshekhovtsova on 13.12.2015.
 */
public class AopTestService {

    public void sleepMethod(long sleepDurationMillis) throws Exception {
        System.out.println("Start to sleep");
        Thread.sleep(sleepDurationMillis);
        System.out.println("Ended sleeping");
    }

}
