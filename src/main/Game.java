package main;

public class Game implements Runnable {
    private final int FPS_SET = 120;
    private GameWindow gameWindows;
    private GamePanel gamePanel;
    private Thread gameLoopThread;


    public Game() {
        gamePanel = new GamePanel();
        gameWindows = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        statGameLoop();
    }
    private void statGameLoop(){
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.d / FPS_SET;
        long lastFrameWasDrawnAt = System.nanoTime();
        long now;
        while (true) {
            now = System.nanoTime();
            if (now - lastFrameWasDrawnAt >= timePerFrame){
                gamePanel.repaint();
                lastFrameWasDrawnAt = now;
            }
        }

    }
}
