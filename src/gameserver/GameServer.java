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
                    //If the other player haven't pass their model to server
                    if(temp == null){
                        temp = smObject;
                    }
                    else{
                        
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
        ServerModel result = new ServerModel(sm1.get)
    }
}
