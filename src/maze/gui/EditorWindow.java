package maze.gui;

import maze.logic.Game;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {

    private static final long serialVersionUID = 1L;


    private JSlider dimensionSlider;
    private JComboBox<?> typeSelector;
    private int mazeSize, typeDragons;
    private Game game;
    private GameConsole gConsole;

    public EditorWindow() {
        mazeSize = 11;
        gConsole = new GameConsole(true);
        setTitle("Editor");
        getContentPane().setPreferredSize(new Dimension(900, 500));
        initializeMazePreferences();
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
        JPanel mazeSettings = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        dimensionSlider = new JSlider();
        dimensionSlider.setMinorTickSpacing(2);
        dimensionSlider.setPaintLabels(true);
        dimensionSlider.setPaintTicks(true);
        dimensionSlider.setSnapToTicks(true);
        dimensionSlider.setMajorTickSpacing(10);
        dimensionSlider.setMaximum(55);
        dimensionSlider.setMinimum(5);
        dimensionSlider.setValue(mazeSize);
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

        JToggleButton erase = new JToggleButton("Erase");
        JToggleButton wall = new JToggleButton("Wall");
        JToggleButton dragon = new JToggleButton("Dragon");
        JToggleButton hero = new JToggleButton("Hero");
        JToggleButton sword = new JToggleButton("Sword");
        JToggleButton shield = new JToggleButton("Shield");
        JToggleButton dart = new JToggleButton("Dart");
        JToggleButton exit = new JToggleButton("Exit");
        ButtonGroup elements = new ButtonGroup();

        elements.add(erase);
        elements.add(wall);
        elements.add(dragon);
        elements.add(hero);
        elements.add(sword);
        elements.add(shield);
        elements.add(dart);
        elements.add(exit);

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

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
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
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            String message = "Do you want to cancel the operation? Your progress will not be saved!";
            int option = JOptionPane.showConfirmDialog(rootPane, message);
            if (option == JOptionPane.YES_OPTION) {
                setVisible(false);
            }
        });

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
