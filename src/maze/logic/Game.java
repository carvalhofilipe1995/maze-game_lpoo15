package maze.logic;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
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

    /**
     * Default Constructor Game
     */
    public Game() {

        maze = new MazeGenerator(17, 17);
        hero = new Hero(0, 0);
        sword = new Sword(0, 0);
        shield = new Shield(0, 0);

        GameState = false;

        exitOpen = false;

        emptyCells = new Vector<>(0, 1);


        dragons = new ArrayList<>();
        dragons.add(new Dragon(0, 0, true));
        dragons.add(new Dragon(0, 0, true));
    }

    /**
     * @param height         height maze
     * @param width          width maze
     * @param number_dragons number dragons maze
     * @param type_dragons   type dragons maze
     */
    public Game(int height, int width, int number_dragons, int type_dragons) {

        maze = new MazeGenerator(height, width);
        hero = new Hero(0, 0);
        sword = new Sword(0, 0);
        shield = new Shield(0, 0);

        GameState = false;

        this.type_dragons = type_dragons;

        exitOpen = false;

        emptyCells = new Vector<>(0, 1);

        dragons = new ArrayList<>();

        for (int i = 0; i < number_dragons; i++) {
            dragons.add(new Dragon(0, 0, true));
        }

    }

    /**
     * @return dragons in the game
     */
    public ArrayList<Dragon> getDragons() {
        return dragons;
    }

    /**
     * @return game state
     */
    public boolean getGameState() {
        return GameState;
    }

    /**
     * @return hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * @return maze
     */
    public MazeGenerator getMaze() {
        return maze;
    }

    /**
     * @return sword
     */
    public Sword getSword() {
        return sword;
    }


    /**
     * @return shield
     */
    public Shield getShield() {
        return shield;
    }

    /**
     * @return true if exit is open
     */
    public boolean isExitOpen() {
        return exitOpen;
    }

    // Update Game State

    /**
     * Update the game state
     * @param direction hero direction
     */
    public void updateGameState(String direction) {

        boolean swordVisible = true;

        if (dragons.size() == 0)
            exitOpen = true;

        heroMove(direction);

        if (maze.getCell(hero.getCoord().x, hero.getCoord().y).equals("S")
                && exitOpen) {
            GameState = true;
            return;
        }

        if (type_dragons == 1) {
            deleteDragonFire();
            int j = 3;
            for (Dragon i : dragons) {
                if (j < random.nextInt(10))
                    dragonsFire(i);
            }
        } else if (type_dragons == 2) {

            for (Dragon i : dragons) {
                deleteDragonFire();
                moveDragons(i, random.nextInt(4));
                int j = 3;
                if (j < random.nextInt(10))
                    dragonsFire(i);

            }

        } else if (type_dragons == 3) {
            for (Dragon i : dragons) {
                deleteDragonFire();
                if (i.isAwake()) {
                    moveDragons(i, random.nextInt(4));
                    int j = 3;
                    if (j < random.nextInt(10))
                        dragonsFire(i);
                }
                sleepDragons();
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

    /**
     * @return true if a dragon is near to hero else false
     */

    public boolean checkIfDragonIsNear() {
        for (Dragon i : dragons) {
            if (i.isNextTo(hero) && !hero.hasSword() && Objects.equals(i.getId(), "D")) {
                hero.kill();
                return true;
            }
        }
        return false;
    }

    // Hero Movements

    /**
     * Check if hero can move
     * @param direction hero direction
     */

    public void heroMove(String direction) {
        switch (direction) {
            case "a":
                if (!Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                        .getCoord().getY()), "S")) {
                    checkMoveLeft();
                } else if (exitOpen) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "d":
                if (!Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                        .getCoord().getY()), "S")) {
                    checkMoveRight();
                } else if (exitOpen) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "w":
                if (!Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                        .getCoord().getY() - 1), "S")) {
                    checkMoveUp();
                } else if (exitOpen) {
                    GameState = true;
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            " ");
                    hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                    maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                            hero.getId());
                }
                break;
            case "s":
                if (!Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                        .getCoord().getY() + 1), "S")) {
                    checkMoveDown();
                } else if (exitOpen) {
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

    /**
     * Check if hero can move to left
     */

    public void checkMoveLeft() {
        if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), " ")) {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), "/")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), "e")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), "D")) {

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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), "d")
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
        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), "*")) {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
                .getCoord().getY()), "^")) {
            if (hero.hasShield()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
                hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
            } else
                hero.kill();

        }

    }

    /**
     * check if hero can move to right
     */

    public void checkMoveRight() {
        if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), " ")) {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), "/")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), "e")) {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), "D")) {
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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), "d")
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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), "*")) {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
                .getCoord().getY()), "^")) {
            if (hero.hasShield()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
                hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
            } else
                hero.kill();

        }

    }

    /**
     * check if hero can move up
     */

    public void checkMoveUp() {
        if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero.getCoord()
                .getY() - 1), " ")) {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1), "/")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1), "e")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1), "D")) {

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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1), "d")
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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1), "*")) {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() - 1), "^")) {
            if (hero.hasShield()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
                hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
            } else
                hero.kill();

        }
    }

    /**
     * check if hero can move down
     */

    public void checkMoveDown() {
        if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero.getCoord()
                .getY() + 1), " ")) {
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1), "/")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            hero.grabSword();
            sword.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1), "e")) {

            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            hero.grabShield();
            shield.pickUp();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1), "D")) {

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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1), "d")
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

        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1), "*")) {
            hero.grabDarts();
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
            hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
            maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                    hero.getId());
        } else if (Objects.equals(maze.getCell((int) hero.getCoord().getX(), (int) hero
                .getCoord().getY() + 1), "^")) {
            if (hero.hasShield()) {
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
                hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
                maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
                        hero.getId());
            } else
                hero.kill();

        }
    }

    // Dragons Movements

    /**
     * Put cells on fire
     * @param Dragon dragon
     */
    public void dragonsFire(Dragon d) {

        // put fire on the right

        for (int i = d.getCoord().x, j = 3; i < maze.getHeight() && j > 0; i++, j--) {
            if (Objects.equals(maze.getCell(i, d.getCoord().y), "X")
                    || Objects.equals(maze.getCell(i, d.getCoord().y), "*")
                    || Objects.equals(maze.getCell(i, d.getCoord().y), "/")
                    || Objects.equals(maze.getCell(i, d.getCoord().y), "^")) {
                break;
            } else if (Objects.equals(maze.getCell(i, d.getCoord().y), " ")) {
                // set cell as fire
                maze.setCellAsFire(new Point(i, d.getCoord().y));
                d.addCellFire(new Point(i, d.getCoord().y));
            }
        }

        // put fire on the left

        for (int i = d.getCoord().x, j = 3; i >= 0 && j > 0; i--, j--) {
            if (Objects.equals(maze.getCell(i, d.getCoord().y), "X")
                    || Objects.equals(maze.getCell(i, d.getCoord().y), "*")
                    || Objects.equals(maze.getCell(i, d.getCoord().y), "/")
                    || Objects.equals(maze.getCell(i, d.getCoord().y), "^")) {
                break;
            } else if (Objects.equals(maze.getCell(i, d.getCoord().y), " ")) {
                // set cell as fire
                maze.setCellAsFire(new Point(i, d.getCoord().y));
                d.addCellFire(new Point(i, d.getCoord().y));
            }
        }

        // put fire over

        for (int i = d.getCoord().y, j = 3; i >= 0 && j > 0; i--, j--) {
            if (Objects.equals(maze.getCell(d.getCoord().x, i), "X")
                    || Objects.equals(maze.getCell(d.getCoord().x, i), "*")
                    || Objects.equals(maze.getCell(d.getCoord().x, i), "/")
                    || Objects.equals(maze.getCell(d.getCoord().x, i), "^")) {
                break;
            } else if (Objects.equals(maze.getCell(d.getCoord().x, i), " ")) {
                // set cell as fire
                maze.setCellAsFire(new Point(d.getCoord().x, i));
                d.addCellFire(new Point(d.getCoord().x, i));
            }
        }

        // put fire under

        for (int i = d.getCoord().y, j = 3; i < maze.getWidth() && j > 0; i++, j--) {
            if (Objects.equals(maze.getCell(d.getCoord().x, i), "X")) {
                break;
            } else if (Objects.equals(maze.getCell(d.getCoord().x, i), " ")) {
                // set cell as fire
                maze.setCellAsFire(new Point(d.getCoord().x, i));
                d.addCellFire(new Point(d.getCoord().x, i));
            }
        }

    }

    /**
     * delete dragons fire
     */

    public void deleteDragonFire() {

        for (Dragon i : dragons) {
            Vector<Point> fire = i.getCellsOnFire();

            for (Point p : fire) {
                maze.setMaze(p, " ");
            }

            i.removeCellsOnFire();
        }

    }

    /**
     * Move dragons
     * @param i  dragon
     * @param direction dragon direction
     */
    public void moveDragons(Dragon i, int direction) {

        switch (direction) {
            case 0: // up
                if (Objects.equals(maze.getCell(i.getCoord().x, i.getCoord().y - 1), " ")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y - 1);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (Objects.equals(maze.getCell(i.getCoord().x, i.getCoord().y - 1), "/")) {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y - 1);
                    maze.setMaze(i.getCoord(), "F");
                } else if (Objects.equals(maze.getCell(i.getCoord().x, i.getCoord().y - 1), "^")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y - 1);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            case 1: // down
                if (Objects.equals(maze.getCell(i.getCoord().x, i.getCoord().y + 1), " ")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y + 1);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (Objects.equals(maze.getCell(i.getCoord().x, i.getCoord().y + 1), "/")) {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y + 1);
                    maze.setMaze(i.getCoord(), "F");
                } else if (Objects.equals(maze.getCell(i.getCoord().x, i.getCoord().y + 1), "^")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x, i.getCoord().y + 1);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            case 2: // right
                if (Objects.equals(maze.getCell(i.getCoord().x + 1, i.getCoord().y), " ")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x + 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (Objects.equals(maze.getCell(i.getCoord().x + 1, i.getCoord().y), "/")) {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x + 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), "F");
                } else if (Objects.equals(maze.getCell(i.getCoord().x + 1, i.getCoord().y), "^")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x + 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            case 3: // left
                if (Objects.equals(maze.getCell(i.getCoord().x - 1, i.getCoord().y), " ")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x - 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                } else if (Objects.equals(maze.getCell(i.getCoord().x - 1, i.getCoord().y), "/")) {
                    sword.setVisible();
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x - 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), "F");
                } else if (Objects.equals(maze.getCell(i.getCoord().x - 1, i.getCoord().y), "^")) {
                    maze.setMaze(i.getCoord(), " ");
                    i.setCoord(i.getCoord().x - 1, i.getCoord().y);
                    maze.setMaze(i.getCoord(), i.getType());
                }
                break;
            default:
                break;

        }

    }
    
    /**
     *  Make dragons sleep
     */

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
    
    /**
     * Check darts direction
     * @param direction darts direction
     */

    public void checkDartsDirection(String direction) {

        switch (direction) {
            case "d":
                for (int i = hero.getCoord().x; i >= 0; i--) {
                    if (Objects.equals(maze.getCell(i, hero.getCoord().y), "X")) {
                        break;
                    } else if (Objects.equals(maze.getCell(i, hero.getCoord().y), "D")
                            || Objects.equals(maze.getCell(i, hero.getCoord().y), "d")) {
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
            case "a":
                for (int i = hero.getCoord().x; i < maze.getHeight(); i++) {
                    if (Objects.equals(maze.getCell(i, hero.getCoord().y), "X")) {
                        break;
                    } else if (Objects.equals(maze.getCell(i, hero.getCoord().y), "D")
                            || Objects.equals(maze.getCell(i, hero.getCoord().y), "d")) {
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

                    if (Objects.equals(maze.getCell(hero.getCoord().x, i), "X")) {
                        break;
                    } else if (Objects.equals(maze.getCell(hero.getCoord().x, i), "D")
                            || Objects.equals(maze.getCell(hero.getCoord().x, i), "d")) {
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

                    if (Objects.equals(maze.getCell(hero.getCoord().x, i), "X")) {
                        break;
                    } else if (Objects.equals(maze.getCell(hero.getCoord().x, i), "D")
                            || Objects.equals(maze.getCell(hero.getCoord().x, i), "d")) {
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

   

    /**
     * Put the elements in the maze
     * @param number_dragons number dragons to populate
     */
    public void initializePositionsElements(int number_dragons) {

        maze.createMaze();
        checkEmptyCells();
        choosePositionHero();
        choosePositionSword();
        choosePositionShield();
        choosePositionDarts();
        choosePositionDragons(number_dragons);

    }

    /**
     * Put darts in the maze
     */
    public void choosePositionDarts() {
        int number_darts = random.nextInt(maze.getHeight() / 2);

        while (number_darts != 0) {

            int position = random.nextInt(emptyCells.size());
            maze.setMaze(emptyCells.elementAt(position), "*");
            emptyCells.remove(position);

            number_darts--;

        }

    }

    /**
     * Check which cells are empty
     */
    public void checkEmptyCells() {
        for (int i = 0; i < maze.height; i++)
            for (int j = 0; j < maze.width; j++)
                if (maze.getCell(i, j).equals(" "))
                    emptyCells.add(new Point(i, j));

    }

    /**
     * Put hero in the maze
     */
    public void choosePositionHero() {
        int position = random.nextInt(emptyCells.size());
        hero.setCoord(emptyCells.elementAt(position).x,
                emptyCells.elementAt(position).y);
        maze.setMaze(
                new Point(emptyCells.get(position).x,
                        emptyCells.get(position).y), "H");
        emptyCells.remove(position);
    }

    /**
     * Put sword in the maze
     */

    public void choosePositionSword() {
        int position = random.nextInt(emptyCells.size());
        sword.setCoord(emptyCells.get(position).x, emptyCells.get(position).y);
        maze.setMaze(
                new Point(emptyCells.get(position).x,
                        emptyCells.get(position).y), "/");
        emptyCells.remove(position);
    }

    /**
     * Put shield in the maze
     */
    public void choosePositionShield() {
        int position = random.nextInt(emptyCells.size());
        shield.setCoord(emptyCells.get(position).x, emptyCells.get(position).y);
        maze.setMaze(
                new Point(emptyCells.get(position).x,
                        emptyCells.get(position).y), "e");
        emptyCells.remove(position);
    }

    /**
     * Put dragons in the maze
     * @param number_dragons dragons to populate
     */

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

}
