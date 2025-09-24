package iq.computing.examples.a1;


import iq.computing.examples.a1.dependencies.DependencyOne;
import iq.computing.examples.a1.dependencies.DependencyTwo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BusinessLogic{
    DependencyOne dependencyOne;
    DependencyTwo dependencyTwo;

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