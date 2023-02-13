package main;

import inputs.KeyboardInput;
import inputs.MouseInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {
    private BufferedImage image;
    private BufferedImage[][] animationsArr;
    private final MouseInput mouseInput;
    private Color myColor = new Color(150, 22, 69);
    private float xDelta = 100, yDelta = 100; //init position;
    private float xDirection = 0.5f, yDirection = 0.5f;
    private int animTick, animIndex, animSpeed = 15; //120 FPS / 8 animsPerSec = 15 speed
    //represents the len of each row from the anim Atlas .png file;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean isPlayerMoving = false;

    public GamePanel() {
        mouseInput = new MouseInput(this);
        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        importImage();
        loadAnimations();
    }

    public void setPlayerMoving(boolean isPlayerMoving) {
        this.isPlayerMoving = isPlayerMoving;
    }

    public void setPlayerDirection(int direction) {
        playerDirection = direction;
        isPlayerMoving = true;
    }

    private void loadAnimations() {
        int subImageWidth = 64;
        int subImageHeight = 40;

        //all other elements of animationArr will have null, set by JRE!!!
        animationsArr = new BufferedImage[9][6];
        for (int i = 0; i < animationsArr.length; i++) {
            for (int j = 0; j < animationsArr[i].length; j++) {
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
        String fileName = "player_sprites.png";
        InputStream inputStream =
                getClass().getResourceAsStream(pathFromRootResDir + fileName);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("Can't load a file named as:" + fileName
                    + "\n Make sure the file is present in the \"res\" folder\n");
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
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

        setAnimation();
        updatePosition();
        int magnificationFactor = 4;
        updateAnimationTick();
        g.drawImage(animationsArr[playerAction][animIndex],
                (int) xDelta, (int) yDelta,
                64 * magnificationFactor, 40 * magnificationFactor, null);
    }

    private void updatePosition() {
        if (isPlayerMoving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }

        }


    }

    private void setAnimation() {
        if (isPlayerMoving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= getSpriteAmount(playerAction)) {
                animIndex = 0;
            }
        }
    }


}