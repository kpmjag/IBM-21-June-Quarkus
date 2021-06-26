package com.ibm.reative;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.Cancellable;

import java.time.Duration;
import java.util.Arrays;

public class MultiMutiny {
    public static void main(String[] args) {
        Multi.createFrom().items(1, 2, 3, 4, 5, 6, 7)
                .onItem().transform(i -> i * 2)
                .subscribe().with(System.out::println);

        Multi.createFrom().iterable(Arrays.asList(1, 2, 3, 4, 5))
                .onItem().transform(i -> i * 2)
                .subscribe().with(System.out::println);


        Multi.createFrom().range(1, 25)
                .onItem().transform(i -> i * 2)
                .subscribe().with(System.out::println);

        Multi.createFrom().failure(new Exception("boom"))
                .onFailure().recoverWithItem(() -> "cached data")
                .subscribe().with(System.out::println);

        //custom logic if want send data
        Multi<Integer> multi = Multi.createFrom().emitter(em -> {
            em.emit(1); //pushing value
            em.emit(2); //pushing
            //em.fail(new RuntimeException("something went wrong"));
            em.emit(3);
            em.complete(); //triggering complete
        });
        multi.subscribe().with(System.out::println, Throwable::printStackTrace, () -> System.out.println("done"));


        //infite stream
        Multi<Long> ticks = Multi.createFrom().ticks().every(Duration.ofMillis(100))
        Cancellable cancellable = ticks.subscribe().with(t -> System.out.println("Tick>>> " + t));

        try {
            Thread.sleep(2000);
            System.out.println("stopped sending data");
            cancellable.cancel();

        } catch (Exception e) {

        }

    }
}
