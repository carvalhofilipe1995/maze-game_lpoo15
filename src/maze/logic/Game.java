package Logic;

import java.util.Scanner;

public class Game {
    private static Maze maze;
    private static Hero hero;
    private static Sword sword;
    private static Dragon dragon;
    private static int gameState;

    public Game(){

    }
    static void run(){
        maze.draw();
        do {
            hero.move();
            System.out.print("\n");
            maze.draw();
        } while (gameState == 0);
    }

    public static void main(String args[]){
        new Game();
        run();
    }

}
