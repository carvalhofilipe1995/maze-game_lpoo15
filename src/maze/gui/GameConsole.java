package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.logic.Dragon;
import maze.logic.Game;

public class GameConsole extends JPanel implements ActionListener {

	int cellWidth, cellHeight;

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

	private boolean showBackground = false;

	private Game game;

	public GameConsole() {

		loadImages();

		game = new Game();
		game.initializePositionsElements(2);
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clean

		Graphics2D graphics = (Graphics2D) g;

		if (showBackground)
			graphics.drawImage(background, 0, 0, this.getWidth(),
					this.getHeight(), 0, 0, background.getWidth(),
					background.getHeight(), null);
		else
			drawGame(graphics);
	}

	public void drawGame(Graphics2D g2d) {

		// Drawing maze

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

	public static void main(String args[]) {
		JFrame f = new JFrame("Graphics");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(700, 700));
		JPanel panel = new GameConsole();
		f.getContentPane().add(panel);
		f.pack();
		f.setVisible(true);
	}

}
