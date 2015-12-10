/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jingyi_Tang
 */
package Snakes;


public class Snakes {
   public static void main() {
       Model model = new Model(30,30);
       Control control = new Control(model);
       View view = new View(model,control);
       //添加一个观察者，让view成为model的观察者
       model.addObserver(view);
      
       (new Thread(model)).start();
   }
   public static void main2(){
       ServerModel model = new ServerModel(30,30);
       Control control = new Control(model);
       View view = new View(model,control);
       
       model.addObserver(view);
       (new Thread(model)).start();
   }
}
