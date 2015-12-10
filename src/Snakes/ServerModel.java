/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snakes;

import java.util.*;

/**
 *
 * @author ribomo,Jingyi Tang
 */
public class ServerModel {
    // UP and DOWN should be even
    // RIGHT and LEFT should be odd
    public static final int UP = 2;
    public static final int DOWN = 4;
    public static final int LEFT = 1;
    public static final int RIGHT = 3;
    
    private SnakeModel player1 = new SnakeModel();
    private SnakeModel player2 = new SnakeModel();
    
    private boolean[][] matrix;
    private Node food;
    private int maxX;
    private int maxY;
    private boolean running = false;
    
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
        this.matrix = this.player1.initSnakeModelA(this.maxX, this.maxY, this.matrix);
        this.matrix = this.player2.initSnakeModelB(this.maxX, this.maxY, this.matrix);
        
        this.food = createFood();
        this.matrix[food.x][food.y] = true;
        
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
        this.timeInterval *= this.speedChangeRate;
    }

    public void speedDown() {
        this.timeInterval /= this.speedChangeRate;
    }

    public void changePauseState() {
        this.paused = !this.paused;
    }

    /**
     * @return the player1
     */
    public SnakeModel getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(SnakeModel player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public SnakeModel getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(SnakeModel player2) {
        this.player2 = player2;
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
   
}