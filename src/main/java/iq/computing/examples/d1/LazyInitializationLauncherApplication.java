package iq.computing.examples.d1;


import iq.computing.examples.d1.dependencies.ClassA;
import iq.computing.examples.d1.dependencies.ClassB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class BusinessLogic{
    ClassA classA;
    ClassB classB;

    // I must have to not use classB here to be lazyily initialization. for this I have to use a constructor,
    // so it cannot be able to load the dependency of classB, bcz it is autowiring the dependency and it is eagerly initialized
//    public BusinessLogic(ClassA classA, ClassB classB) {
//        super();
//        System.out.println("Constructor for both - Class-A & Class-B =" + classA + "-" + classB);
//        this.classA = classA;
//        this.classB = classB;
//    }

// if above constructor is executed then result: ->
//Lazy Initialization - ClassA
//    Some initialization logic
//    Constructor for both - Class-A & Class-B =iq.computing.examples.d1.dependencies.ClassA@441772e-iq.computing.examples.d1.dependencies.ClassB@7334aada
// if below constructor is executed then result: ->
//    Lazy Initialization - ClassA
//    Constructor for both - Class-A & Class-B =iq.computing.examples.d1.dependencies.ClassA@dbf57b3-null

    public BusinessLogic(ClassA classA) {
        super();
        System.out.println("Constructor for both - Class-A & Class-B =" + classA + "-" + classB);
        this.classA = classA;
    }

    @Override
    public String toString() {
        return new StringBuffer().
                append("Class-A = ").
                append(classA).
                append(",Class-B = ").
                append(classB).
                toString();
    }
}

@Configuration
@ComponentScan("iq.computing.examples.d1")
public class LazyInitializationLauncherApplication {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (LazyInitializationLauncherApplication.class)){
            // When I use this lazily-initialized bean, then only it will be created.
            context.getBean(ClassB.class);
//            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//            System.out.println(context.getBean(BusinessLogic.class));
        }
    }
}

/*
// Why exception when constructor overloaded.
package iq.computing.examples.a1;


import iq.computing.examples.a1.dependencies.DependencyOne;
import iq.computing.examples.a1.dependencies.DependencyTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BusinessLogic{
    DependencyOne dependencyOne;
    DependencyTwo dependencyTwo;
    @Autowired
    public BusinessLogic(DependencyOne dependencyOne) {
        super();
        System.out.println("Constructor - One =" + dependencyOne);
        this.dependencyOne = dependencyOne;
    }
    @Autowired
    public BusinessLogic(DependencyTwo dependencyTwo) {
        super();
        System.out.println("Constructor - DependencyTwo =" + dependencyTwo);
        this.dependencyTwo = dependencyTwo;
    }
    @Autowired
    public BusinessLogic(DependencyOne dependencyOne, DependencyTwo dependencyTwo) {
        super();
        System.out.println("Constructor for both - Dependency One & DependencyTwo =" + dependencyOne + "-" + dependencyTwo);
        this.dependencyOne = dependencyOne;
        this.dependencyTwo = dependencyTwo;
    }

    @Override
    public String toString() {
        return new StringBuffer().
                append("Dependency-One = ").
                append(dependencyOne).
                append(",Dependency-Two = ").
                append(dependencyTwo).
                toString();
    }
}

@Configuration
@ComponentScan
public class DependencyInjectionLauncherApplication {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (DependencyInjectionLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(BusinessLogic.class));
        }
    }
}
*/

/*
// Setter-Based Injection
package iq.computing.examples.a1;


import iq.computing.examples.a1.dependencies.DependencyOne;
import iq.computing.examples.a1.dependencies.DependencyTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BusinessLogic{
    DependencyOne dependencyOne;
    DependencyTwo dependencyTwo;

    // Setter-Based Injection
    @Autowired
    void setDependencyOne(DependencyOne dependencyOne){
        this.dependencyOne = dependencyOne;
    }
    // Setter-Based Injection
    @Autowired
    void setDependencyTwo(DependencyTwo dependencyTwo){
        this.dependencyTwo = dependencyTwo;
    }

    @Override
    public String toString() {
        return new StringBuffer().
                append("Dependency-One = ").
                append(dependencyOne).
                append(",Dependency-Two = ").
                append(dependencyTwo).
                toString();
    }
}

@Configuration
@ComponentScan
public class DependencyInjectionLauncherApplication {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (DependencyInjectionLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(BusinessLogic.class));
        }
    }
}
*/

/*
// Using separate package for Beans
package iq.computing.examples.a1;

import iq.computing.examples.a1.dependencies.DependencyOne;
import iq.computing.examples.a1.dependencies.DependencyTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BusinessLogic{
    @Autowired
    DependencyOne dependencyOne;
    @Autowired
    DependencyTwo dependencyTwo;

    @Override
    public String toString() {
        return new StringBuffer().
                append("Dependency-One = ").
                append(dependencyOne).
                append(",Dependency-Two = ").
                append(dependencyTwo).
                toString();
    }
}

@Configuration
@ComponentScan("iq.computing.examples.a1")
public class DependencyInjectionLauncherApplication {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (DependencyInjectionLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(BusinessLogic.class));
        }
    }
}
/*

/*

// 37
package iq.computing;

import iq.computing.game.GameRunner;
import iq.computing.game.GamingConsole;
import iq.computing.game.PacmanGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App03GamingSpringBeans {
    @Bean
    public GamingConsole game(){
        return new PacmanGame();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game){
        return new GameRunner(game);
    }

    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (App03GamingSpringBeans.class)){
            context.getBean(GameRunner.class).run();
        }
    }
}



/*
// Business-Logic and Dependencies in the same file
package iq.computing.examples.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BusinessLogic{
    @Autowired
    DependencyOne dependencyOne;
    @Autowired
    DependencyTwo dependencyTwo;

    @Override
    public String toString() {
        return new StringBuffer().
                append("Dependency-One = ").
                append(dependencyOne).
                append(",Dependency-Two = ").
                append(dependencyTwo).
                toString();
    }
}
@Component
class DependencyOne{

}
@Component
class DependencyTwo{

}
@Configuration
@ComponentScan
public class DependencyInjectionLauncherApplication {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (DependencyInjectionLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(BusinessLogic.class));
        }
    }
}

/*

// 37
package iq.computing;

import iq.computing.game.GameRunner;
import iq.computing.game.GamingConsole;
import iq.computing.game.PacmanGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App03GamingSpringBeans {
    @Bean
    public GamingConsole game(){
        return new PacmanGame();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game){
        return new GameRunner(game);
    }

    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (App03GamingSpringBeans.class)){
            context.getBean(GameRunner.class).run();
        }
    }
}
*/