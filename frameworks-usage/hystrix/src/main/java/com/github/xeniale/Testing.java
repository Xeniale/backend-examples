package com.github.xeniale;

import com.netflix.hystrix.HystrixCircuitBreaker;

public class Testing {

    public static void main(String[] args) {
        HystrixCircuitBreaker cb = new HystrixCircuitBreaker.NoOpCircuitBreaker();
        cb.isOpen();
    }
}
