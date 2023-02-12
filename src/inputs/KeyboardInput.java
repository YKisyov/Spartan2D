package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                //    System.out.println("it was W");
                gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                //    System.out.println("it was A");
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                //    System.out.println("it was S");
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                //    System.out.println("it was D");
                gamePanel.changeXDelta(5);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
