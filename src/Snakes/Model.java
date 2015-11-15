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
    boolean[][] matrix;                         // 指示位置上有没蛇体或食物
    LinkedList nodeArray = new LinkedList();    // 蛇体
    Node food;
    int maxX;
    int maxY;
    int direction = 2;                          // 蛇运行的方向
    boolean running = false;                    // 运行状态

    int timeInterval = 200;                     // 时间间隔，毫秒
    double speedChangeRate = 0.6;              // 每次得速度变化率
    boolean paused = false;                     // 暂停标志

    int score = 0;                              // 得分
    int countMove = 0;                          // 吃到食物前移动的次数

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
        timeInterval = 200;                     // 时间间隔，毫秒
        paused = false;                         // 暂停标志
        score = 0;                              // 得分
        countMove = 0;      
        running = true;// 吃到食物前移动的次数

        // initial matirx, 全部清0
        matrix = new boolean[maxX][];
        for (int i = 0; i < maxX; ++i) {
            matrix[i] = new boolean[maxY];
            Arrays.fill(matrix[i], false);
        }

        
        // 初始化蛇体，如果横向位置超过20个，长度为10，否则为横向位置的一半
        int initArrayLength = maxX > 20 ? 10 : maxX / 2;
        nodeArray.clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = maxX / 2 + i;
            int y = maxY / 2;
            
            nodeArray.addLast(new Node(x, y));
            matrix[x][y] = true;
        }

       
        food = createFood();
        matrix[food.x][food.y] = true;
        }

    public void changeDirection(int newDirection) {
        // 改变的方向不能与原来方向同向或反向
        if (direction % 2 != newDirection % 2) {
            direction = newDirection;
        }
    }

   
    public boolean moveOn() {
        Node n = (Node) nodeArray.getFirst();
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

        if ((0 <= x && x < maxX) && (0 <= y && y < maxY)) {
            if (matrix[x][y]) {        
                if (x == food.x && y == food.y) {       
                    nodeArray.addFirst(food);           

                    // 分数规则，与移动改变方向的次数和速度两个元素有关
                    
                    score += 10+ timeInterval/50;
                    countMove = 0;

                    food = createFood();               
                    matrix[food.x][food.y] = true;      
                    return true;
                } else                                  
                    return false;
               
            } else {                 
                nodeArray.addFirst(new Node(x, y));
                matrix[x][y] = true;
                n = (Node) nodeArray.removeLast();
                matrix[n.x][n.y] = false;
                countMove++;
                return true;
            }
        }
        return false; 
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(timeInterval);
            } catch (Exception e) {
                break;
            }

            if (!paused) {
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
        running = false;
    }

    private Node createFood() {
        int x = 0;
        int y = 0;
        do{
            Random r = new Random();
            x = r.nextInt(maxX);
            y = r.nextInt(maxY);
        } while (matrix[x][y]);

        return new Node(x, y);
    }

    public void speedUp() {
        timeInterval *= speedChangeRate;
    }

    public void speedDown() {
        timeInterval /= speedChangeRate;
    }

    public void changePauseState() {
        paused = !paused;
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
