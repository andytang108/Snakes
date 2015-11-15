/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snakes;

/**
 *
 * @author Jingyi_Tang
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Control  implements KeyListener{
    Model model;

    public Control(Model model){
        this.model = model;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (model.running){
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    model.changeDirection(Model.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    model.changeDirection(Model.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    model.changeDirection(Model.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    model.changeDirection(Model.RIGHT);
                    break;
                case KeyEvent.VK_ADD:
                case KeyEvent.VK_PAGE_UP:
                    model.speedUp();
                    break;
                case KeyEvent.VK_SUBTRACT:
                case KeyEvent.VK_PAGE_DOWN:
                    model.speedDown();
                    break;
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_P:
                    model.changePauseState();
                    break;
                default:
            }
        }

        // 任何情况下处理的按键，按键导致重新启动游戏
        if (keyCode == KeyEvent.VK_R ||
                keyCode == KeyEvent.VK_S ||
                keyCode == KeyEvent.VK_ENTER) {
            model.reset();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}
