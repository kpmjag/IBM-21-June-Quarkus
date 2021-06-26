package com.ibm.reative;

import io.smallrye.mutiny.Uni;

public class FirstMutiny {
    public static void main(String[] args) {
        //Uni
        String dataSource ="Hello Muntiny";
        Uni.createFrom()
                .item(dataSource) //data source
                .onItem().transform(item -> item) //trigger event - data event //up stream
                .onItem().transform(item->item.toUpperCase())  // down stream
                .subscribe().with(data -> System.out.println(data), err -> System.out.println(err));
    }
}
