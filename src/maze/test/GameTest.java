package maze.test;

import maze.logic.Game;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testPopulateMaze() {
        Game game = new Game();
        game.initializePositionsElements(2);
        assertEquals("H", game.getHero().getId());
        assertEquals("H", game.getMaze().getCell(game.getHero().getCoord().x, game.getHero().getCoord().y));

        assertEquals("/", game.getSword().getId());
        assertEquals("/", game.getMaze().getCell(game.getSword().getCoord().x, game.getSword().getCoord().y));

        assertEquals("e", game.getShield().getId());
        assertEquals("e", game.getMaze().getCell(game.getShield().getCoord().x, game.getShield().getCoord().y));

        assertEquals("D", game.getDragons().get(0).getId());
        assertEquals("D", game.getDragons().get(1).getId());
        assertEquals("D", game.getMaze().getCell(game.getDragons().get(0).getCoord().x, game.getDragons().get(0)
                .getCoord().y));
        assertEquals("D", game.getMaze().getCell(game.getDragons().get(1).getCoord().x, game.getDragons().get(1)
                .getCoord().y));
        assertNotEquals(game.getDragons().get(0).getCoord(), game.getDragons().get(1).getCoord());
    }

    @Test
    public void testHeroMovement() {
        Game game;

        //up
        game = new Game();
        game.getHero().setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "H");
        game.getMaze().setMaze(new Point(1, 1), " ");
        game.heroMove("w");
        assertEquals("H", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));

        game = new Game();
        game.getHero().setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "H");
        game.getMaze().setMaze(new Point(1, 1), "/");
        game.heroMove("w");
        assertEquals("A", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));
        assertTrue(game.getHero().hasSword());
        assertTrue(game.getSword().isPicked());

        game = new Game();
        game.getHero().setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "H");
        game.getMaze().setMaze(new Point(1, 1), "e");
        game.heroMove("w");
        assertEquals("H", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));
        assertTrue(game.getHero().hasShield());
        assertTrue(game.getShield().isPicked());

        game = new Game();
        game.getHero().setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "H");
        game.getMaze().setMaze(new Point(1, 1), "*");
        game.heroMove("w");
        assertEquals("A", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));
        assertTrue(game.getHero().hasDarts());

        //down
        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(1, 2), " ");
        game.heroMove("s");
        assertEquals("H", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(1, 2), "/");
        game.heroMove("s");
        assertEquals("A", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));
        assertTrue(game.getHero().hasSword());
        assertTrue(game.getSword().isPicked());

        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(1, 2), "e");
        game.heroMove("s");
        assertEquals("H", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));
        assertTrue(game.getHero().hasShield());
        assertTrue(game.getShield().isPicked());

        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(1, 2), "*");
        game.heroMove("s");
        assertEquals("A", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));
        assertTrue(game.getHero().hasDarts());

        //left
        game = new Game();
        game.getHero().setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "H");
        game.getMaze().setMaze(new Point(1, 1), " ");
        game.heroMove("a");
        assertEquals("H", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));

        game = new Game();
        game.getHero().setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "H");
        game.getMaze().setMaze(new Point(1, 1), "/");
        game.heroMove("a");
        assertEquals("A", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));
        assertTrue(game.getHero().hasSword());
        assertTrue(game.getSword().isPicked());

        game = new Game();
        game.getHero().setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "H");
        game.getMaze().setMaze(new Point(1, 1), "e");
        game.heroMove("a");
        assertEquals("H", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));
        assertTrue(game.getHero().hasShield());
        assertTrue(game.getShield().isPicked());

        game = new Game();
        game.getHero().setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "H");
        game.getMaze().setMaze(new Point(1, 1), "*");
        game.heroMove("a");
        assertEquals("A", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));
        assertTrue(game.getHero().hasDarts());

        //right
        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(2, 1), " ");
        game.heroMove("d");
        assertEquals("H", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(2, 1), "/");
        game.heroMove("d");
        assertEquals("A", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));
        assertTrue(game.getHero().hasSword());
        assertTrue(game.getSword().isPicked());

        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(2, 1), "e");
        game.heroMove("d");
        assertEquals("H", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));
        assertTrue(game.getHero().hasShield());
        assertTrue(game.getShield().isPicked());

        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "H");
        game.getMaze().setMaze(new Point(2, 1), "*");
        game.heroMove("d");
        assertEquals("A", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));
        assertTrue(game.getHero().hasDarts());
    }

    @Test
    public void testDragonMovement() {
        Game game = new Game();

        //up
        game.getDragons().get(0).setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "D");
        game.getMaze().setMaze(new Point(1, 1), " ");
        game.moveDragons(game.getDragons().get(0), 0);
        assertEquals("D", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));

        game.getDragons().get(0).setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "D");
        game.getMaze().setMaze(new Point(1, 1), "/");
        game.moveDragons(game.getDragons().get(0), 0);
        assertEquals("F", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));

        game.getDragons().get(0).setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "D");
        game.getMaze().setMaze(new Point(1, 1), "^");
        game.moveDragons(game.getDragons().get(0), 0);
        assertEquals("D", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(1, 2));

        //down
        game.getDragons().get(0).setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "D");
        game.getMaze().setMaze(new Point(1, 2), " ");
        game.moveDragons(game.getDragons().get(0), 1);
        assertEquals("D", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        game.getDragons().get(0).setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "D");
        game.getMaze().setMaze(new Point(1, 2), "/");
        game.moveDragons(game.getDragons().get(0), 1);
        assertEquals("F", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        game.getDragons().get(0).setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "D");
        game.getMaze().setMaze(new Point(1, 2), "^");
        game.moveDragons(game.getDragons().get(0), 1);
        assertEquals("D", game.getMaze().getCell(1, 2));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        //right
        game.getDragons().get(0).setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "D");
        game.getMaze().setMaze(new Point(2, 1), " ");
        game.moveDragons(game.getDragons().get(0), 2);
        assertEquals("D", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        game.getDragons().get(0).setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "D");
        game.getMaze().setMaze(new Point(2, 1), "/");
        game.moveDragons(game.getDragons().get(0), 2);
        assertEquals("F", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        game.getDragons().get(0).setCoord(1, 1);
        game.getMaze().setMaze(new Point(1, 1), "D");
        game.getMaze().setMaze(new Point(2, 1), "^");
        game.moveDragons(game.getDragons().get(0), 2);
        assertEquals("D", game.getMaze().getCell(2, 1));
        assertEquals(" ", game.getMaze().getCell(1, 1));

        //left
        game.getDragons().get(0).setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "D");
        game.getMaze().setMaze(new Point(1, 1), " ");
        game.moveDragons(game.getDragons().get(0), 3);
        assertEquals("D", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));

        game.getDragons().get(0).setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "D");
        game.getMaze().setMaze(new Point(1, 1), "/");
        game.moveDragons(game.getDragons().get(0), 3);
        assertEquals("F", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));

        game.getDragons().get(0).setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "D");
        game.getMaze().setMaze(new Point(1, 1), "^");
        game.moveDragons(game.getDragons().get(0), 3);
        assertEquals("D", game.getMaze().getCell(1, 1));
        assertEquals(" ", game.getMaze().getCell(2, 1));
    }

    @Test
    public void testThrowDart() {
        Game game;
        //test darts down and right
        game = new Game();
        game.getHero().setCoord(1, 1);
        game.getHero().grabDarts();
        game.getHero().grabDarts();
        assertTrue(game.getHero().hasDarts());
        assertEquals("A", game.getHero().getId());
        assertEquals(2, game.getDragons().size());
        game.getDragons().get(0).setCoord(2, 1);
        game.getMaze().setMaze(new Point(2, 1), "D");
        game.checkDartsDirection("d");
        game.getDragons().get(0).setCoord(1, 2);
        game.getMaze().setMaze(new Point(1, 2), "D");
        game.checkDartsDirection("s");
        assertEquals("H", game.getHero().getId());
        assertFalse(game.getHero().hasDarts());
        assertEquals(0, game.getDragons().size());

        //darts up and left
        game = new Game();
        game.getHero().setCoord(15, 15);
        game.getHero().grabDarts();
        game.getHero().grabDarts();
        assertTrue(game.getHero().hasDarts());
        assertEquals("A", game.getHero().getId());
        assertEquals(2, game.getDragons().size());
        game.getDragons().get(0).setCoord(15, 14);
        game.getMaze().setMaze(new Point(15, 14), "D");
        game.checkDartsDirection("w");
        assertEquals(1, game.getDragons().size());
        game.getDragons().get(0).setCoord(14, 15);
        game.getMaze().setMaze(new Point(14, 15), "D");
        game.checkDartsDirection("a");
        assertEquals("H", game.getHero().getId());
        assertFalse(game.getHero().hasDarts());
        assertEquals(0, game.getDragons().size());
    }

    @Test
    public void testEncounter() {
        Game game = new Game(11, 11, 1, 2);
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
        assertFalse(game.getHero().isAlive());
        assertFalse(game.getDragons().isEmpty());

        game = new Game(11, 11, 1, 2);
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
        assertTrue(game.getHero().isAlive());
        assertTrue(game.getDragons().isEmpty());


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
        assertFalse(game.getHero().isAlive());
        assertFalse(game.getDragons().isEmpty());
        assertTrue(game.getDragons().get(0).isAlive());


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
        assertTrue(game.getHero().isAlive());
        assertFalse(game.getDragons().isEmpty());
        assertTrue(game.getDragons().get(0).isAlive());
    }

    @Test
    public void testExit() {
        Game game = new Game(11, 11, 1, 2);
        game.initializePositionsElements(1);
        assertEquals("S", game.getMaze().getCell(game.getMaze().getExit().x, game.getMaze().getExit().y));
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
        assertNotEquals(game.getMaze().getExit(), game.getHero().getCoord());
        assertEquals("S", game.getMaze().getCell(game.getMaze().getExit().x, game.getMaze().getExit().y));
        assertFalse(game.isExitOpen());
        assertFalse(game.getGameState());

        assertEquals(1, game.getDragons().size());
        game.getDragons().remove(game.getDragons().get(0));
        assertEquals(0, game.getDragons().size());
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
        assertEquals(game.getMaze().getExit(), game.getHero().getCoord());
        assertEquals(game.getHero().getId(), game.getMaze().getCell(game.getMaze().getExit().x, game.getMaze().getExit()
                .y));
        assertTrue(game.isExitOpen());
        assertTrue(game.getGameState());
    }
}