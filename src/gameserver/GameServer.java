/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.*;
import Snakes.ServerModel;

/**
 *
 * @author ribomo
 */
public class GameServer implements Runnable {
    Socket gameSocket;
    int playerNum;
    ServerModel sm;
    ServerModel smP1;
    ServerModel smP2;

    GameServer(Socket s,int playerNum) {
        this.gameSocket = s;
        this.playerNum = playerNum;
    }

    public static void main(String args[]) throws Exception {
        ServerSocket ssock = new ServerSocket(9999);
        System.out.println("Listening");
        int playerNumber = 0;
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("Connected");
            playerNumber++;
            new Thread(new GameServer(sock, playerNumber)).start();
        }
    }
    
    public void run() {
        try {
            ObjectInputStream objIn = new ObjectInputStream(gameSocket.getInputStream());
            ObjectOutputStream objOut = new ObjectOutputStream(gameSocket.getOutputStream());
            Boolean isContinue = true;
            while(isContinue){
                Object obj = objIn.readObject();
                if(obj instanceof ServerModel){
                    ServerModel smObject = (ServerModel) obj;
                    if(playerNum == 1){
                        smP1 = smObject;
                    }
                    else if(playerNum == 2){
                        smP2 = smObject;
                    }
                    if(smP1 != null && smP2 != null){
                        sm = mergeServerModel(smP1,smP2);
                        smP1 = null;
                        smP2 = null;
                        objOut.writeObject(sm);
                    }
                }
            }
            gameSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public ServerModel mergeServerModel(ServerModel sm1, ServerModel sm2){
        ServerModel result = new ServerModel(sm1.getMaxX(),sm1.getMaxY());
        result.setDirection1(sm1.getDirection1());
        result.setDirection2(sm2.getDirection2());
        result.setFood(sm1.getFood());
        result.setMatrix(sm1.getMatrix());
        result.setNodeArray(sm1.getNodeArray());
        result.setNodeArray2(sm2.getNodeArray2());
        result.setRunning(sm1.isRunning());
        result.setScore1(sm1.getScore1());
        result.setScore2(sm1.getScore2());
        result.setSpeedChangeRate(sm1.getSpeedChangeRate());
        result.setTimeInterval(sm1.getTimeInterval());
        
        return result;
    }
}
