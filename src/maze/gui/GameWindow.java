package maze.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represents the game window
 */

public class GameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    //private Game game;

    private JButton newGame;
    private JButton createGame;
    private JButton quitGame;
    private JButton gameOptions;
    private JButton SaveAndLoad;

    private JPanel panelButtonsUp;
    private JPanel panelButtonsDown;

    private GameConsole gameConsole;
    private OptionsWindow options;
    private EditorWindow editor;
    private SaveAndLoad save_load;

    public GameWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        setTitle("The Labirinth");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelButtonsUp = new JPanel();
        panelButtonsDown = new JPanel();
        gameConsole = new GameConsole(false);
        options = new OptionsWindow(this, "Options", true);
        editor = new EditorWindow(this, "Editor", true);
        save_load = new SaveAndLoad(gameConsole);

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
                    setSize(642, 598);
                    getContentPane().add(gameConsole);

                    if (!save_load.getLoadCall())
                        gameConsole.startNewGame(options.getSizeLabirinth(),
                                options.getSizeLabirinth(),
                                options.getNumberDragons(),
                                options.getTypeDragons());
                    else
                        gameConsole.loadGame(save_load.LoadGame());


                }
                repaint();
                gameConsole.requestFocusInWindow();
            }
        });

        // edit button
        createGame = new JButton("Editor");
        createGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Do you want to create a new game?";
                int option = JOptionPane.showConfirmDialog(rootPane, message);

                if (option == JOptionPane.YES_OPTION) {
                    editor.startOption();
                    if (editor.getGame() != null) {
                        setSize(642, 598);
                        getContentPane().add(gameConsole);
                        gameConsole.loadGame(editor.getGame());
                        save_load.updateGamesSaved();
                        save_load.startWindow();
                        gameConsole.requestFocusInWindow();
                    }
                }
                repaint();
                gameConsole.requestFocusInWindow();

            }
        });

        // gameOptions button

        gameOptions = new JButton("Options");
        gameOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options.startOption();
                gameConsole.requestFocusInWindow();

            }
        });

        // SaveAndLoad button
        SaveAndLoad = new JButton("Save/Load");
        SaveAndLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_load.updateGamesSaved();
                save_load.startWindow();
                gameConsole.requestFocusInWindow();

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
        setSize(642, 597);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 2 - getSize().width / 2, size.height / 2
                - getSize().height / 2);

        setVisible(true);
    }

}
