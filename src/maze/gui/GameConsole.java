package maze.gui;

import maze.logic.Dragon;
import maze.logic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
    private boolean activeGame = false, editorMode;
    private Game game;
    private int elementID, dragonType;
    
    private int upKey = KeyEvent.VK_W;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int downKey = KeyEvent.VK_S;
	private int sendDartsKey = KeyEvent.VK_E;
	
    public GameConsole(boolean editor) {
        editorMode = editor;
        elementID = -1;
        addKeyListener(new KeyBoard());
        addMouseListener(new Mouse());
        setFocusable(true);
        setDoubleBuffered(true);

        loadImages();
    }

    public void loadGame(Game g) {
        this.game = g;  
        initGame();
        
    }
    
    public void setDragonType(int dragonType) {
        this.dragonType = dragonType;
    }
    // Load information

    public void setElementID(int elementID) {
        this.elementID = elementID;
    }

    public void startNewGame(int width, int height, int numberDragons,
                             int typeDragons) {

        game = new Game(height, width, numberDragons, typeDragons);
        game.initializePositionsElements(numberDragons);
        
        initGame();
    }

    public void startNewCustomGame(Game game) {
        this.game = game;
        initGame();
    }

    public void initGame() {
        activeGame = true;
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
        drawGame(graphics);
    }

    public void drawGame(Graphics2D g2d) {

        for (int i = 0; i < game.getMaze().getWidth(); i++)
            for (int j = 0; j < game.getMaze().getHeight(); j++)
                if (Objects.equals(game.getMaze().getCell(i, j), "X"))
                    drawCellsMaze(g2d, wall, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), " "))
                    drawCellsMaze(g2d, path, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "S"))
                    drawCellsMaze(g2d, exit, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "D"))
                    drawCellsMaze(g2d, dragonAwake, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "d"))
                    drawCellsMaze(g2d, dragonSleep, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "H"))
                    drawCellsMaze(g2d, heroWithOutSword, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "A"))
                    drawCellsMaze(g2d, heroWithSword, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "*"))
                    drawCellsMaze(g2d, darts, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "/"))
                    drawCellsMaze(g2d, sword, i, j);
                else if (Objects.equals(game.getMaze().getCell(i, j), "e"))
                    drawCellsMaze(g2d, shield, i, j);

    }

    public void drawCellsMaze(Graphics2D g, BufferedImage image, int i, int j) {
        int dstX, dstY, yCorrection;

        cellWidth = this.getWidth() / this.game.getMaze().getWidth();
        cellHeight = (int) (cellWidth / (131.0 / 101.0));
        int temp = (int) (81.0 * cellHeight / 131.0);

        if (this.getHeight() < temp * game.getMaze().getHeight()) {
            cellHeight = this.getHeight() / game.getMaze().getHeight();
            cellHeight += 81.0 * cellHeight / 131.0;
            cellWidth = (int) (cellHeight * 101.0 / 131.0);
        }

        yCorrection = (int) (-50.0 * cellHeight / 131.0);

        dstX = i * cellWidth;

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

    Point findCellInMaze(Point p) {
        int dstX, dstY, yCorrection, i, j;

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

                yCorrection = (int) (-50.0 * cellHeight / 131.0);

                dstX = i * cellWidth;

                dstY = j * (yCorrection + cellHeight);

                // centering board
                dstX += (getWidth() - cellWidth * game.getMaze().getWidth()) / 2.0;
                dstY += (getHeight() - (cellHeight - 0.37 * cellHeight)
                        * game.getMaze().getHeight()) / 2.0;
                if (p.x > dstX && p.x < dstX + cellWidth && p.y > dstY && p.y < dstY + cellHeight)
                    return new Point(i, j);

            }
        }
        return null;
    }

    void editCell(Point p, String id) {
        if (p.x > 0 && p.x < game.getMaze().getWidth() - 1 && p.y > 0 && p.y < game.getMaze().getHeight() - 1) {
            eraseCell(p);
            switch (id) {
                case "S":
                    return;
                case "D":
                    game.getDragons().add(new Dragon(p.x, p.y));
                    break;
                case "H":
                    eraseCell(game.getHero().getCoord());
                    game.getHero().setCoord(p.x, p.y);
                    break;
                case "/":
                    eraseCell(game.getSword().getCoord());
                    game.getSword().setCoord(p.x, p.y);
                    break;
                case "e":
                    eraseCell(game.getShield().getCoord());
                    game.getShield().setCoord(p.x, p.y);
                    break;
            }
            game.getMaze().setMaze(p, id);
        } else {
            eraseCell(p);
            if (id.equals("S")) {
                eraseCell(game.getMaze().getExit());
                game.getMaze().setExit(p.x, p.y);
                game.getMaze().setMaze(p, id);
            } else {
                game.getMaze().setMaze(p, "X");
            }

        }
    }

    void eraseCell(Point p) {
        String fill;
        if (p.x > 0 && p.x < game.getMaze().getWidth() - 1 && p.y > 0 && p.y < game.getMaze().getHeight() - 1) {
            fill = " ";
        } else {
            fill = "X";
        }
        if (game.getMaze().getCell(p.x, p.y).equals("D")) {
            for (int i = 0; i < game.getDragons().size(); i++)
                game.getDragons().remove(i);
            game.getMaze().setMaze(p, fill);
        } else if (game.getMaze().getCell(p.x, p.y).equals("H")) {
            game.getMaze().setMaze(p, fill);
            game.getHero().setCoord(0, 0);
        } else if (game.getMaze().getCell(p.x, p.y).equals("/")) {
            game.getMaze().setMaze(p, fill);
            game.getSword().setCoord(0, 0);
        } else if (game.getMaze().getCell(p.x, p.y).equals("e")) {
            game.getMaze().setMaze(p, fill);
            game.getShield().setCoord(0, 0);
        } else
            game.getMaze().setMaze(p, fill);
    }

    public boolean activeGame() {
        return this.activeGame;
    }

    public Game getGame() {
        return this.game;
    }

    private class KeyBoard extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if (!editorMode) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_RIGHT || key == rightKey)
                    game.updateGameState("d");
                else if (key == KeyEvent.VK_LEFT || key == leftKey)
                    game.updateGameState("a");
                else if (key == KeyEvent.VK_UP || key == upKey)
                    game.updateGameState("w");
                else if (key == KeyEvent.VK_DOWN || key == downKey)
                    game.updateGameState("s");
                else if (key == KeyEvent.VK_SPACE || key == sendDartsKey) {

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
                            eraseCell(findCellInMaze(p));
                            break;
                        case 1:
                            editCell(findCellInMaze(p), "X");
                            break;
                        case 2:
                            editCell(findCellInMaze(p), "D");
                            break;
                        case 3:
                            editCell(findCellInMaze(p), "H");
                            break;
                        case 4:
                            editCell(findCellInMaze(p), "/");
                            break;
                        case 5:
                            editCell(findCellInMaze(p), "e");
                            break;
                        case 6:
                            editCell(findCellInMaze(p), "*");
                            break;
                        case 7:
                            editCell(findCellInMaze(p), "S");
                            break;
                        default:
                            break;
                    }
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    eraseCell(findCellInMaze(p));
                }
                repaint();
            }
        }
    }
    
    public int getKey(String str) {
		str = str.toUpperCase();
		
		switch (str) {
		case "A": return KeyEvent.VK_A;
		case "B": return KeyEvent.VK_B;
		case "C": return KeyEvent.VK_C;
		case "D": return KeyEvent.VK_D;
		case "E": return KeyEvent.VK_E;
		case "F": return KeyEvent.VK_F;
		case "G": return KeyEvent.VK_G;
		case "H": return KeyEvent.VK_H;
		case "I": return KeyEvent.VK_I;
		case "J": return KeyEvent.VK_J;
		case "K": return KeyEvent.VK_K;
		case "L": return KeyEvent.VK_L;
		case "M": return KeyEvent.VK_M;
		case "N": return KeyEvent.VK_N;
		case "O": return KeyEvent.VK_O;
		case "P": return KeyEvent.VK_P;
		case "Q": return KeyEvent.VK_Q;
		case "R": return KeyEvent.VK_R;
		case "S": return KeyEvent.VK_S;
		case "T": return KeyEvent.VK_T;
		case "U": return KeyEvent.VK_U;
		case "V": return KeyEvent.VK_V;
		case "W": return KeyEvent.VK_W;
		case "X": return KeyEvent.VK_X;
		case "Y": return KeyEvent.VK_Y;
		case "Z": return KeyEvent.VK_Z;
		}
		
		return 0;
	}
}
