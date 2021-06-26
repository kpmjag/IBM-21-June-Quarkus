///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.smallrye.reactive:mutiny:0.15.0
package _03_composition_transformation;

import static java.util.concurrent.CompletableFuture.delayedExecutor;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public class _11_Multi_Concatenate {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("⚡️ Multi concatenation");

        var firstGenerator = new Generator(0);
        var secondGenerator = new Generator(100);
        CountDownLatch latch = new CountDownLatch(1);

        var first = Multi.createBy().repeating()
                .uni(firstGenerator::next).atMost(10);

        var second = Multi.createBy().repeating()
                .uni(secondGenerator::next).atMost(10);

        Multi.createBy().concatenating().streams(first, second)
                .onTermination().invoke(latch::countDown)
                .subscribe().with(System.out::println);

        latch.await();
    }

    private static class Generator {
        final AtomicLong aLong;

        Generator(long start) {
            aLong = new AtomicLong(start);
        }

        Uni<Long> next() {
            return Uni.createFrom().completionStage(
                    supplyAsync(
                            aLong::getAndIncrement,
                            delayedExecutor(ThreadLocalRandom.current().nextInt(500), TimeUnit.MILLISECONDS)));
        }
    }
}
