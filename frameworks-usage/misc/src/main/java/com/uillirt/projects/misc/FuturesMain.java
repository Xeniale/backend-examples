package com.uillirt.projects.misc;

import com.uillirt.projects.misc.futures.TestFutureFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class FuturesMain {

    private static final Logger LOG = LoggerFactory.getLogger(FuturesMain.class);

    public static void main(String[] args) {
//        .thenCombine(secondSuccessFuture, fn).exceptionally(throwable -> {
//            resFuture.completeExceptionally(throwable);
//            return null;
//        })

        CompletableFuture<Long> firstSuccessFuture = TestFutureFactory.createSuccessfulFuture(1L);
        CompletableFuture<Long> secondSuccessFuture = TestFutureFactory.createSuccessfulFuture(2L);
        CompletableFuture<Long> exceptionalFuture = TestFutureFactory.createExceptionalFuture(3L);
        combineTest(firstSuccessFuture, secondSuccessFuture).whenComplete((res, e) -> {
            if (e != null) {
                LOG.error("Error occurred", e);
                return;
            }
            LOG.info("Got result: " + res);
        });
    }

    private static CompletableFuture<Long> combineTest(CompletableFuture<Long> firstSuccessFuture, CompletableFuture<Long> secondSuccessFuture) {
        BiFunction<Long, Long, Long> fn = (val1, val2) -> {
            LOG.info("Sum vals: {} {}", val1, val2);
            return val1 + val2;
        };

        CompletableFuture<Long> resFuture = new CompletableFuture<>();
        firstSuccessFuture.thenApply(val -> {
            if (val == 1L) {
                resFuture.completeExceptionally(new Exception("Value can't be equal to 1L"));
                return null;
            }
            return val;
        });
        return resFuture;
    }

}
