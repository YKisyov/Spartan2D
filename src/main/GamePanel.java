package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInput mouseInput;
    private int xDelta = 100, yDelta = 100; //init position;
    private int frames;

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

        if

    }


    public void setRectPosition(int x, int y) {
        if (areXYinTheFrame(x, y)){
            xDelta = x;
            yDelta = y;
            repaint();
        }
    }

    private boolean areXYinTheFrame(int x, int y) {
        return ((0 <= x && x <= 400) && (0 <= y && y <= 400));
    }

    public void changeXDelta(int value) {
        xDelta += value;
        repaint();
    }

    public void changeYDelta(int value) {
        yDelta += value;
        repaint();
    }
}
