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
import java.util.TreeMap;
import java.util.function.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;


public class DrawGraph extends JPanel {
   public static Double MAX_SCORE;
   public static final int PREF_W = 1024;
   public static final int PREF_H = 720;
   public static final int BORDER_GAP = 30;
   public static final Color GRAPH_COLOR = Color.green;
   public static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   public static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   public static final int GRAPH_POINT_WIDTH = 1;
   public static final int Y_HATCH_CNT = 1;
   public static final int RESOLUTION = 10000;
   public List<Double> scores;
   public Double start, finish, gap;
   public DrawLog log;
   public TreeMap<Double, Double> map;
   public String[] mess;

   public DrawGraph(List<Double> scores2, Double start, Double finish, Double gap, TreeMap<Double, Double> map, String[] mess) {
      this.scores = scores2;
      this.start = start;
      this.finish = finish;
      this.gap = gap;
      log = new DrawLog();
      this.map = map;
      this.mess = mess;
   }
   
   @Override
   protected void paintComponent(Graphics g) {

      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   
      log.drawLog(g2, map, start, finish, gap);
   
      double xScale = ((double) PREF_W - 2 * BORDER_GAP) / (RESOLUTION);
      double yScale = ((double) PREF_H - 2 * BORDER_GAP) / MAX_SCORE;

      List<Point> graphPoints = new ArrayList<Point>();

      for (int i = 0; i < RESOLUTION; i++) {
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
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
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

   public static void createAndShowGui(Double start, Double finish, TreeMap<Double, Double> map, Double gap, Function<Double,Double> func, String[] mess) {
      List<Double> scores = new ArrayList<>();
      for (int i = 0; i < RESOLUTION ; i++) {
         scores.add(func.apply(start + i * (finish - start) / RESOLUTION));
      }
      DrawGraph mainPanel = new DrawGraph(scores, start, finish, gap, map, mess);
      MAX_SCORE = func.apply(finish);

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void start(Double start, Double finish, TreeMap<Double, Double> map, Double gap, Function<Double, Double> func, String[] mess) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(start, finish, map, gap, func, mess);
         }
      });
   }
}