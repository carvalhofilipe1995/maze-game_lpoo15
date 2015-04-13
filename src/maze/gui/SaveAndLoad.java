package maze.gui;

import maze.logic.Game;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SaveAndLoad extends JDialog {

    private static final long serialVersionUID = 1L;
    private GameConsole gamePanel;
    private JTextField nameFileToSave;
    private List gamesList;

    private String GamesFolder = "Saved";

    public SaveAndLoad(GameConsole game) {

        this.gamePanel = game;

        setTitle("Save and load options");

        getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

        initializeLoadSection();
        initializeSaveSection();

        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
                / 2 - this.getSize().height / 2);

    }

    public void initializeSaveSection() {

        JPanel SaveGame = new JPanel();
        SaveGame.setBackground(Color.orange);
        getContentPane().add(SaveGame);
        SaveGame.setLayout(new BorderLayout(0, 0));

        JLabel lblSave = new JLabel("Save Section");
        SaveGame.add(lblSave, BorderLayout.NORTH);
        lblSave.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel SaveGameInputPanel = new JPanel();
        SaveGame.add(SaveGameInputPanel, BorderLayout.CENTER);

        JLabel lblName = new JLabel("Name:");
        SaveGameInputPanel.add(lblName);

        nameFileToSave = new JTextField();
        SaveGameInputPanel.add(nameFileToSave);
        nameFileToSave.setColumns(10);

        JButton saveButton = new JButton("Save");
        SaveGameInputPanel.add(saveButton);
        saveButton.addActionListener(e -> SaveGame());

    }

    public void initializeLoadSection() {
        JPanel LoadGame = new JPanel();
        LoadGame.setBackground(Color.orange);
        getContentPane().add(LoadGame);
        LoadGame.setLayout(new BorderLayout(0, 0));

        JLabel lblLoad = new JLabel("Load Section");
        lblLoad.setHorizontalAlignment(SwingConstants.CENTER);
        LoadGame.add(lblLoad, BorderLayout.NORTH);

        JPanel ListPanel = new JPanel();
        LoadGame.add(ListPanel);

        gamesList = new List();
        ListPanel.add(gamesList);

        JButton loadButton = new JButton("Load");
        ListPanel.add(loadButton);
        loadButton.addActionListener(arg0 -> {
            Game loadedGame = LoadGame();

            if (loadedGame != null)
                gamePanel.loadGame(loadedGame);
        });

    }

    public void startWindow() {
        setVisible(true);
    }

    public void SaveGame() {
        if (!gamePanel.activeGame()) {
            JOptionPane.showMessageDialog(null,
                    "There is no active game to be saved.");
            return;
        }

        if (nameFileToSave.getText().length() == 0) {
            JOptionPane.showMessageDialog(null,
                    "You must specify a name to save the game.");
            return;
        }

        File savesFolder = new File(GamesFolder);
        if (!savesFolder.exists())
            savesFolder.mkdir();

        ObjectOutputStream file;
        try {
            file = new ObjectOutputStream(new FileOutputStream(GamesFolder
                    + "/" + nameFileToSave.getText()));
            file.writeObject(gamePanel.getGame());
            file.close();
            JOptionPane.showMessageDialog(null, "Game successfully saved.");
            setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "An error occured while saving the game.");
        }

    }

    public Game LoadGame() {

        if (gamesList.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "You must select a game to load.");
            return null;
        }

        try {
            FileInputStream fin = new FileInputStream(GamesFolder + "/"
                    + gamesList.getSelectedItem());
            ObjectInputStream ois = new ObjectInputStream(fin);
            Game game = (Game) ois.readObject();
            ois.close();
            setVisible(false);

            return game;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "An error occured while loading the game.");
            return null;
        }

    }

    public void updateGamesSaved() {
        File folder = new File(GamesFolder);
        if (!folder.isDirectory())
            return;

        gamesList.removeAll();
        for (File file : folder.listFiles())
            gamesList.add(file.getName());
    }

}
