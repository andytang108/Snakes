/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import Snakes.Client;
/**
 *
 * @author ribomo
 */
public class test {
    public static void main(String args[]){
        Client c = new Client("localhost",9999);
        while(true){
            String text = c.getText();
            System.out.println(text);
            c.sendText("I got the number: "+text);
        }
    }
}
