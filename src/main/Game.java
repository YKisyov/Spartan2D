package main;

public class Game {
    private GameWindow gameWindows;
    private GamePanel gamePanel;

    public Game() {
        gamePanel = new GamePanel();
        gameWindows = new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }
}
