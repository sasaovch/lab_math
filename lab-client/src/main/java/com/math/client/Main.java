package com.math.client;

import java.awt.Color;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Main {
    public static final double WIDTH = 700;
    public static final double HEIGHT = 700;
    public static void main(String[] args) {
        new GraphFrame().setVisible(true);
    }
}
class GraphFrame extends JFrame {
    public GraphFrame() {
        super();
        setBounds(200, 200, (int) Main.WIDTH, (int) Main.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new GraphPanel());
    }
}
class GraphPanel extends JPanel {
    private static final double SCALE_X = 10;
    private static final double SCALE_Y = 15;
    public GraphPanel() {
        super();
    }
     @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2);
        drawGraph(g2);
        drawAxis(g2);
    }
    private void drawGraph(Graphics2D g2) {
        double lastX = 0;
        double lastY1 = Main.HEIGHT / 2;
        double lastY2 = Main.HEIGHT / 2;
 
        for (int x = 1; x <= Main.WIDTH; x++) {
            double y1 = Main.HEIGHT / 2 - func1(x / SCALE_X) * SCALE_Y;
            double y2 = Main.HEIGHT / 2 - func2(x / SCALE_X) * SCALE_Y;
 
            g2.drawLine((int)lastX, (int)lastY1, (int)x, (int)y1);
            g2.drawLine((int)lastX, (int)lastY2, (int)x, (int)y2);
 
            lastX = x;
            lastY1 = y1;
            lastY2 = y2;
          
        }
    }
    private double func1(double x) {
        return  (x);
    }
    private double func2(double x) {
        return x*x;
    }
    //рисование сетки
     private void drawGrid(Graphics2D g2) {
        int dx, dy;
        dx = dy = 20;
        Color c = g2.getColor(); // save color
        final int W = getWidth();
        final int H = getHeight();
        g2.setColor(Color.RED);
        for (int x = 0; x < W; x += dx) {
            g2.drawLine(x, 0, x, H);
        }
        for (int y = 0; y < H; y += dy) {
            g2.drawLine(0, y, W, y);
        }
        g2.setColor(c); // restore color
    }
     //Рисование осей координат
     private void drawAxis(Graphics2D g2) {
        Color c = g2.getColor(); // save color
        final int W = getWidth();
        final int H = getHeight();
        g2.setColor(Color.BLUE);
        g2.drawLine(0, 0, 0, H);
        g2.drawLine(0, 0, 3, 5);
        g2.drawLine(0, 0, -3, 5);
        int y = H/2 ;
        g2.drawLine(0, y, W, y);
        g2.drawLine(W, y, W - 5, y - 3);
        g2.drawLine(W, y, W - 5, y + 3);
        g2.setColor(c); // restore color
    }
}