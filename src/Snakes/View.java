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
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {
    Control control = null;
    Model model = null;
    ServerModel smodel = null;
    String type = "model";
    
    JFrame mainFrame;
    Canvas paintCanvas;
    JLabel labelScore;

    public static final int canvasWidth = 300;
    public static final int canvasHeight = 300;

    public static final int nodeWidth = 10;
    public static final int nodeHeight = 10;

    public View(Model model, Control control) {
        this.model = model;
        this.control = control;

        mainFrame = new JFrame("Snake");

        Container cp = mainFrame.getContentPane();

        
        labelScore = new JLabel("Score:");
        cp.add(labelScore, BorderLayout.NORTH);

        
        paintCanvas = new Canvas();
        paintCanvas.setSize(canvasWidth + 1, canvasHeight + 1);
        paintCanvas.addKeyListener(control);
        cp.add(paintCanvas, BorderLayout.CENTER);

        
        JPanel panelButtom = new JPanel();
        panelButtom.setLayout(new BorderLayout());
        JLabel labelHelp;
        labelHelp = new JLabel("PageUp, PageDown for speed;", JLabel.CENTER);
        panelButtom.add(labelHelp, BorderLayout.NORTH);
        labelHelp = new JLabel("ENTER or R or S for start;", JLabel.CENTER);
        panelButtom.add(labelHelp, BorderLayout.CENTER);
        labelHelp = new JLabel("SPACE or P for pause", JLabel.CENTER);
        panelButtom.add(labelHelp, BorderLayout.SOUTH);
        cp.add(panelButtom, BorderLayout.SOUTH);

        mainFrame.addKeyListener(control);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    
    public View(ServerModel model, Control control) {
        this.smodel = model;
        this.control = control;
        this.type = "smodel";

        mainFrame = new JFrame("MultiSnake");

        Container cp = mainFrame.getContentPane();

        
        labelScore = new JLabel("Score:");
        cp.add(labelScore, BorderLayout.NORTH);

        
        paintCanvas = new Canvas();
        paintCanvas.setSize(canvasWidth + 1, canvasHeight + 1);
        paintCanvas.addKeyListener(control);
        cp.add(paintCanvas, BorderLayout.CENTER);

        
        JPanel panelButtom = new JPanel();
        panelButtom.setLayout(new BorderLayout());
        JLabel labelHelp;
        labelHelp = new JLabel("PageUp, PageDown for speed;", JLabel.CENTER);
        panelButtom.add(labelHelp, BorderLayout.NORTH);
        labelHelp = new JLabel("SPACE or P for pause", JLabel.CENTER);
        panelButtom.add(labelHelp, BorderLayout.SOUTH);
        cp.add(panelButtom, BorderLayout.SOUTH);

        mainFrame.addKeyListener(control);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    void repaint2() {
        Graphics g = paintCanvas.getGraphics();

        //draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        // draw the snake
        g.setColor(Color.ORANGE);
        LinkedList na = smodel.nodeArray;
        LinkedList na2 = smodel.getNodeArray2();
        Iterator it = na.iterator();
        Iterator it2 = na2.iterator();
        while (it.hasNext()) {
            Node n = (Node) it.next();
            drawNode(g, n);
        }
        g.setColor(Color.GREEN);
        while (it2.hasNext()) {
            Node n2 = (Node) it2.next();
            drawNode(g, n2);
        }

        // draw the food
        g.setColor(Color.RED);
        Node n = smodel.food;
        drawNode(g, n);

        updateScore2();
    }
    
    void repaint() {
        Graphics g = paintCanvas.getGraphics();

        //draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        // draw the snake
        g.setColor(Color.BLACK);
        LinkedList na = model.nodeArray;
        Iterator it = na.iterator();
        while (it.hasNext()) {
            Node n = (Node) it.next();
            drawNode(g, n);
        }

        // draw the food
        g.setColor(Color.RED);
        Node n = model.food;
        drawNode(g, n);

        updateScore();
    }

    private void drawNode(Graphics g, Node n) {
        g.fillRect(n.x * nodeWidth,
                n.y * nodeHeight,
                nodeWidth - 1,
                nodeHeight - 1);
    }

    public void updateScore() {
        String s = "Score: " + model.score;
        labelScore.setText(s);
    }
    public void updateScore2() {
        String s = "Score1: " + smodel.getScore1()+" Score2: "+smodel.getScore2();
        labelScore.setText(s);
    }

    public void update(Observable o, Object arg) {
        if(this.type.equals("model")){
            repaint();
        }
        else{
            repaint2();
        }
    }
}
