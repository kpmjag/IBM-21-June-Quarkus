package com.ibm.reative;

import io.smallrye.mutiny.Uni;

import java.time.Duration;


public class UniDelay {
    public static void main(String[] args) {
        System.out.println("start");
        Uni.createFrom().item(1)
                .onItem().transform(i -> "hello-" + i)
                .onItem().delayIt().by(Duration.ofMillis(1000))
                .subscribe().with(System.out::println);
        System.out.println("end");
    }
}
