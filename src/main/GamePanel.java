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
    private BufferedImage[][] animationsArr;
    private final MouseInput mouseInput;
    private Color myColor = new Color(150, 22, 69);
    private float xDelta = 100, yDelta = 100; //init position;
    private float xDirection = 0.5f, yDirection = 0.5f;
    private int animTick, animIndex, animSpeed = 15; //120 FPS / 8 animsPerSec = 15 speed

    //represents the len of each row from the anim Atlas .png file;
    int[] jaggedRowsLength;

    public GamePanel() {
        mouseInput = new MouseInput(this);
        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        jaggedRowsLength = new int[]
                {5, 6, 3, 1, 2, 4, 3, 3, 3};
        /* row 0 = player idleAnime;
         *  row 1 = player running;
         *  row 2 = player donno what action is that //todo figure it out;
         *  row 3 = Happy or / Angry I guess;
         *  row 4 =
         *  row 5 = takes damage
         *  row 6 = attack;
         *  row 7 = strongerAttack;
         *  row 8 = upper cut attack;
         * */

        importImage();
        loadAnimations();

    }

    private void loadAnimations() {
        int subImageWidth = 64;
        int subImageHeight = 40;

        //all other elements of animationArr will have null, set by JRE!!!
        animationsArr = new BufferedImage[9][6];
        for (int i = 0; i < animationsArr.length; i++) {
            for (int j = 0; j < jaggedRowsLength[i]; j++) {
                animationsArr[i][j] =
                        image.getSubimage(
                                j * subImageWidth,
                                i * subImageHeight,
                                subImageWidth,
                                subImageHeight);
            }

        }
        /*
        old crap from anim tutorial, don't like the way it was realized;
        for (int i = 0; i < animationsArr.length; i++) {
            animationsArr[i] = image.getSubimage(i * subImageWidth, rowNumberFromTheAtlasImgSeq * subImageHeight,
                    subImageWidth, subImageHeight);
        }*/
    }

    private void importImage() {
        String pathFromRootResDir = "/";
        String fileName = "player_sprites_test.png";
        InputStream inputStream =
                getClass().getResourceAsStream(pathFromRootResDir + fileName);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("Can't load a file named as:" + fileName
                    + "\n Make sure the file is present in the \"res\" folder\n");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        int animationRow = 8;
        updateAnimationTick(animationRow);
        g.drawImage(animationsArr[animationRow][animIndex], (int) xDelta, (int) yDelta, 128, 80, null);
    }

    private void updateAnimationTick(int animationRowAsPerAtlas) {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= jaggedRowsLength[animationRowAsPerAtlas]) {
                animIndex = 0;
            }
        }
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
