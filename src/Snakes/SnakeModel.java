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
public class SnakeModel {
    private LinkedList<Node> nodeArray;  
    private int direction;
    private int score;
    
    public SnakeModel(LinkedList nodeArray, int direction, int score){
        this.nodeArray = nodeArray;
        this.direction = direction;
        this.score = score;
    }
    
    public SnakeModel(){
        this.nodeArray = new LinkedList();
        this.direction = 2;
        this.score = 0;
    }
    
    public SnakeModel(LinkedList nodeArray){
        this.nodeArray = nodeArray;
        this.direction = 2;
        this.score = 0;
    }
    
    public SnakeModel(int maxX, int maxY){
        int initArrayLength = maxX > 20 ? 10 : maxX / 2;
        nodeArray.clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = maxX / 2 + i;
            int y = maxY / 2;
            
            nodeArray.addLast(new Node(x, y));
        }
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
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
