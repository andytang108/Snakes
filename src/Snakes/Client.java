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
public class Client {
    String ip;
    int port;
    Socket s;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
        try{
            s = new Socket(ip, port);
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
    }

    public void sendText(String text) {
        try {
            OutputStream outStream = s.getOutputStream();
            PrintWriter out = new PrintWriter(outStream, true);
            out.println(text);
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
    }

    public String getText() {
        try {
            InputStream inStream = s.getInputStream();
            Scanner in = new Scanner(inStream);
            String line = in.nextLine();
            return line;
        }
        catch (Exception exc1) {
            exc1.printStackTrace();
        }
        return "";
    }
    
    public void close(){
        try{
            s.close();
        }
        catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
    
    public void sendObject(Object o){
        try{
            OutputStream outStream = s.getOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(outStream);
            objectStream.writeObject(o);
        }
        catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
    
    public Object receiveObject(){
        try{
            InputStream inStream = s.getInputStream();
            ObjectInputStream objectStream = new ObjectInputStream(inStream);
            return objectStream.readObject();
        }
        catch(Exception exc1){
            exc1.printStackTrace();
            return null;
        }
    }
}
