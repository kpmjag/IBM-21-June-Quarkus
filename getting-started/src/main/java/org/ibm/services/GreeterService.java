package org.ibm.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class GreeterService {

    @Inject
    HelloService helloService;

    private HaiService haiService;

    public GreeterService(){

    }

    @Inject
    public GreeterService(HaiService haiService){
        this.haiService = haiService;
    }
    //new GreeterService(new HaiService())

    @PostConstruct
    public void init() {
        System.out.println("GreeterService is init");
    }

    public String sayHello() {
        return helloService.sayHello()  + haiService.sayHai();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Greeter Service destroy is called");
    }
}
