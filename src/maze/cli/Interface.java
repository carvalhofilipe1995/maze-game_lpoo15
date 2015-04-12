package maze.cli;

import maze.logic.Game;

import java.util.Scanner;

public class Interface {

    protected static Game game;
    static Scanner scan = new Scanner(System.in);

    public static void printMenu() {
        System.out.print(">>>>>>>>>> The Labirinth >>>>>>>>>>\n\n");
        System.out.print("\t1. Play\n");
        System.out.print("\t2. Quit\n");
        System.out.print("\n\tOption -> ");
    }

    public static void createGame() {

        int size = -1;
        int number_dragons = -1;
        int type_dragons = -1;
        int maze_preferences = -1;

        System.out.print("\n>>>>>>>>>>>>> Options >>>>>>>>>>>>>>\n\n");
        System.out.print("---> Choose which maze do you want to play: "
                + "\n\n\t1. Default\n\t2. Generate a new Maze   \n");

        while (maze_preferences != 1 && maze_preferences != 2) try {
            System.out.print("\n\tOption -> ");
            maze_preferences = Integer.parseInt(scan.next());
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }

        if (maze_preferences == 2) {

            while (size < 5 || size % 2 == 0) try {
                System.out
                        .print("\n---> Maze size (odd value between 5 and 99):  ");
                size = Integer.parseInt(scan.next());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            while (number_dragons == -1) try {
                System.out.print("\n---> Number of dragons:   ");
                number_dragons = Integer.parseInt(scan.next());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            System.out.print("\n\n---> Type of dragons: \n"
                    + "\n\t1. Static\n"
                    + "\n\t2. Roam\n"
                    + "\n\t3. Roam and Sleep\n");
            while (type_dragons < 1 || type_dragons > 3) try {
                System.out.print("\n\tOption -> ");
                type_dragons = Integer.parseInt(scan.next());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }

        switch (maze_preferences) {
            case 1:
                game = new Game();
                game.initializePositionsElements(2);
                break;
            case 2:
                game = new Game(size, size, number_dragons, type_dragons);
                game.initializePositionsElements(number_dragons);
                break;
            default:
                System.out.print("\n    Invalid input! Try again! \n");
                break;
        }

    }

    public static void updateGame() {

        System.out.print("\n\n");

        game.printGame();

        do {

            System.out.print("\n  Direction ( w/s || a/d || e ) -> ");
            String direction = scan.next();

            switch (direction) {
                case "e":
                    if (game.getHero().hasDarts()) {
                        System.out.print("\n\n   -> Direction: ");
                        String darts = scan.next();
                        game.checkDartsDirection(darts);
                    } else {
                        System.out.print("\n\n   Hero doesn't have darts!  \n\n\n");
                    }
                    game.printGame();
                    break;
                default:
                    System.out.print("\n\n");
                    game.updateGameState(direction);
                    game.checkIfDragonIsNear();
                    game.printGame();
            }


        } while (!game.getGameState() && game.getHero().isAlive());

    }

    public static void main(String args[]) {
        int option = 0;
        boolean done = false;

        printMenu();
        while (!done) {
            try {
                option = Integer.parseInt(scan.next());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
            switch (option) {
                case 1:
                    done = true;
                    createGame();
                    updateGame();
                    if (game.getHero().isAlive()) {
                        System.out.print("\n"
                                + "-------- Congratulations! You Win! ----------\n\n");
                    } else {
                        System.out.print("\n"
                                + "-------- Try Again! You Lose! ----------\n\n");
                    }
                    break;
                case 2:
                    System.out.print("\n    Quitting game! \n");
                    return;
                default:
                    System.out.print("\n\n 	Invalid input! Try again!\n");
                    break;
            }

        }
        scan.close();
    }

}
