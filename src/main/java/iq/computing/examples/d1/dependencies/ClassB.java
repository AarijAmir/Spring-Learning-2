package iq.computing.examples.d1.dependencies;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ClassB{
    private ClassA classA;

    public ClassB(ClassA classA) {
        // Complex initialization logic
        System.out.println("Some initialization logic");
        this.classA = classA;
    }
}