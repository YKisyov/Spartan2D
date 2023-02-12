package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInput mouseInput;
    private int xDelta = 100, yDelta = 100; //init position;
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
        g.fillRect(xDelta, yDelta, 200, 50);
        frames++;

        if (System.currentTimeMillis() - lastCheckedForNewFrameAt >= 1000) {
            lastCheckedForNewFrameAt = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames =  0;
        }
        repaint();
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
