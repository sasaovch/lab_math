package com.math.client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;


public class DrawGraph extends JPanel {
   private final int PREF_W = 1024;
   private final int PREF_H = 720;
   private final int BORDER_GAP = 30;
   private final Color GRAPH_COLOR = Color.green;
   private final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private final int GRAPH_POINT_WIDTH = 1;
   private final int Y_HATCH_CNT = 1;
   private final int RESOLUTION = 100;
   private Double MAX_SCORE;
   private Way way;
   private Double start, finish, gap;
   private ArrayList<Double[]> map;
   private String[] mess;
   private Function<Double, Double> funct;
   private List<Double> scores;

   public DrawGraph(Double start, Double finish, double gap, ArrayList<Double[]> map, Function<Double, Double> funct, String[] mess, Way way) {
      this.start = start;
      this.finish = finish;
      this.gap = gap;
      this.map = map;
      this.mess = mess;
      this.way = way;
      this.funct = funct;
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      DrawLog log = new DrawLog(PREF_W, PREF_H, BORDER_GAP, MAX_SCORE);
      if (way == Way.RECTANGLE) {
         log.drawRect(g2, map, start, finish, gap);
      } else if (way == Way.TRAPEZOID) {
         log.drawTrap(g2, map, start, finish, gap);
      } else {
         log.drawSimp(g2, map, start, finish, gap);
      }
      
      g2.setColor(Color.GRAY);
      double xScale = ((double) PREF_W - 2 * BORDER_GAP) / (RESOLUTION);
      double yScale = ((double) PREF_H - 2 * BORDER_GAP) / MAX_SCORE;
      
      List<Point> graphPoints = new ArrayList<Point>();

      for (int i = 0; i <= RESOLUTION; i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

      // create hatch marks for y axis. 
      for (int i = 0; i < Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = 0; i < scores.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }
      
      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }

      FontRenderContext frc = g2.getFontRenderContext(); 
      Font f = new Font("Arial Bold", Font.ITALIC, 30); 
      AffineTransform at = new AffineTransform(); 
      int count = 1;
      g2.setColor(Color.RED);
      g2.setBackground(Color.RED);
      g2.setFont(f);
      for (String me : mess) {
         TextLayout tl = new TextLayout(me, f, frc);
         at.setToTranslation(PREF_W/2-(tl.getBounds().getMaxX())/2, 50 * count++);
         Shape sh = tl.getOutline(at); 
         g2.draw(sh); 
      }
   }
   
   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   public void startDraw() {
      scores = new ArrayList<>();
      for (int i = 0; i <= RESOLUTION ; i++) {
         scores.add(funct.apply(start + i * (finish - start) / RESOLUTION));
      }

      MAX_SCORE = funct.apply(finish);

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(this);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   // public static void start(Double start, Double finish, ArrayList<Double[]> map, Double gap, Function<Double, Double> func, String[] mess, Way way) {
   //    SwingUtilities.invokeLater(new Runnable() {
   //       public void run() {
   //          createAndShowGui(start, finish, map, gap, func, mess, way);
   //       }
   //    });
   // }
}