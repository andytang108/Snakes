/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snakes;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author ribomo
 */

public class Server {
    int port;
    ServerSocket ss;
    ServerModel serverModel = new ServerModel(30,30);
    
    
    public Server(int port){
        this.port = port;
        
        try {
            ss = new ServerSocket(port);
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
    }
    
   
    
    public void close(){
        try{
            ss.close();
        }
        catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
    
    
}
