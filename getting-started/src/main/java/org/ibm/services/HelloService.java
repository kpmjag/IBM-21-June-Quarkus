package org.ibm.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

//@ApplicationScoped
@Singleton
public class HelloService {
    public String sayHello() {
        return "Hello Service!!!";
    }
}
