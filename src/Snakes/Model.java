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


class Model extends Observable implements Runnable {
    private boolean[][] matrix;                         // 指示位置上有没蛇体或食物
    private LinkedList nodeArray = new LinkedList();    // 蛇体
    private Node food;
    private int maxX;
    private int maxY;
    int direction = 2;                          // 蛇运行的方向
    private boolean running = false;                    // 运行状态

    private int timeInterval = 250;                     // 时间间隔，毫秒
    private double speedChangeRate = 0.6;              // 每次得速度变化率
    private boolean paused = false;                     // 暂停标志

    int score = 0;                              // 得分

    // UP and DOWN should be even
    // RIGHT and LEFT should be odd
    public static final int UP = 2;
    public static final int DOWN = 4;
    public static final int LEFT = 1;
    public static final int RIGHT = 3;

    public Model( int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        reset();
    }

    public void reset(){
        direction = Model.UP;              // 蛇运行的方向
        setTimeInterval(250);                     // 时间间隔，毫秒
        setPaused(false);                         // 暂停标志
        score = 0;                              // 得分   
        setRunning(true);// 吃到食物前移动的次数

        // initial matirx, 全部清0
        setMatrix(new boolean[getMaxX()][]);
        for (int i = 0; i < getMaxX(); ++i) {
            getMatrix()[i] = new boolean[getMaxY()];
            Arrays.fill(getMatrix()[i], false);
        }

        
        // 初始化蛇体，如果横向位置超过20个，长度为10，否则为横向位置的一半
        int initArrayLength = getMaxX() > 20 ? 10 : getMaxX() / 2;
        getNodeArray().clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = getMaxX() / 2 + i;
            int y = getMaxY() / 2;
            
            getNodeArray().addLast(new Node(x, y));
            getMatrix()[x][y] = true;
        }

       
        setFood(createFood());
        getMatrix()[getFood().x][getFood().y] = true;
        }

    public void changeDirection(int newDirection) {
        // 改变的方向不能与原来方向同向或反向
        if (direction % 2 != newDirection % 2) {
            direction = newDirection;
        }
    }

   
    public boolean moveOn() {
        Node n = (Node) getNodeArray().getFirst();
        int x = n.x;
        int y = n.y;

        // 根据方向增减坐标值
        switch (direction) {
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
                    
                    score += 10+ getTimeInterval()/50;
                    
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
                if (moveOn()) {
                    setChanged();           // Model通知View数据已经更新
                    notifyObservers();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Your final score is "+score+".",
                            "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
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
     * @return the matrix
     */
    public boolean[][] getMatrix() {
        return matrix;
    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * @return the nodeArray
     */
    public LinkedList getNodeArray() {
        return nodeArray;
    }

    /**
     * @param nodeArray the nodeArray to set
     */
    public void setNodeArray(LinkedList nodeArray) {
        this.nodeArray = nodeArray;
    }

    /**
     * @return the food
     */
    public Node getFood() {
        return food;
    }

    /**
     * @param food the food to set
     */
    public void setFood(Node food) {
        this.food = food;
    }

    /**
     * @return the maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    /**
     * @return the maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return the timeInterval
     */
    public int getTimeInterval() {
        return timeInterval;
    }

    /**
     * @param timeInterval the timeInterval to set
     */
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     * @return the speedChangeRate
     */
    public double getSpeedChangeRate() {
        return speedChangeRate;
    }

    /**
     * @param speedChangeRate the speedChangeRate to set
     */
    public void setSpeedChangeRate(double speedChangeRate) {
        this.speedChangeRate = speedChangeRate;
    }

    /**
     * @return the paused
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused the paused to set
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
