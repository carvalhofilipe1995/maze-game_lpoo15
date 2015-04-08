package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/*
 * Represents the game window
 */

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton newGame;
	private JButton createGame;
	private JButton quitGame;
	private JButton gameOptions;
	private JButton SaveAndLoad;

	private JPanel panelButtonsUp;
	private JPanel panelButtonsDown;
	
	private OptionsWindow options;

	public GameWindow() {
		
		setTitle("The Labirinth");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panelButtonsUp = new JPanel();
		panelButtonsDown = new JPanel();
		
		options = new OptionsWindow();

		initializeButtons();
		getContentPane().setLayout(new BorderLayout(0, 0));

		addButtonsLayout();

	}

	public void initializeButtons() {

		// new game button
		newGame = new JButton("New game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to play a new game?";
				int option = JOptionPane.showConfirmDialog(rootPane, message);

				if (option == JOptionPane.YES_OPTION) {

				}
			}
		});

		// create game button
		createGame = new JButton("Create game");
		createGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to create a new game?";
				int option = JOptionPane.showConfirmDialog(rootPane, message);

				if (option == JOptionPane.YES_OPTION) {

				}
			}
		});

		// gameOptions button

		gameOptions = new JButton("Options");
		gameOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options.startOption();
			}
		});

		// SaveAndLoad button
		SaveAndLoad = new JButton("Save/Load");
		SaveAndLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// quit game button
		quitGame = new JButton("Quit game");
		quitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to leave the game?";
				int option = JOptionPane.showConfirmDialog(rootPane, message);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	public void addButtonsLayout() {

		panelButtonsUp.add(newGame);
		panelButtonsUp.add(createGame);
		panelButtonsUp.add(SaveAndLoad);
		getContentPane().add(panelButtonsUp, BorderLayout.PAGE_START);

		panelButtonsDown.add(quitGame);
		panelButtonsDown.add(gameOptions);
		getContentPane().add(panelButtonsDown, BorderLayout.PAGE_END);

	}

	public void startGameFrame() {
		setSize(600, 600);

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(size.width / 2 - getSize().width / 2, size.height / 2
				- getSize().height / 2);

		setVisible(true);
	}

	public static void main(String args[]) {
		GameWindow g = new GameWindow();
		g.startGameFrame();
	}

}
