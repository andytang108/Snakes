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


public class Control implements KeyListener{
    Model model = null;
    ServerModel smodel = null;

    public Control(Model model){
        this.model = model;
    }
    public Control(ServerModel model){
        this.smodel = model;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(model != null){
            if (model.isRunning()){
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
        }
        // 按键导致重新启动游戏
        if (keyCode == KeyEvent.VK_R ||
                keyCode == KeyEvent.VK_S ||
                keyCode == KeyEvent.VK_ENTER) {
            if (Model.score != 0)
            {
                //JPanel panel = new JPanel();
                //panel.add(new JLabel("The game is paused."));
                //Object[] btn = {"Continue"};
                Model.paused = true;
                int result = JOptionPane.showConfirmDialog(null, "Are you sure to restart the game?", "Notice", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    
                }
                else
                {
                    Model.paused = false;
                    return;
                }
            }
            else
            {
                model.reset();
            }
        }
        if(smodel != null){
            if (smodel.isRunning()){
                int player = smodel.getPlayer();

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        if(player == 2){
                            smodel.changeDirection2(Model.UP,2);
                        }
                        else{
                            smodel.changeDirection2(Model.UP,1);
                        }
                        
                        break;
                    case KeyEvent.VK_DOWN:
                        if(player == 2){
                            smodel.changeDirection2(Model.DOWN,2);
                        }
                        else{
                            smodel.changeDirection2(Model.DOWN,1);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(player == 2){
                            smodel.changeDirection2(Model.LEFT,2);
                        }
                        else{
                            smodel.changeDirection2(Model.LEFT,1);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(player == 2){
                            smodel.changeDirection2(Model.RIGHT,2);
                        }
                        else{
                            smodel.changeDirection2(Model.RIGHT,1);
                        }
                        break;
                    case KeyEvent.VK_ADD:
                    case KeyEvent.VK_PAGE_UP:
                        smodel.speedUp();
                        break;
                    case KeyEvent.VK_SUBTRACT:
                    case KeyEvent.VK_PAGE_DOWN:
                        smodel.speedDown();
                        break;
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_P:
                        smodel.changePauseState();
                        break;
                    default:
                }
            }
        }
       
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}
