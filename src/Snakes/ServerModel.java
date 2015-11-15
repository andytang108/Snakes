/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snakes;

import java.util.*;

/**
 *
 * @author ribomo
 */
public class ServerModel {
    // UP and DOWN should be even
    // RIGHT and LEFT should be odd
    public static final int UP = 2;
    public static final int DOWN = 4;
    public static final int LEFT = 1;
    public static final int RIGHT = 3;
    
    SnakeModel player1;
    SnakeModel player2;
    
    boolean[][] matrix;
    Node food;
    int maxX;
    int maxY;
    boolean running = false;
    
    int timeInterval;
    double speedChangeRate = 0.6;        
    boolean paused;
    
    public ServerModel(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
        
        this.timeInterval = 200;
        this.paused = false;
        this.running = false;
        this.matrix = new boolean[maxX][];
        for (int i = 0; i < maxX; ++i) {
            this.matrix[i] = new boolean[maxY];
            Arrays.fill(this.matrix[i], false);
        }
        
        
    }
    
    public void paintSnake(int snakeNum){
        SnakeModel snake;
        if(snakeNum == 1){
            snake = this.player1;
        }
        else{
            snake = this.player2;
        }
        LinkedList <Node>snakeNode = snake.getNodeArray().;
        
        for(int i = 0;i < snakeNode.size(); i++){
            matrix[snakeNode.][snakeNode[i].y] = true;
        }
    }
    
    public void initSnakeNode(int  snakeNum){
        SnakeModel snake;
        if(snakeNum == 1){
            snake = this.player1;
        }
        else{
            snake = this.player2;
        }
        
        
        // 初始化蛇体，如果横向位置超过20个，长度为10，否则为横向位置的一半
        int initArrayLength = maxX > 20 ? 10 : maxX / 2;
        snake.getNodeArray().clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = maxX / 2 + i;
            int y = maxY / 2;
            
            snake.addLast(new Node(x, y));
            matrix[x][y] = true;
        }
    }
}