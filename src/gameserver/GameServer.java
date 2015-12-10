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

/**
 *
 * @author ribomo
 */
public class GameServer implements Runnable {

    Socket gameSocket;
    int playerNum;

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
            System.out.println("Haha");
            PrintWriter out = new PrintWriter(gameSocket.getOutputStream(),true);
            Scanner in = new Scanner(gameSocket.getInputStream());
            out.println("your player number is: "+ playerNum);
            for(int i = 0;i<100;i++){
                TimeUnit.SECONDS.sleep(1);
                out.println(Integer.toString(i));
                System.out.println(in.nextLine());
            }
            gameSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
