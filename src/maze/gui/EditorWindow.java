package maze.gui;

import maze.logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorWindow extends JDialog {

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

    public EditorWindow(JFrame parent, String name, boolean modal) {
        super(parent, name, modal);
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

    public Game getGame() {
        return game;
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
        dimensionSlider.addChangeListener(e -> {
            if (dimensionSlider.getValue() != mazeSize) {
                mazeSize = dimensionSlider.getValue();
                startOption();
            }
        });

        String[] type = {"Static", "Roam",
                "Roam and Sleep"};
        typeSelector = new JComboBox<Object>(type);
        typeSelector.setSelectedIndex(0);
        typeSelector.addActionListener(e ->
                gConsole.setDragonType(typeSelector.getSelectedIndex() + 1));

        erase = new JToggleButton("Erase");
        erase.addMouseListener(new MouseAdapter() {
                                   @Override
                                   public void mousePressed(MouseEvent e) {
                                       gConsole.setElementID(0);
                                   }
                               }
        );
        wall = new JToggleButton("Wall");
        wall.addMouseListener(new MouseAdapter() {
                                  @Override
                                  public void mousePressed(MouseEvent e) {
                                      gConsole.setElementID(1);
                                  }
                              }
        );
        dragon = new JToggleButton("Dragon");
        dragon.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        gConsole.setElementID(2);
                                    }
                                }
        );
        hero = new JToggleButton("Hero");
        hero.addMouseListener(new MouseAdapter() {
                                  @Override
                                  public void mousePressed(MouseEvent e) {
                                      gConsole.setElementID(3);
                                  }
                              }
        );
        sword = new JToggleButton("Sword");
        sword.addMouseListener(new MouseAdapter() {
                                   @Override
                                   public void mousePressed(MouseEvent e) {
                                       gConsole.setElementID(4);
                                   }
                               }
        );
        shield = new JToggleButton("Shield");
        shield.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        gConsole.setElementID(5);
                                    }
                                }
        );
        dart = new JToggleButton("Dart");
        dart.addMouseListener(new MouseAdapter() {
                                  @Override
                                  public void mousePressed(MouseEvent e) {
                                      gConsole.setElementID(6);
                                  }
                              }
        );
        exit = new JToggleButton("Exit");
        exit.addMouseListener(new MouseAdapter() {
                                  @Override
                                  public void mousePressed(MouseEvent e) {
                                      gConsole.setElementID(7);
                                  }
                              }
        );
        elements = new ButtonGroup();
        elements.add(erase);
        elements.add(wall);
        elements.add(dragon);
        elements.add(hero);
        elements.add(sword);
        elements.add(shield);
        elements.add(dart);
        elements.add(exit);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            String message = "Do you want to create a game with this map?";
            int option = JOptionPane.showConfirmDialog(rootPane, message);

            if (option == JOptionPane.YES_OPTION) {
                game = gConsole.getGame();
                setVisible(false);
            } else if (option == JOptionPane.NO_OPTION) {
                game = null;
                setVisible(false);
            }

        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            String message = "Do you want to cancel the operation? Your progress will not be saved!";
            int option = JOptionPane.showConfirmDialog(rootPane, message);
            if (option == JOptionPane.YES_OPTION) {
                game = null;
                setVisible(false);
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
        repaint();
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
