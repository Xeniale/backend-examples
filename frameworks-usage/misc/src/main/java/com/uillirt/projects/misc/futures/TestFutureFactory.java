package com.uillirt.projects.misc.futures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class TestFutureFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TestFutureFactory.class);

    public static <T> CompletableFuture<T> createSuccessfulFuture(T t) {
        CompletableFuture<T> future = new CompletableFuture<>();
        LOG.info("Got class with name: " + t.getClass().getName());
        future.complete(t);
        return future;
    }

    public static <T> CompletableFuture<T> createExceptionalFuture(T t) {
        CompletableFuture<T> future = new CompletableFuture<>();
        LOG.info("Got class with name: " + t.getClass().getName());
        future.completeExceptionally(new Exception("Create Exceptional Future exception!"));
        return future;
    }
}
