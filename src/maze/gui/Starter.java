package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Starter extends JFrame {

	private JButton newGame;
	private JButton exitGame;
	private JFrame game;
	private BufferedImage backgroundImage = null;

	public Starter() {
		initializeButtons();
	}

	public void initializeButtons() {

		game = new JFrame("The labirinth");
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setSize(750, 750);
		game.getContentPane().setLayout(null);
		game.getContentPane().setBackground(Color.BLACK);
		
		// Play game Button
		newGame = new JButton("New game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Starts a new game

			}
		});

		newGame.setBounds(275, 250, 200, 100);
		newGame.setBackground(Color.GREEN);
		newGame.setOpaque(true);

		// Exit Game Button
		exitGame = new JButton("Exit");

		exitGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String msg = "Do you want to quit?";
				int res = JOptionPane.showConfirmDialog(rootPane, msg);

				if (res == JOptionPane.YES_OPTION) {
					game.setVisible(false);
					System.exit(0);
				}

			}

		});

		exitGame.setBounds(275, 450, 200, 100);
		exitGame.setBackground(Color.RED);
		exitGame.setOpaque(true);

		game.getContentPane().add(newGame);
		game.getContentPane().add(exitGame);
		game.setVisible(true);
	}


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Starter g = new Starter();
			}
		});
	}
}
