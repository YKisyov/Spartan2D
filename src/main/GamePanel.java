package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    //TODO remove this code once we are happy with the addition of new rects onMouseClicks
    public class MyRect {
        int x, y, w, h;
        int xDirection = 1, yDirection = 1;
        Color color;

        public MyRect(int x, int y) {
            this.x = x;
            this.y = y;
            w = randomObj.nextInt(50);
            h = w;
            color = generateRandomColor();
        }

        public void updateRect() {
            x += xDirection;
            y += yDirection;
            if ((x + w) > 400 || x < 0){
                xDirection *= -1;
                color = generateRandomColor();
            }
            if ((y+h) > 400 || y < 0){
                yDirection *= -1;
                color = generateRandomColor();
            }
        }
        public void draw(Graphics g){
            g.setColor(color);
            g.fillRect(x, y, w, h);
        }
    }


    private MouseInput mouseInput;
    private Color myColor = new Color(150, 22, 69);
    Random randomObj;
    private float xDelta = 100, yDelta = 100; //init position;
    private float xDirection = 1.f, yDirection = 1.f;

    //TODO ALSO TO REMOVE THIS ONE:
    private ArrayList<MyRect> rectList = new ArrayList<>();

    //todo remove this one as well: ->>
    public void spawnRect(int x, int y){
        rectList.add(new MyRect(x, y));
    }

    public GamePanel() {
        randomObj = new Random();
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // it is clearing surface and allowing us to paint again;

        for (MyRect rect : rectList){
            rect.updateRect();
            rect.draw(g);
        }

        updateRectangle();

        g.setColor(myColor);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);

    }

    private void updateRectangle() {
        float xBottomRightCorner = xDelta + 200;
        float yBottomRightCorner = yDelta + 50;
        xDelta += xDirection;
        if (xDelta > 400 || xDelta < 0) {
            xDirection *= -1;
            //randomly change the color
            myColor = generateRandomColor();
        }

        yDelta += yDirection;
        if (yDelta > 400 || yDelta < 0) {
            yDirection *= -1;
            //randomly change the color
            myColor = generateRandomColor();
        }
    }

    private Color generateRandomColor() {
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
