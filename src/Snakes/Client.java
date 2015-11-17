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
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    int port;
    Socket s;
    String ip;
    ServerModel client1Model;
    ServerModel client2Model;
    
    public Client(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        s = new Socket(ip, port);
    }
    
    public void sendModel(ServerModel serverModel){
        try{
            OutputStream outStream = s.getOutputStream();
            PrintWriter out = new PrintWriter(outStream, true);
           
            
            
            
        }
        catch(IOException ioexc)
        {
            ioexc.printStackTrace();
        }
    }
    public void receiveModel(){
        try{
            InputStream inputStream = s.getInputStream();
            Scanner in = new Scanner(inputStream);
            
               
            
            
        }
        catch(IOException ioexc)
        {
            ioexc.printStackTrace();
        }
        
    }
    public void close(){
        try{
            s.close();
        }
        catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
}
