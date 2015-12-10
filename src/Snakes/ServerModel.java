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
import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;



public class ServerModel extends Model{
    private LinkedList nodeArray2 = new LinkedList(); // 蛇体1,2
    
    
    private int direction1 = 2;
    private int direction2 = 4;                             // 蛇运行的方向

   
    private int score1 = 0;
    private int score2 = 0;// 得分
    
    private int player;
    
    
    public ServerModel(int maxX, int maxY) {
        super(maxX,maxY);

        reset2();
    }

    private void reset2(){
        setDirection1(Model.UP);
        setDirection2(Model.DOWN);// 蛇运行的方向
        setTimeInterval(200);                     // 时间间隔，毫秒
        setPaused(false);                         // 暂停标志
        setScore1(0);
        setScore2(0);// 得分   
<<<<<<< HEAD
        setRunning(true);
        this.setPlayer(1);
=======
        running = true;
        this.setPlayer(2);
>>>>>>> master

        // initial matirx, 全部清0
        setMatrix(new boolean[getMaxX()][]);
        for (int i = 0; i < getMaxX(); ++i) {
            getMatrix()[i] = new boolean[getMaxY()];
            Arrays.fill(getMatrix()[i], false);
        }
        
        int initArrayLength = getMaxX() > 20 ? 10 : getMaxX() / 2;
        // 初始化蛇体，如果横向位置超过20个，长度为10，否则为横向位置的一半
        getNodeArray().clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = getMaxX() / 2 + i;
            int y = getMaxY() / 2;
            
            getNodeArray().addLast(new Node(x, y));
            getMatrix()[x][y] = true;
        }
        getNodeArray2().clear();
        for (int i = 1; i < initArrayLength+1; ++i) {
            int x2 = getMaxX() / 2 - i;
            int y2 = getMaxY() / 2;
            
            getNodeArray2().addLast(new Node(x2, y2));
            getMatrix()[x2][y2] = true;
        }
        

       
        setFood(createFood());
        getMatrix()[getFood().x][getFood().y] = true;
        }

    public void changeDirection2(int newDirection, int which) {
        // 改变的方向不能与原来方向同向或反向
        if(which == 1){
            if (getDirection1() % 2 != newDirection % 2) {
                setDirection1(newDirection);
            }
        }
        if(which == 2){
            if (getDirection2() % 2 != newDirection % 2) {
                setDirection2(newDirection);
            }
        }
    }
    
    public boolean moveOn2(){
        Node n2 = (Node) getNodeArray2().getFirst();//蛇2
        int x2 = n2.x;
        int y2 = n2.y;
        // 根据方向增减坐标值
        switch (getDirection2()) {
            case UP:
                y2--;
                break;
            case DOWN:
                y2++;
                break;
            case LEFT:
                x2--;
                break;
            case RIGHT:
                x2++;
                break;
        }
        if ((0 <= x2 && x2 < getMaxX()) && (0 <= y2 && y2 < getMaxY())) {
            if (getMatrix()[x2][y2]) {        
                if (x2 == getFood().x && y2 == getFood().y) {       
                    getNodeArray2().addFirst(getFood());           

                    setScore2(getScore2() + 10+ getTimeInterval()/50);
                    
                    setFood(createFood());               
                    getMatrix()[getFood().x][getFood().y] = true;      
                    return true;
                } else                                  
                    return false;
               
            } else {                 
                getNodeArray2().addFirst(new Node(x2, y2));
                getMatrix()[x2][y2] = true;
                n2 = (Node) getNodeArray2().removeLast();
                getMatrix()[n2.x][n2.y] = false;
                return true;
            }
        }
        return false;
    }

   
    public boolean moveOn1() {
        Node n = (Node) getNodeArray().getFirst();
        int x = n.x;
        int y = n.y;
        // 根据方向增减坐标值
        switch (getDirection1()) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        

        if ((0 <= x && x < getMaxX()) && (0 <= y && y < getMaxY())) {
            if (getMatrix()[x][y]) {        
                if (x == getFood().x && y == getFood().y) {       
                    getNodeArray().addFirst(getFood());           

                    // 分数规则，与移动改变方向的次数和速度两个元素有关
                    
                    setScore1(getScore1() + 5+ getTimeInterval()/50);
                    
                    setFood(createFood());               
                    getMatrix()[getFood().x][getFood().y] = true;      
                    return true;
                } else                                  
                    return false;
               
            } else {                 
                getNodeArray().addFirst(new Node(x, y));
                getMatrix()[x][y] = true;
                n = (Node) getNodeArray().removeLast();
                getMatrix()[n.x][n.y] = false;
                return true;
            }
        }
        
        return false; 
    }

    @Override
    public void run() {
        setRunning(true);
        while (isRunning()) {
            try {
                Thread.sleep(getTimeInterval());
            } catch (Exception e) {
                break;
            }

            if (!isPaused()) {
                if (moveOn2()&&moveOn1()) {
                    setChanged();           // Model通知View数据已经更新
                    notifyObservers();
                }else if(!moveOn1()) {
                    if(this.getPlayer()==1){
                        JOptionPane.showMessageDialog(null,
                                "Your final score is "+(getScore1()-150)+". You lost!!!",
                                "Get Rekt",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null,
                            "Your final score is "+getScore2()+". You win!!!",
                            "Congrat!",
                            JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                   
                }else{
                    if(this.getPlayer()==1){
                        JOptionPane.showMessageDialog(null,
                            "Your final score is "+getScore1()+". You win!!!",
                            "Congrat!",
                            JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Your final score is "+(getScore2()-150)+". You lost!!!",
                                "Get Rekt",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    
                }
            }
        }
        setRunning(false);
    }

    private Node createFood() {
        int x = 0;
        int y = 0;
        do{
            Random r = new Random();
            x = r.nextInt(getMaxX());
            y = r.nextInt(getMaxY());
        } while (getMatrix()[x][y]);

        return new Node(x, y);
    }

    public void speedUp() {
        setTimeInterval((int) (getTimeInterval() * getSpeedChangeRate()));
    }

    public void speedDown() {
        setTimeInterval((int) (getTimeInterval() / getSpeedChangeRate()));
    }

    public void changePauseState() {
        setPaused(!isPaused());
    }

    /**
     * @return the player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * @return the nodeArray2
     */
    public LinkedList getNodeArray2() {
        return nodeArray2;
    }

    /**
     * @param nodeArray2 the nodeArray2 to set
     */
    public void setNodeArray2(LinkedList nodeArray2) {
        this.nodeArray2 = nodeArray2;
    }

    /**
     * @return the direction1
     */
    public int getDirection1() {
        return direction1;
    }

    /**
     * @param direction1 the direction1 to set
     */
    public void setDirection1(int direction1) {
        this.direction1 = direction1;
    }

    /**
     * @return the direction2
     */
    public int getDirection2() {
        return direction2;
    }

    /**
     * @param direction2 the direction2 to set
     */
    public void setDirection2(int direction2) {
        this.direction2 = direction2;
    }

    /**
     * @return the score1
     */
    public int getScore1() {
        return score1;
    }

    /**
     * @param score1 the score1 to set
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     * @return the score2
     */
    public int getScore2() {
        return score2;
    }

    /**
     * @param score2 the score2 to set
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
