package maze.test;

import maze.logic.Game;
import org.junit.Test;

import java.awt.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GameTest {
    Game game;

    public final <T> void testAlt(int minIter,
                                  Supplier<T> generator, Function<T, String> errorMessage, Predicate<T>... predicates) {
        boolean[] tested = new boolean[predicates.length];
        int checked = 0;
        for (int iter = 0; iter < minIter && checked < predicates.length; iter++) {
            T x = generator.get();
            boolean found = false;
            for (int i = 0; i < predicates.length; i++)
                if (predicates[i].test(x)) {
                    found = true;
                    if (!tested[i]) {
                        checked++;
                        tested[i] = true;
                    }
                }
            if (!found)
                fail(errorMessage.apply(x));
            iter++;
        }
    }

    @Test
    public void testCellMovement() {
        //test hero movement

        testAlt(1000,
                () -> {
                    game = new Game();
                    game.initializePositionsElements(0);
                    Point[] heroP = new Point[2];
                    heroP[0] = game.getHero().getCoord();
                    game.heroMove("w");
                    heroP[1] = game.getHero().getCoord();
                    return heroP;
                },
                (m) -> "Invalid movement: " + m,
                (m) -> m[1].equals(new Point(m[0].x, m[0].y - 1)),
                (m) -> m[1].equals(m[0]));

        testAlt(1000,
                () -> {
                    game = new Game();
                    game.initializePositionsElements(0);
                    Point[] heroP = new Point[2];
                    heroP[0] = game.getHero().getCoord();
                    game.heroMove("a");
                    heroP[1] = game.getHero().getCoord();
                    return heroP;
                },
                (m) -> "Invalid movement: " + m,
                (m) -> m[1].equals(new Point(m[0].x - 1, m[0].y)),
                (m) -> m[1].equals(m[0]));

        testAlt(1000,
                () -> {
                    game = new Game();
                    game.initializePositionsElements(0);
                    Point[] heroP = new Point[2];
                    heroP[0] = game.getHero().getCoord();
                    game.heroMove("s");
                    heroP[1] = game.getHero().getCoord();
                    return heroP;
                },
                (m) -> "Invalid movement: " + m,
                (m) -> m[1].equals(new Point(m[0].x, m[0].y + 1)),
                (m) -> m[1].equals(m[0]));

        testAlt(1000,
                () -> {
                    game = new Game();
                    game.initializePositionsElements(0);
                    Point[] heroP = new Point[2];
                    heroP[0] = game.getHero().getCoord();
                    game.heroMove("d");
                    heroP[1] = game.getHero().getCoord();
                    return heroP;
                },
                (m) -> "Invalid movement: " + m,
                (m) -> m[1].equals(new Point(m[0].x - 1, m[0].y)),
                (m) -> m[1].equals(m[0]));

        //test dragon movement

        testAlt(1000,
                () -> {
                    game = new Game();
                    game.initializePositionsElements(1);
                    Point[] dragonP = new Point[2];
                    dragonP[0] = game.getDragons().get(0).getCoord();
                    game.updateGameState("a");
                    dragonP[1] = game.getDragons().get(0).getCoord();
                    return dragonP;
                },
                (m) -> "Invalid movement: " + m,
                (m) -> m[1].equals(m[0]),
                (m) -> m[1].equals(new Point(m[0].x, m[0].y - 1)),
                (m) -> m[1].equals(new Point(m[0].x - 1, m[0].y)),
                (m) -> m[1].equals(new Point(m[0].x, m[0].y + 1)),
                (m) -> m[1].equals(new Point(m[0].x + 1, m[0].y)));
    }

    @Test
    public void testGrabItems() {
        Game game = new Game();
        game.initializePositionsElements(0);
        game.getHero().setCoord(1, 1);
        if (game.getMaze().getCell(2, 1).equals(" ")) {
            game.getMaze().setMaze(new Point(2, 1), "/");
            game.updateGameState("d");
        } else {
            game.getMaze().setMaze(new Point(1, 2), "/");
            game.updateGameState("s");
        }
        assertEquals("A", game.getHero().getId());
        assertEquals(true, game.getHero().hasSword());


        game = new Game();
        game.initializePositionsElements(0);
        game.getHero().setCoord(1, 1);
        if (game.getMaze().getCell(2, 1).equals(" ")) {
            game.getMaze().setMaze(new Point(2, 1), "e");
            game.updateGameState("d");
        } else {
            game.getMaze().setMaze(new Point(1, 2), "e");
            game.updateGameState("s");
        }
        assertEquals(true, game.getHero().hasShield());


        game = new Game();
        game.initializePositionsElements(0);
        game.getHero().setCoord(1, 1);
        if (game.getMaze().getCell(2, 1).equals(" ")) {
            game.getMaze().setMaze(new Point(2, 1), "*");
            game.updateGameState("d");
        } else {
            game.getMaze().setMaze(new Point(1, 2), "*");
            game.updateGameState("s");
        }
        assertEquals(true, game.getHero().hasDarts());
    }

    @Test
    public void testEncounter() {
        Game game = new Game(11, 11, 1, 2);
        game.initializePositionsElements(1);
        game.getHero().setCoord(1, 1);
        game.getHero().grabSword();
        if (game.getMaze().getCell(2, 1).equals(" ")) {
            game.getDragons().get(0).setCoord(2, 1);
            game.getMaze().setMaze(new Point(2, 1), "D");
            game.updateGameState("d");
        } else {
            game.getDragons().get(0).setCoord(1, 2);
            game.getMaze().setMaze(new Point(1, 2), "D");
            game.updateGameState("s");
        }
        assertEquals(true, game.getHero().isAlive());
        assertEquals(true, game.getDragons().isEmpty());


        game = new Game(11, 11, 1, 2);
        game.initializePositionsElements(1);
        game.getHero().setCoord(1, 1);
        if (game.getMaze().getCell(2, 1).equals(" ")) {
            game.getDragons().get(0).setCoord(2, 1);
            game.getMaze().setMaze(new Point(2, 1), "D");
            game.updateGameState("d");
        } else {
            game.getDragons().get(0).setCoord(1, 2);
            game.getMaze().setMaze(new Point(1, 2), "D");
            game.updateGameState("s");
        }
        assertEquals(false, game.getHero().isAlive());
        assertEquals(false, game.getDragons().isEmpty());
        assertEquals(true, game.getDragons().get(0).isAlive());


        game = new Game(11, 11, 1, 2);
        game.initializePositionsElements(1);
        game.getHero().setCoord(1, 1);
        if (game.getMaze().getCell(2, 1).equals(" ")) {
            game.getDragons().get(0).setCoord(2, 1);
            game.getDragons().get(0).setAsleep();
            game.getMaze().setMaze(new Point(2, 1), "d");
            game.updateGameState("d");
        } else {
            game.getDragons().get(0).setCoord(1, 2);
            game.getMaze().setMaze(new Point(1, 2), "d");
            game.getDragons().get(0).setAsleep();
            game.updateGameState("s");
        }
        assertEquals(true, game.getHero().isAlive());
        assertEquals(false, game.getDragons().isEmpty());
        assertEquals(true, game.getDragons().get(0).isAlive());
    }

    @Test
    public void testExit() {
        Game game = new Game(11, 11, 1, 2);
        game.initializePositionsElements(1);
        if (game.getMaze().getExit().x == 10) {
            game.getHero().setCoord(9, game.getMaze().getExit().y);
            game.updateGameState("d");
        } else if (game.getMaze().getExit().x == 0) {
            game.getHero().setCoord(1, game.getMaze().getExit().y);
            game.updateGameState("a");
        } else if (game.getMaze().getExit().y == 10) {
            game.getHero().setCoord(game.getMaze().getExit().x, 9);
            game.updateGameState("s");
        } else {
            game.getHero().setCoord(game.getMaze().getExit().x, 1);
            game.updateGameState("w");
        }
        assertEquals(false, game.isExitOpen());
        assertEquals(false, game.getGameState());


        game = new Game(11, 11, 0, 1);
        game.initializePositionsElements(0);
        if (game.getMaze().getExit().x == 10) {
            game.getHero().setCoord(9, game.getMaze().getExit().y);
            game.updateGameState("d");
            game.updateGameState("d");
        } else if (game.getMaze().getExit().x == 0) {
            game.getHero().setCoord(1, game.getMaze().getExit().y);
            game.updateGameState("a");
            game.updateGameState("a");
        } else if (game.getMaze().getExit().y == 10) {
            game.getHero().setCoord(game.getMaze().getExit().x, 9);
            game.updateGameState("s");
            game.updateGameState("s");
        } else {
            game.getHero().setCoord(game.getMaze().getExit().x, 1);
            game.updateGameState("w");
            game.updateGameState("w");
        }
        assertEquals(true, game.isExitOpen());
        assertEquals(true, game.getGameState());
    }
}