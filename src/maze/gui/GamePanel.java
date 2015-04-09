package maze.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Game;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage heroWithSword;
	private BufferedImage heroWithOutSword;

	private BufferedImage dragonAwake;
	private BufferedImage dragonSleep;

	private BufferedImage darts;
	private BufferedImage sword;

	private BufferedImage path;
	private BufferedImage wall;
	
	private Game game;
	
	
	public GamePanel(){
		loadImages();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void initializeGame() {
		game = new Game();
	}

	public void initializeGame(int size_maze, int number_dragons,
			int type_dragons) {
		game = new Game(size_maze,size_maze,number_dragons,type_dragons);
	}

	public void loadImages() {

		try {

			heroWithSword = ImageIO.read(new File("images/heroWithSword.jpg"));
			dragonAwake = ImageIO.read(new File("images/dragonAwake.jpg"));
			dragonSleep = ImageIO.read(new File("images/dragonSleep.jpg"));
			sword = ImageIO.read(new File("images/sword.jpg"));
			path = ImageIO.read(new File("images/path.jpg"));
			wall = ImageIO.read(new File("images/ wall.jpg"));
			

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphs = (Graphics2D) g;
		
		drawGame(graphs);
	}
	
	
	public void drawGame(Graphics g2d){
		for(int i = 0; i < game.getMaze().getHeight(); i++){
			for(int j = 0; j < game.getMaze().getWidth(); j++){
				
			}
		}
	}
	

}
