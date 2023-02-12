package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private BufferedImage image;
    private final MouseInput mouseInput;
    private Color myColor = new Color(150, 22, 69);
    private float xDelta = 100, yDelta = 100; //init position;
    private float xDirection = 0.5f, yDirection = 0.5f;

    public GamePanel() {
        mouseInput = new MouseInput(this);
        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        importImage();
    }

    private void importImage() {
        String pathFromRootResDir = "/";
        String fileName = "player_sprites.png";
        InputStream inputStream =
                getClass().getResourceAsStream(pathFromRootResDir+fileName);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("Can't load a file named as:" + fileName
            + "\n Make sure the file is present in the \"res\" folder\n");
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        /*
        1280 / 32px = 40 images wide;
        800  / 32px = 25 images in height;
        So we can fit 40 x 25 images each with size 32x32px without overlapping
        or going out of the border of the JFrame;
        */
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // it is clearing surface and allowing us to paint again;
       g.drawImage(image, 0, 0 , null);
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
