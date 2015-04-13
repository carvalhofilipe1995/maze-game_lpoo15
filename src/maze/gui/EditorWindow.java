package maze.gui;

import maze.logic.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorWindow extends JFrame {

    private static final long serialVersionUID = 1L;


    private JSlider dimensionSlider;
    private JComboBox<?> typeSelector;
    private int mazeSize, typeDragons;
    private Game game;
    private GameConsole gConsole;
    private JPanel mazeSettings;
    private GridBagConstraints c;
    private JToggleButton erase;
    private JToggleButton wall;
    private JToggleButton dragon;
    private JToggleButton hero;
    private JToggleButton sword;
    private JToggleButton shield;
    private JToggleButton dart;
    private JToggleButton exit;
    private ButtonGroup elements;
    private JButton confirmButton;
    private JButton cancelButton;

    public EditorWindow() {
        mazeSize = 11;
        gConsole = new GameConsole(true);
        setTitle("Editor");
        getContentPane().setPreferredSize(new Dimension(900, 500));
        initializeMazePreferences();
        addMazePreferences();
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
                / 2 - this.getSize().height / 2);
    }

    public void initializeGameArea() {
        JPanel gameArea = new JPanel();
        gameArea.add(gConsole);
        gConsole.startNewCustomGame(game);
        getContentPane().add(gConsole);
    }

    public void initializeMazePreferences() {
        mazeSettings = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        dimensionSlider = new JSlider();
        dimensionSlider.setMinorTickSpacing(2);
        dimensionSlider.setPaintLabels(true);
        dimensionSlider.setPaintTicks(true);
        dimensionSlider.setSnapToTicks(true);
        dimensionSlider.setMajorTickSpacing(10);
        dimensionSlider.setMaximum(55);
        dimensionSlider.setMinimum(5);
        dimensionSlider.setValue(mazeSize);

        String[] type = {"Static", "Roam",
                "Roam and Sleep"};
        typeSelector = new JComboBox<Object>(type);
        typeSelector.setSelectedIndex(0);

        erase = new JToggleButton("Erase");
        wall = new JToggleButton("Wall");
        dragon = new JToggleButton("Dragon");
        hero = new JToggleButton("Hero");
        sword = new JToggleButton("Sword");
        shield = new JToggleButton("Shield");
        dart = new JToggleButton("Dart");
        exit = new JToggleButton("Exit");
        elements = new ButtonGroup();


        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to create a game with this map?";
	            int option = JOptionPane.showConfirmDialog(rootPane, message);

	            if (option == JOptionPane.YES_OPTION) {

	                int sizeLabirinth = dimensionSlider.getValue();
	                String type_dragons = typeSelector.getName();
	                if (type_dragons == "Static") {
	                    typeDragons = 1;
	                } else if (type_dragons == "Roam") {
	                    typeDragons = 2;
	                } else if (type_dragons == "Roam and Sleep") {
	                    typeDragons = 3;
	                }

	                setVisible(false);

	            } else if (option == JOptionPane.NO_OPTION) {
	                setVisible(false);
	            }
				
			}
		});

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to cancel the operation? Your progress will not be saved!";
	            int option = JOptionPane.showConfirmDialog(rootPane, message);
	            if (option == JOptionPane.YES_OPTION) {
	                setVisible(false);
	            }
			}
		});
    }

    public void addMazePreferences() {

        c.insets = new Insets(20, 20, 0, 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(dimensionSlider, c);

        String[] type = {"Static", "Roam",
                "Roam and Sleep"};
        typeSelector = new JComboBox<Object>(type);
        typeSelector.setSelectedIndex(0);
        c.insets = new Insets(0, 20, 0, 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;

        mazeSettings.add(Box.createRigidArea(new Dimension(0, 25)), c);
        mazeSettings.add(typeSelector, c);

        mazeSettings.add(Box.createRigidArea(new Dimension(0, 25)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(erase, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(wall, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(dragon, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(hero, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(sword, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(shield, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(dart, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(exit, c);


        mazeSettings.add(Box.createRigidArea(new Dimension(0, 50)), c);
        c.insets = new Insets(0, 20, 20, 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(confirmButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = GridBagConstraints.RELATIVE;
        mazeSettings.add(cancelButton, c);
        getContentPane().add(mazeSettings, BorderLayout.EAST);
    }

    public void startOption() {
        generateEmptyMaze();
        initializeGameArea();
        setVisible(true);
    }

    public void generateEmptyMaze() {
        game = new Game(mazeSize, mazeSize, 0, typeDragons);
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (i == 0 || i == mazeSize - 1 || j == 0 || j == mazeSize - 1) {
                    game.getMaze().setMaze(new Point(i, j), "X");
                } else {
                    game.getMaze().setMaze(new Point(i, j), " ");
                }
            }
        }
    }
}
