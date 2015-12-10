/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {  
    int port;
    ServerSocket ss;
    Socket incoming;
    InputStream inStream;
    Scanner in;
    OutputStream outStream;
    PrintWriter out;
    
    //Init server
    public Server(int port){
        this.port = port;
        
        try {
            ss = new ServerSocket(port);
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
    }
    
    public void waitForConnection(){
        try {
            incoming = ss.accept();
            inStream = incoming.getInputStream();
            outStream = incoming.getOutputStream();
            in = new Scanner(inStream);
            out = new PrintWriter(outStream, true);
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
    }
    
    //Echo input from server to client
    public void echoInput(){
        try{
            InputStream inStream = incoming.getInputStream(); // the INPUT stream handler
            OutputStream outStream = incoming.getOutputStream();
            Scanner in = new Scanner(inStream); //setup of input
            PrintWriter out = new PrintWriter(outStream, true);
            String lineIn = in.nextLine();
            out.println(lineIn);
            System.out.println(lineIn.trim());
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
    }
    
    //Get input string from server
    public String getInput(){
        try{
            String lineIn = in.nextLine().trim();
            return lineIn;
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
        return "";
    }
    
    //Sent string to servers
    public void sentData(String text){
        try{
            out.println(text);
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }       
    }
    
    //Close server
    public void close(){
        try{
            ss.close();
        }
        catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
    
    public Object receiveObject(){
        try{
            ObjectInputStream objectStream = new ObjectInputStream(inStream);
            return objectStream.readObject();
        }
        catch(Exception exc1){
            exc1.printStackTrace();
            return null;
        }
    }
    
    public void sendObject(Object o){
        try{
            ObjectOutputStream objectStream = new ObjectOutputStream(outStream);
            objectStream.writeObject(o);
        }
        catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
}
