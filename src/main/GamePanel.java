package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInput mouseInput;
    private Color myColor = new Color(150, 22, 69);
    Random randomObj;
    private float xDelta = 100, yDelta = 100; //init position;
    private float xDirection = 1.f, yDirection = 1.f;
    private int frames;
    private long lastCheckedForNewFrameAt = 0L;

    public GamePanel() {
        randomObj = new Random();
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // it is clearing surface and allowing us to paint again;
        updateRectangle();

        g.setColor(myColor);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
        frames++;

        if (System.currentTimeMillis() - lastCheckedForNewFrameAt >= 1000) {
            lastCheckedForNewFrameAt = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }

    private void updateRectangle() {
        float xBottomRightCorner = xDelta + 200;
        float yBottomRightCorner = yDelta + 50;
        xDelta += xDirection;
        if (xDelta > 400 || xDelta < 0) {
            xDirection *= -1;
            //randomly change the color
            myColor = randomColor();
        }

        yDelta += yDirection;
        if (yDelta > 400 || yDelta < 0) {
            yDirection *= -1;
            //randomly change the color
            myColor = randomColor();
        }
    }

    private Color randomColor() {
        int r = randomObj.nextInt(255);
        int g = randomObj.nextInt(255);
        int b = randomObj.nextInt(255);
        return new Color(r, g, b);
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
