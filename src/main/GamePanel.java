package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInput mouseInput;
    private float xDelta = 100, yDelta = 100; //init position;
    private float xDirection = 0.09f, yDirection = 0.09f;
    private int frames;
    private long lastCheckedForNewFrameAt = 0L;

    public GamePanel() {
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // it is clearing surface and allowing us to paint again;
        updateRectangle();

        g.setColor(new Color(150, 20, 90));
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
        frames++;

        if (System.currentTimeMillis() - lastCheckedForNewFrameAt >= 1000) {
            lastCheckedForNewFrameAt = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
        repaint();
    }

    private void updateRectangle() {
        xDelta += xDirection;
        if (xDelta > 400 || xDelta < 0) {
            xDirection *= -1;
        }
        yDelta += yDirection;
        if (yDelta > 400 || yDelta < 0)
            yDirection *= -1;
    }


    public void setRectPosition(int x, int y) {
        xDelta = x;
        yDelta = y;
    }

    public void changeXDelta(int value) {
        xDelta += value;
    }

    public void changeYDelta(int value) {
        yDelta += value;
    }
}
