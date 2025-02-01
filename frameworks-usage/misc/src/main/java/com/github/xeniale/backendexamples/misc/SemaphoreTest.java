package com.github.xeniale.backendexamples.misc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws Exception {
        int perm;
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        perm = semaphore.availablePermits();
        System.out.println("Sem acq. Permits: " + perm);
        semaphore.release();
        perm = semaphore.availablePermits();
        System.out.println("Sem released 1. Permits: " + perm);
        semaphore.release();
        System.out.println("Sem released 2. Permits: " + semaphore.availablePermits());
        semaphore.acquire();
        System.out.println("Sem acq 1. Permits: " + semaphore.availablePermits());
        semaphore.acquire();
        System.out.println("Sem acq 2. Permits: " + semaphore.availablePermits());
        semaphore.acquire();
        System.out.println("Sem acq 3. Permits: " + semaphore.availablePermits());
    }
}

