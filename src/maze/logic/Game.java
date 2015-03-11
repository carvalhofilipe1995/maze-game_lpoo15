package maze.logic;

public class Game {

    private static maze.cli.Cli cli = new maze.cli.Cli();

    private static Maze maze;
    private static Hero hero;
    private static Sword sword;
    private static Dragon dragon;
    private static int gameState;


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
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        }
                        break;
                    case "S":
                        if (!dragon.isAlive()) {
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
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        }
                        break;
                    case "S":
                        if (!dragon.isAlive()) {
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
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        }
                        break;
                    case "S":
                        if (!dragon.isAlive()) {
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
                    case "D":
                        maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                        if (hero.hasSword()) {
                            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        } else {
                            hero.kill();
                        }
                        break;
                    case "d":
                        if (hero.hasSword()) {
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, " ");
                            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                            maze.setCell(hero.getCoord().x, hero.getCoord().y, hero.getId());
                            dragon.kill();
                        }
                        break;
                    case "S":
                        if (!dragon.isAlive()) {
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

    public static void init() {
        maze = new Maze();
        if (cli.getChooseMaze() == 2) {
            MazeGenerator generator = new MazeGenerator(cli.getHeight(), cli.getWidth());
            generator.createMaze();
            maze.setMaze(generator.getMaze());
        }
        hero = new Hero(1, 1);
        sword = new Sword(8, 1);
        dragon = new Dragon(5, 4, true, "test");
        gameState = 0;
    }


    static void run() {
        do {
            cli.drawMaze(maze.getMaze());
            System.out.print("\n");
            moveHero(cli.getDirection());
        } while (gameState == 0);
    }

    public static void main(String args[]) {
        init();
        run();
    }

}

