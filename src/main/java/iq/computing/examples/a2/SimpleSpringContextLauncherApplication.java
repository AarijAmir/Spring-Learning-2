package iq.computing.examples.a2;

import iq.computing.game.GameRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("iq.computing.game")
public class SimpleSpringContextLauncherApplication {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (SimpleSpringContextLauncherApplication.class)){
            context.getBean(GameRunner.class).run();

            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
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