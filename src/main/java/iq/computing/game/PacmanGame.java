package iq.computing.game;

import org.springframework.stereotype.Component;

@Component
public class PacmanGame implements GamingConsole {
    public void up(){
        System.out.println("Pacman Jump");
    }
    public void down(){
        System.out.println("Pacman Go into the hole");
    }
    public void left(){
        System.out.println("Pacman Go Back");
    }
    public void right(){
        System.out.println("Pacman Accelerate");
    }
}
