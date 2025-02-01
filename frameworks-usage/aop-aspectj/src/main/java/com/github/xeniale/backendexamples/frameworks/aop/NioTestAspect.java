package com.github.xeniale.backendexamples.frameworks.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by kshekhovtsova on 13.12.2015.
 */
@Aspect
public class NioTestAspect {

    @Around("execution(* com.github.xeniale.backendexamples.testing.nio.TestClass.test())")
    public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Aop: start nio test");
        Object result = pjp.proceed();
        System.out.println("Aop: end nio test");
        return result;
    }
}
