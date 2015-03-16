package maze.logic;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private static maze.cli.Cli cli = new maze.cli.Cli();

    private static Maze maze;
    private static Hero hero;
    private static Sword sword;
    private static Shield shield;
    private static ArrayList<Dragon> dragons;
    private static int gameState;

    public static boolean dragonsAlive() {
        for (Dragon dragon : dragons) {
            if (dragon.isAlive()) {
                return true;
            }
        }
        return false;
    }


    public static void moveHero(String input) {

        switch (input) {
            case "a":
                switch (maze.getCell(hero.getCoord().x, hero.getCoord().y - 1)) {
                    case " ":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "E":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                        hero.grabSword();
                        sword.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "e":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                        hero.grabShield();
                        shield.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        }
                        break;
                    case "S":
                        if (!dragonsAlive()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                            hero.setVictory();
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        } else {
                            System.out.println("You need to kill the dragon(s)!");
                        }
                        break;
                    default:
                        return;
                }
                break;
            case "d":
                switch (maze.getCell(hero.getCoord().x, hero.getCoord().y + 1)) {
                    case " ":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "E":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                        hero.grabSword();
                        sword.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "e":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                        hero.grabShield();
                        shield.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        }
                        break;
                    case "S":
                        if (!dragonsAlive()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            hero.setVictory();
                        } else {
                            System.out.println("You need to kill the dragon(s)!");
                        }
                        break;
                    default:
                        return;
                }
                break;
            case "w":
                switch (maze.getCell(hero.getCoord().x - 1, hero.getCoord().y)) {
                    case " ":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "E":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                        hero.grabSword();
                        sword.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "e":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                        hero.grabShield();
                        shield.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        }
                        break;
                    case "S":
                        if (!dragonsAlive()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            hero.setVictory();
                        } else {
                            System.out.println("You need to kill the dragon(s)!");
                        }
                        break;
                    default:
                        return;
                }
                break;
            case "s":
                switch (maze.getCell(hero.getCoord().x + 1, hero.getCoord().y)) {
                    case " ":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "E":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                        hero.grabSword();
                        sword.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "e":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                        hero.grabShield();
                        shield.pickUp();
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                        break;
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            for (Dragon dragon : dragons) {
                                if (dragon.getCoord().equals(hero.getCoord())) {
                                    dragon.kill();
                                }
                            }
                        }
                        break;
                    case "S":
                        if (!dragonsAlive()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            hero.setVictory();
                        } else {
                            System.out.println("You need to kill the dragon(s)!");
                        }
                        break;
                    default:
                        return;
                }
                break;

            default:
                System.out.print("Invalid input! Try again!\n");
        }
        System.out.print("\n");
    }

    public static void moveDragon() {
        Random random = new Random();
        int d;

        for (Dragon dragon : dragons) {
            if (dragon.isAlive() && dragon.isAwake() && (dragon.getType() == 2 || dragon.getType() == 3)) {
                d = random.nextInt(5);
                switch (d) {
                    case 0://stay
                        break;
                    case 1://left
                        if (maze.getCell(dragon.getCoord().x - 1, dragon.getCoord().y).equals(" ")) {
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, " ");
                            dragon.setCoord(dragon.getCoord().x - 1, dragon.getCoord().y);
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, dragon.getId());
                        }
                        break;
                    case 2://right
                        if (maze.getCell(dragon.getCoord().x + 1, dragon.getCoord().y).equals(" ")) {
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, " ");
                            dragon.setCoord(dragon.getCoord().x + 1, dragon.getCoord().y);
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, dragon.getId());
                        }
                        break;
                    case 3://up
                        if (maze.getCell(dragon.getCoord().x, dragon.getCoord().y - 1).equals(" ")) {
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, " ");
                            dragon.setCoord(dragon.getCoord().x, dragon.getCoord().y - 1);
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, dragon.getId());
                        }
                        break;
                    case 4://down
                        if (maze.getCell(dragon.getCoord().x, dragon.getCoord().y + 1).equals(" ")) {
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, " ");
                            dragon.setCoord(dragon.getCoord().x, dragon.getCoord().y + 1);
                            maze.setCell(dragon.getCoord().x, dragon.getCoord().y, dragon.getId());
                        }
                        break;
                    default:
                        return;
                }
            }

            if (dragon.isAlive() && dragon.getType() == 3) {
                d = random.nextInt(100);
                if (d % 2 == 0) {
                    if (dragon.isAwake()) {
                        dragon.setAsleep();
                        maze.setCell(dragon.getCoord().x, dragon.getCoord().y, dragon.getId());
                    } else {
                        dragon.setAwake();
                        maze.setCell(dragon.getCoord().x, dragon.getCoord().y, dragon.getId());
                    }
                }
            }
        }
    }

    public static void testEncounter() {
        for (Dragon dragon : dragons) {
            if (dragon.isAwake()) {
                if (dragon.isNextTo(hero)) {
                    if (!hero.hasSword()) {
                        hero.kill();
                    }
                }/* else if (dragon.isInRange(hero, 3)) {
                    if (!hero.hasShield()) {
                        hero.kill();
                    }
                }*/
            }
        }
    }

    public static void updateGameState() {
        if (!hero.isAlive())
            gameState = 1;
        else if (hero.getVictory())
            gameState = 2;
    }

    public static void init() {
        maze = new Maze();
        if (cli.getChooseMaze() == 2) {
            MazeGenerator generator = new MazeGenerator(cli.getHeight(), cli.getWidth());
            generator.createMaze();
            generator.populate(cli.getNDragons(), cli.getTDragons());
            maze.setMaze(generator.getMaze());
            hero = generator.getHero();
            sword = generator.getSword();
            shield = generator.getShield();
            dragons = generator.getDragons();
        } else {
            hero = new Hero(1, 1);
            sword = new Sword(8, 1);
            shield = new Shield(5, 5);
            dragons = new ArrayList<>(1);
            dragons.add(new Dragon(3, 1, true, 0));
        }
        gameState = 0;
    }


    static void run() {
        do {
            cli.drawMaze(maze.getMaze());
            System.out.print("\n");
            moveHero(cli.getDirection());
            testEncounter();
            moveDragon();
            testEncounter();
            updateGameState();
        } while (gameState == 0);

        cli.drawMaze(maze.getMaze());
        switch (gameState) {
            case 1:
                cli.victory();
                break;
            case 2:
                cli.defeat();
                break;
        }
    }

    public static void main(String args[]) {
        init();
        run();
    }

}

