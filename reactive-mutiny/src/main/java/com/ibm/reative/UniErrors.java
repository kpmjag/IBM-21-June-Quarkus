package com.ibm.reative;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class UniErrors {

    public static String fallback() {
        return "data from caching server";
    }

    public static String mybizlogic() {
        String something = "I have data";
        boolean foundError = true;
        if (foundError) {
            System.out.println("method is called in error state");
             throw new RuntimeException("Boom");
        }
        return something;
    }

    public static void main(String[] args) {
        Uni.createFrom()
                .item(UniErrors::mybizlogic)
                .onItem().transform(d -> d.toUpperCase())
                .onItem().transform(d -> d.replace(" ", "--"))
                .onFailure().retry().atMost(4)
                .onFailure().recoverWithItem(UniErrors::fallback)
                .subscribe().with((item) -> System.out.println(item), err -> System.out.println(err.getMessage()));

    }
}
