package maze.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class Game {

    Random random = new Random();
    private ArrayList<Dragon> dragons;
    private MazeGenerator maze;
    private Hero hero;
    private Shield shield;
    private Sword sword;
    private int type_dragons;

    private boolean GameState;
    private boolean exitOpen;
    private Vector<Point> emptyCells;
    //private Vector<Point> doubleCells;

    public Game() {

        maze = new MazeGenerator(17, 17);
        hero = new Hero(0, 0);
        sword = new Sword(0, 0);
        shield = new Shield(0, 0);

        GameState = false;

        exitOpen = false;

        emptyCells = new Vector<>(0, 1);
        //doubleCells = new Vector<>(0, 1);

        dragons = new ArrayList<>();
        dragons.add(new Dragon(0, 0, true));
        dragons.add(new Dragon(0, 0, true));
    }

    public Game(int height, int width, int number_dragons, int type_dragons) {

        maze = new MazeGenerator(height, width);
        hero = new Hero(0, 0);
        sword = new Sword(0, 0);
        shield = new Shield(0, 0);

        GameState = false;

        this.type_dragons = type_dragons;

        exitOpen = false;

        emptyCells = new Vector<Point>(0, 1);
        // doubleCells = new Vector<Point>(0, 1);

        dragons = new ArrayList<Dragon>();

        for (int i = 0; i < number_dragons; i++) {
            dragons.add(new Dragon(0, 0, true));
        }

    }

    public ArrayList<Dragon> getDragons() {
        return dragons;
    }

    public boolean getGameState() {
        return GameState;
    }

    public Hero getHero() {
        return hero;
    }

    public MazeGenerator getMaze() {
        return maze;
    }

    public Sword getSword() {
        return sword;
    }

    public Shield getShield() {
        return shield;
    }

    public boolean isExitOpen() {
        return exitOpen;
    }

    // Update Game State

    public void updateGameState(String direction) {

        boolean swordVisible = true;

        if (dragons.size() == 0)
            exitOpen = true;

        heroMove(direction);

        if (maze.getCell(hero.getCoord().x, hero.getCoord().y).equals("S")
                && exitOpen == true) {
            GameState = true;
            return;
        }

        if (type_dragons == 2) {
  
            for (Dragon i : dragons) {
                deleteDragonFire();
                moveDragons(i, random.nextInt(4));
                dragonsFire(i);
            }

        } else if (type_dragons == 3) {
            for (Dragon i : dragons) {
                deleteDragonFire();
                moveDragons(i, random.nextInt(4));
                sleepDragons();
                if (i.isAwake())
                    dragonsFire(i);
            }
        }

        for (Dragon i : dragons) {
            if (i.getCoord().x == sword.getCoord().x
                    && i.getCoord().y == sword.getCoord().y) {
                swordVisible = false;
            }
        }

        if (swordVisible && !sword.isPicked()) {
            maze.setMaze(sword.getCoord(), sword.getId());
        }

    }

    public boolean checkIfDragonIsNear() {
        for (Dragon i : dragons) {
            if (i.isNextTo(hero) && !hero.hasSword() && i.getId() == "D") {
                hero.kill();
                return true;
            }
        }
        return false;
    }

    // Hero Movements

    public void heroMove(String direction) {
        switch (direction) {
            case "a":
                if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                        .getCoord().getY()) != "S") {
                    checkMoveLeft();
                } else if (exitOpen == true) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "d":
                if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                        .getCoord().getY()) != "S") {
                    checkMoveRight();
                } else if (exitOpen == true) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "w":
                if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                        .getCoord().getY() - 1) != "S") {
                    checkMoveUp();
                } else if (exitOpen == true) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "s":
                if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                        .getCoord().getY() + 1) != "S") {
                    checkMoveDown();
                } else if (exitOpen == true) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "e":
                break;
            default:
                break;
        }

    }

    public void checkMoveLeft() {
        if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()) == " ") {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()) == "/") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()) == "e") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()) == "D") {

            if (hero.hasSword()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        " ");
                hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
                for (Dragon dragon : dragons) {
                    if (dragon.getCoord().equals(hero.getCoord())) {
                        deleteDragonFire();
                        dragon.kill();
                        dragons.remove(dragon);
                        break;
                    }
                }
            } else {
                hero.kill();
            }

        } else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()) == "d"
                && hero.hasSword()) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
            for (Dragon dragon : dragons) {
                if (dragon.getCoord().equals(hero.getCoord())) {
                    deleteDragonFire();
                    dragon.kill();
                    dragons.remove(dragon);
                    break;
                }

            }
        } else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()) == "*") {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        }

    }

    public void checkMoveRight() {
        if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), " ")) {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        } else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()) == "/") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()) == "e") {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()) == "D") {
            if (hero.hasSword()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        " ");
                hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
                for (Dragon dragon : dragons) {
                    if (dragon.getCoord().equals(hero.getCoord())) {
                        deleteDragonFire();
                        dragon.kill();
                        dragons.remove(dragon);
                        break;
                    }
                }
            } else {
                hero.kill();
            }

        } else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()) == "d"
                && hero.hasSword()) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
            for (Dragon dragon : dragons)
                if (dragon.getCoord().equals(hero.getCoord())) {
                    deleteDragonFire();
                    dragon.kill();
                    dragons.remove(dragon);
                    break;
                }

        } else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()) == "*") {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        }

    }

    public void checkMoveUp() {
        if (maze.getCell((int) hero.getCoord().getX(), (int) hero.getCoord()
                .getY() - 1) == " ") {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1) == "/") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1) == "e") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1) == "D") {

            if (hero.hasSword()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        " ");
                hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
                for (Dragon dragon : dragons) {
                    if (dragon.getCoord().equals(hero.getCoord())) {
                        deleteDragonFire();
                        dragon.kill();
                        dragons.remove(dragon);
                        break;
                    }
                }
            } else {
                hero.kill();
            }

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1) == "d"
                && hero.hasSword()) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
            for (Dragon dragon : dragons) {
                if (dragon.getCoord().equals(hero.getCoord())) {
                    deleteDragonFire();
                    dragon.kill();
                    dragons.remove(dragon);
                    break;
                }
            }

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1) == "*") {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        }
    }

    public void checkMoveDown() {
        if (maze.getCell((int) hero.getCoord().getX(), (int) hero.getCoord()
                .getY() + 1) == " ") {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1) == "/") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1) == "e") {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1) == "D") {

            if (hero.hasSword()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        " ");
                hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());

                for (Dragon dragon : dragons) {
                    if (dragon.getCoord().equals(hero.getCoord())) {
                        deleteDragonFire();
                        dragon.kill();
                        dragons.remove(dragon);
                        break;
                    }
                }

            } else {
                hero.kill();
            }

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1) == "d"
                && hero.hasSword()) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
            for (Dragon dragon : dragons)
                if (dragon.getCoord().equals(hero.getCoord())) {
                    deleteDragonFire();
                    dragon.kill();
                    dragons.remove(dragon);
                    break;
                }

        } else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1) == "*") {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        }
    }

    // Dragons Movements

    public void dragonsFire(Dragon d) {

        // put fire on the right

        for (int i = d.getCoord().x, j = 3; i < maze.getHeight() && j > 0; i++, j--) {
            if (maze.getCell(i, d.getCoord().y) == "X"
                    || maze.getCell(i, d.getCoord().y) == "*"
                    || maze.getCell(i, d.getCoord().y) == "/"
                    || maze.getCell(i, d.getCoord().y) == "^") {
                break;
            } else if (maze.getCell(i, d.getCoord().y) == " ") {
                // set cell as fire
                maze.setCellAsFire(new Point(i, d.getCoord().y));
                d.addCellFire(new Point(i, d.getCoord().y));
            }
        }

        // put fire on the left

        for (int i = d.getCoord().x, j = 3; i >= 0 && j > 0; i--, j--) {
            if (maze.getCell(i, d.getCoord().y) == "X"
                    || maze.getCell(i, d.getCoord().y) == "*"
                    || maze.getCell(i, d.getCoord().y) == "/"
                    || maze.getCell(i, d.getCoord().y) == "^") {
                break;
            } else if (maze.getCell(i, d.getCoord().y) == " ") {
                // set cell as fire
                maze.setCellAsFire(new Point(i, d.getCoord().y));
                d.addCellFire(new Point(i, d.getCoord().y));
            }
        }

        // put fire over

        for (int i = d.getCoord().y, j = 3; i >= 0 && j > 0; i--, j--) {
            if (maze.getCell(d.getCoord().x, i) == "X"
                    || maze.getCell(d.getCoord().x, i) == "*"
                    || maze.getCell(d.getCoord().x, i) == "/"
                    || maze.getCell(d.getCoord().x, i) == "^") {
                break;
            } else if (maze.getCell(d.getCoord().x, i) == " ") {
                // set cell as fire
                maze.setCellAsFire(new Point(d.getCoord().x, i));
                d.addCellFire(new Point(d.getCoord().x, i));
            }
        }

        // put fire under

        for (int i = d.getCoord().y, j = 3; i < maze.getWidth() && j > 0; i++, j--) {
            if (maze.getCell(d.getCoord().x, i) == "X") {
                break;
            } else if (maze.getCell(d.getCoord().x, i) == " ") {
                // set cell as fire
                maze.setCellAsFire(new Point(d.getCoord().x, i));
                d.addCellFire(new Point(d.getCoord().x, i));
            }
        }

    }

    public void deleteDragonFire() {

        for (Dragon i : dragons) {
            Vector<Point> fire = i.getCellsOnFire();

            for (Point p : fire) {
                maze.setMaze(p, " ");
            }

            i.removeCellsOnFire();
        }

    }

    public void moveDragons(Dragon i, int direction) {

        switch (direction) {
            case 0: // up
                if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == " ") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y - 1);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == "/") {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y - 1);
                    maze.setMaze(i.getCoord(), "F");
                } else if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == "^") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y - 1);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            case 1: // down
                if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == " ") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y + 1);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == "/") {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y + 1);
                    maze.setMaze(i.getCoord(), "F");
                } else if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == "^") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y + 1);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            case 2: // right
                if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == " ") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x + 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == "/") {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x + 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), "F");
                } else if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == "^") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x + 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            case 3: // left
                if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == " ") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x - 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == "/") {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x - 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), "F");
                } else if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == "^") {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x - 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            default:
                break;

        }

    }

    public void sleepDragons() {

        for (Dragon i : dragons) {
            int sleep = random.nextInt(10);
            if (sleep < 5) {
                i.setAsleep();
            } else {
                i.setAwake();
            }
        }

    }

    public void checkDartsDirection(String direction) {

        switch (direction) {
            case "a":
                for (int i = hero.getCoord().x; i >= 0; i--) {
                    if (maze.getCell(i, hero.getCoord().y) == "X") {
                        break;
                    } else if (maze.getCell(i, hero.getCoord().y) == "D"
                            || maze.getCell(i, hero.getCoord().y) == "d") {
                        for (Dragon j : dragons) {
                            if (j.getCoord().x == i
                                    && j.getCoord().y == hero.getCoord().y) {
                                j.kill();
                                maze.setMaze(j.getCoord(), " ");
                                dragons.remove(j);
                                hero.setDarts();
                                break;
                            }
                        }
                    }
                }
                break;
            case "d":
                for (int i = hero.getCoord().x; i < maze.getHeight(); i++) {
                    if (maze.getCell(i, hero.getCoord().y) == "X") {
                        break;
                    } else if (maze.getCell(i, hero.getCoord().y) == "D"
                            || maze.getCell(i, hero.getCoord().y) == "d") {
                        for (Dragon j : dragons) {
                            if (j.getCoord().x == i
                                    && j.getCoord().y == hero.getCoord().y) {
                                j.kill();
                                maze.setMaze(j.getCoord(), " ");
                                dragons.remove(j);
                                hero.setDarts();
                                break;
                            }
                        }
                    }

                }
                break;
            case "w":
                for (int i = hero.getCoord().y; i >= 0; i--) {

                    if (maze.getCell(hero.getCoord().x, i) == "X") {
                        break;
                    } else if (maze.getCell(hero.getCoord().x, i) == "D"
                            || maze.getCell(hero.getCoord().x, i) == "d") {
                        for (Dragon j : dragons) {
                            if (j.getCoord().y == i
                                    && j.getCoord().x == hero.getCoord().x) {
                                j.kill();
                                maze.setMaze(j.getCoord(), " ");
                                dragons.remove(j);
                                hero.setDarts();
                                break;
                            }
                        }
                    }

                }
                break;
            case "s":
                for (int i = hero.getCoord().y; i < maze.getWidth(); i++) {

                    if (maze.getCell(hero.getCoord().x, i) == "X") {
                        break;
                    } else if (maze.getCell(hero.getCoord().x, i) == "D"
                            || maze.getCell(hero.getCoord().x, i) == "d") {
                        for (Dragon j : dragons) {
                            if (j.getCoord().y == i
                                    && j.getCoord().x == hero.getCoord().x) {
                                j.kill();
                                maze.setMaze(j.getCoord(), " ");
                                dragons.remove(j);
                                hero.setDarts();
                                break;
                            }
                        }
                    }

                }
                break;
            default:
                break;

        }

    }

    // Populate lab with the elements

    public void initializePositionsElements(int number_dragons) {

        maze.createMaze();
        checkEmptyCells();
        choosePositionHero();
        choosePositionSword();
        choosePositionShield();
        choosePositionDarts();
        choosePositionDragons(number_dragons);

    }

    public void choosePositionDarts() {
        int number_darts = random.nextInt(maze.getHeight() / 2);

        while (number_darts != 0) {

            int position = random.nextInt(emptyCells.size());
            maze.setMaze(emptyCells.elementAt(position), "*");
            emptyCells.remove(position);

            number_darts--;

        }

    }

    public void checkEmptyCells() {
        for (int i = 0; i < maze.height; i++)
            for (int j = 0; j < maze.width; j++)
                if (maze.getCell(i, j).equals(" "))
                    emptyCells.add(new Point(i, j));

    }

    public void choosePositionHero() {
        int position = random.nextInt(emptyCells.size());
        hero.setCoord(emptyCells.elementAt(position).x,
                emptyCells.elementAt(position).y);
        maze.setMaze(
                new Point(emptyCells.get(position).x,
                        emptyCells.get(position).y), "H");
        emptyCells.remove(position);
    }

    public void choosePositionSword() {
        int position = random.nextInt(emptyCells.size());
        sword.setCoord(emptyCells.get(position).x, emptyCells.get(position).y);
        maze.setMaze(
                new Point(emptyCells.get(position).x,
                        emptyCells.get(position).y), "/");
        emptyCells.remove(position);
    }

    public void choosePositionShield() {
        int position = random.nextInt(emptyCells.size());
        shield.setCoord(emptyCells.get(position).x, emptyCells.get(position).y);
        maze.setMaze(
                new Point(emptyCells.get(position).x,
                        emptyCells.get(position).y), "e");
        emptyCells.remove(position);
    }

    public void choosePositionDragons(int number_dragons) {
        for (int i = 0; i < number_dragons; i++) {
            int position = random.nextInt(emptyCells.size());
            dragons.get(i).setCoord(emptyCells.get(position).x,
                    emptyCells.get(position).y);
            maze.setMaze(
                    new Point(emptyCells.get(position).x, emptyCells
                            .get(position).y), dragons.get(i).getType());
            emptyCells.remove(position);
        }
    }

    public void printGame() {
        for (int i = 0; i < maze.height; i++) {
            System.out.print("  ");
            for (int j = 0; j < maze.width; j++) {
                System.out.print(maze.getCell(j, i) + ' ');
            }
            System.out.print("\n");
        }
    }

}
