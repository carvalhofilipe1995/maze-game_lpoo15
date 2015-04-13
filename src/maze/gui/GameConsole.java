package maze.gui;

import maze.logic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameConsole extends JPanel implements ActionListener {

    private int cellWidth, cellHeight;
    private BufferedImage heroWithSword;
    private BufferedImage heroWithOutSword;
    private BufferedImage darts;
    private BufferedImage sword;
    private BufferedImage shield;
    private BufferedImage dragonAwake;
    private BufferedImage dragonSleep;
    private BufferedImage path;
    private BufferedImage wall;
    private BufferedImage exit;
    private BufferedImage background;
    private boolean activeGame = false, editorMode;
    private Game game;
    private int elementID;

    // Load information

    public GameConsole(boolean editor) {
        editorMode = editor;
        elementID = -1;
        addKeyListener(new KeyBoard());
        addMouseListener(new Mouse());
        setFocusable(true);
        setDoubleBuffered(true);

        loadImages();
    }
    
    public void loadGame(Game g){
    	this.game = g;
    }

    public void startNewGame(int width, int heigth, int numberDragons,
                             int typeDragons) {

        game = new Game(width, heigth, numberDragons, typeDragons);

        game.initializePositionsElements(numberDragons);

        initGame();
    }

    public void startNewCustomGame(Game game) {
        this.game = game;
        initGame();
    }

    public void initGame() {
    	activeGame = false;
        setVisible(true);
        requestFocus();
        repaint();
    }

    public void loadImages() {
        try {

            heroWithSword = ImageIO.read(new File("images/heroWithSword.jpg"));
            heroWithOutSword = ImageIO.read(new File(
                    "images/heroWithOutSword.jpg"));

            darts = ImageIO.read(new File("images/darts.jpg"));
            sword = ImageIO.read(new File("images/sword.jpg"));
            shield = ImageIO.read(new File("images/shield.jpg"));

            dragonAwake = ImageIO.read(new File("images/dragonAwake.jpg"));
            dragonSleep = ImageIO.read(new File("images/dragonSleep.jpg"));

            path = ImageIO.read(new File("images/path.jpg"));
            wall = ImageIO.read(new File("images/wall.jpg"));
            exit = ImageIO.read(new File("images/closedExit.jpg"));

        } catch (IOException e) {

        }
    }

    // Drawing

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clean
        Graphics2D graphics = (Graphics2D) g;

		/*
         * if (showBackground) graphics.drawImage(background, 0, 0,
		 * this.getWidth(), this.getHeight(), 0, 0, background.getWidth(),
		 * background.getHeight(), null); else
		 */

        drawGame(graphics);
    }

    public void drawGame(Graphics2D g2d) {

        for (int i = 0; i < game.getMaze().getWidth(); i++)
            for (int j = 0; j < game.getMaze().getHeight(); j++)
                if (game.getMaze().getCell(i, j) == "X")
                    drawCellsMaze(g2d, wall, i, j);
                else if (game.getMaze().getCell(i, j) == " ")
                    drawCellsMaze(g2d, path, i, j);
                else if (game.getMaze().getCell(i, j) == "S")
                    drawCellsMaze(g2d, exit, i, j);
                else if (game.getMaze().getCell(i, j) == "D")
                    drawCellsMaze(g2d, dragonAwake, i, j);
                else if (game.getMaze().getCell(i, j) == "d")
                    drawCellsMaze(g2d, dragonSleep, i, j);
                else if (game.getMaze().getCell(i, j) == "H")
                    drawCellsMaze(g2d, heroWithOutSword, i, j);
                else if (game.getMaze().getCell(i, j) == "A")
                    drawCellsMaze(g2d, heroWithSword, i, j);
                else if (game.getMaze().getCell(i, j) == "*")
                    drawCellsMaze(g2d, darts, i, j);
                else if (game.getMaze().getCell(i, j) == "/")
                    drawCellsMaze(g2d, sword, i, j);
                else if (game.getMaze().getCell(i, j) == "e")
                    drawCellsMaze(g2d, shield, i, j);

    }

    public void drawCellsMaze(Graphics2D g, BufferedImage image, int i, int j) {

        cellWidth = this.getWidth() / this.game.getMaze().getWidth();
        cellHeight = (int) (cellWidth / (131.0 / 101.0));
        int temp = (int) (81.0 * cellHeight / 131.0);

        if (this.getHeight() < temp * game.getMaze().getHeight()) {
            cellHeight = this.getHeight() / game.getMaze().getHeight();
            cellHeight += 81.0 * cellHeight / 131.0;
            cellWidth = (int) (cellHeight * 101.0 / 131.0);
        }

        int yCorrection = (int) (-50.0 * cellHeight / 131.0);

        int dstX = i * cellWidth;

        int dstY;

        dstY = j * (yCorrection + cellHeight);

        // centering board
        dstX += (getWidth() - cellWidth * game.getMaze().getWidth()) / 2.0;
        dstY += (getHeight() - (cellHeight - 0.37 * cellHeight)
                * game.getMaze().getHeight()) / 2.0;

        g.drawImage(image, dstX, dstY, dstX + cellWidth, dstY + cellHeight, 0,
                0, image.getWidth(null), image.getHeight(null), null);

    }

    // Update Game State

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    Point findCellinMaze(Point p) {
        int i = 0, j = 0;
        for (i = 0; i < game.getMaze().getWidth(); i++) {
            for (j = 0; j < game.getMaze().getHeight(); j++) {
                cellWidth = this.getWidth() / this.game.getMaze().getWidth();
                cellHeight = (int) (cellWidth / (131.0 / 101.0));
                int temp = (int) (81.0 * cellHeight / 131.0);

                if (this.getHeight() < temp * game.getMaze().getHeight()) {
                    cellHeight = this.getHeight() / game.getMaze().getHeight();
                    cellHeight += 81.0 * cellHeight / 131.0;
                    cellWidth = (int) (cellHeight * 101.0 / 131.0);
                }

                int yCorrection = (int) (-50.0 * cellHeight / 131.0);

                int dstX = i * cellWidth;

                int dstY = j * (yCorrection + cellHeight);

                if (p.x > dstX && p.x < dstX + cellWidth && p.y > dstY && p.y < dstY + cellHeight)
                    break;
            }
        }
        return new Point(i, j);
    }

    void editCell(Point p, String id) {
        if (id.equals(" ")) {
            eraseCell(p);
        } else {
            game.getMaze().setMaze(p, id);
        }
    }

    void eraseCell(Point p) {
        //if()
        game.getMaze().setMaze(p, " ");

    }

    private class KeyBoard extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if (!editorMode) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_RIGHT)
                    game.updateGameState("d");
                else if (key == KeyEvent.VK_LEFT)
                    game.updateGameState("a");
                else if (key == KeyEvent.VK_UP)
                    game.updateGameState("w");
                else if (key == KeyEvent.VK_DOWN)
                    game.updateGameState("s");
                else if (key == KeyEvent.VK_SPACE) {

                    if (game.getHero().hasDarts()) {
                        String[] options = {"UP", "DOWN", "RIGHT", "LEFT"};
                        String message = "Choose only one?";

                        int direction = JOptionPane.showOptionDialog(getRootPane(),
                                message, "Direction Darts",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, options,
                                options[0]);

                        switch (direction) {
                            case 0:
                                game.checkDartsDirection("w");
                                break;
                            case 1:
                                game.checkDartsDirection("s");
                                break;
                            case 2:
                                game.checkDartsDirection("a");
                                break;
                            case 3:
                                game.checkDartsDirection("d");
                                break;
                        }

                    } else {
                        JOptionPane.showMessageDialog(getRootPane(),
                                "Hero doesn't have darts!");
                    }
                }

                game.checkIfDragonIsNear();

                if (!(!game.getGameState() && game.getHero().isAlive())) {

                    if (game.getHero().isAlive()) {
                        JOptionPane.showMessageDialog(getRootPane(),
                                "Congratulations! You win!");
                        setVisible(false);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(getRootPane(),
                                "Try again! You lose!");
                        setVisible(false);
                        return;
                    }
                }
                repaint();
            }
        }
    }

    private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (editorMode) {
                int key = e.getButton();
                Point p = e.getPoint();
                if (key == MouseEvent.BUTTON1) {
                    switch (elementID) {
                        case 0:
                            editCell(findCellinMaze(p), "S");
                            break;
                        case 1:
                            editCell(findCellinMaze(p), "D");
                            break;
                        case 2:
                            editCell(findCellinMaze(p), "H");
                            break;
                        case 3:
                            editCell(findCellinMaze(p), "/");
                            break;
                        case 4:
                            editCell(findCellinMaze(p), "e");
                            break;
                        case 5:
                            editCell(findCellinMaze(p), "*");
                            break;
                        default:
                            break;
                    }
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    eraseCell(findCellinMaze(p));
                }

            }
        }
    }
    
    public boolean activeGame(){
    	return this.activeGame;
    }
    
    public Game getGame(){
    	return this.game;
    }
}
