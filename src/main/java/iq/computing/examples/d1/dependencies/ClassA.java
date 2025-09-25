package iq.computing.examples.d1.dependencies;

import org.springframework.stereotype.Component;

@Component
public class ClassA{
    public ClassA() {
        System.out.println("Lazy Initialization - ClassA");
    }
}
