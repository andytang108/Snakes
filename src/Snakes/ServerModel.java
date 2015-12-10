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
    LinkedList nodeArray2 = new LinkedList(); // 蛇体1,2
    
    
    int direction1 = 2;
    int direction2 = 4;                             // 蛇运行的方向

   
    int score1 = 0;
    int score2 = 0;// 得分

    
    public ServerModel(int maxX, int maxY) {
        super(maxX,maxY);

        reset2();
    }

    private void reset2(){
        direction1 = Model.UP;
        direction2 = Model.DOWN;// 蛇运行的方向
        timeInterval = 200;                     // 时间间隔，毫秒
        paused = false;                         // 暂停标志
        score1 = 0;
        score2 = 0;// 得分   
        running = true;

        // initial matirx, 全部清0
        matrix = new boolean[maxX][];
        for (int i = 0; i < maxX; ++i) {
            matrix[i] = new boolean[maxY];
            Arrays.fill(matrix[i], false);
        }
        
        int initArrayLength = maxX > 20 ? 10 : maxX / 2;
        // 初始化蛇体，如果横向位置超过20个，长度为10，否则为横向位置的一半
        nodeArray.clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = maxX / 2 + i;
            int y = maxY / 2;
            
            nodeArray.addLast(new Node(x, y));
            matrix[x][y] = true;
        }
        nodeArray2.clear();
        for (int i = 1; i < initArrayLength+1; ++i) {
            int x2 = maxX / 2 - i;
            int y2 = maxY / 2;
            
            nodeArray2.addLast(new Node(x2, y2));
            matrix[x2][y2] = true;
        }
        

       
        food = createFood();
        matrix[food.x][food.y] = true;
        }

    public void changeDirection2(int newDirection, int which) {
        // 改变的方向不能与原来方向同向或反向
        if(which == 1){
            if (direction1 % 2 != newDirection % 2) {
                direction1 = newDirection;
            }
        }
        if(which == 2){
            if (direction2 % 2 != newDirection % 2) {
                direction2 = newDirection;
            }
        }
    }
    
    public boolean moveOn2(){
        Node n2 = (Node) nodeArray2.getFirst();//蛇2
        int x2 = n2.x;
        int y2 = n2.y;
        // 根据方向增减坐标值
        switch (direction2) {
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
        if ((0 <= x2 && x2 < maxX) && (0 <= y2 && y2 < maxY)) {
            if (matrix[x2][y2]) {        
                if (x2 == food.x && y2 == food.y) {       
                    nodeArray2.addFirst(food);           

                    score2 += 10+ timeInterval/50;
                    
                    food = createFood();               
                    matrix[food.x][food.y] = true;      
                    return true;
                } else                                  
                    return false;
               
            } else {                 
                nodeArray2.addFirst(new Node(x2, y2));
                matrix[x2][y2] = true;
                n2 = (Node) nodeArray2.removeLast();
                matrix[n2.x][n2.y] = false;
                return true;
            }
        }
        return false;
    }

   
    public boolean moveOn1() {
        Node n = (Node) nodeArray.getFirst();
        int x = n.x;
        int y = n.y;
        // 根据方向增减坐标值
        switch (direction1) {
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
                    
                    score1 += 5+ timeInterval/50;
                    
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
                if (moveOn2()&&moveOn1()) {
                    setChanged();           // Model通知View数据已经更新
                    notifyObservers();
                }else if(!moveOn1()) {
                    JOptionPane.showMessageDialog(null,
                            "Your final score is "+score1+". But you lost!!!",
                            "Get Rekt",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Your final score is "+score1+". You win!!!",
                            "Congrat!",
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
