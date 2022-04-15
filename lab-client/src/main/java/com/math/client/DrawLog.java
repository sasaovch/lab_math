package com.math.client;

import java.awt.*;
import java.util.ArrayList;


public class DrawLog {

    public DrawLog() {}

    public void drawRect(Graphics2D gr, ArrayList<Double[]> map, Double start, Double finish, Double gap) {
        int counter = 0;
        double xScale = ((double) DrawGraph.PREF_W - 2 * DrawGraph.BORDER_GAP) / (map.size());
        double yScale = ((double) DrawGraph.PREF_H - 2 * DrawGraph.BORDER_GAP)/ DrawGraph.MAX_SCORE;
        for (int i = 0; i < map.size(); i++) {
            Polygon p = new Polygon();
            Double[] coord = map.get(i);
            int x1 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y1 = (int) ((DrawGraph.MAX_SCORE - coord[0]) * yScale + DrawGraph.BORDER_GAP);
            int x2 = (int) (xScale * (counter++) + DrawGraph.BORDER_GAP);
            int y2 = (int) (DrawGraph.PREF_H - DrawGraph.BORDER_GAP);
            int x3 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y3 = y2;
            int x4 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y4 = y1;
            p.addPoint(x1, y1);
            p.addPoint(x2, y2);
            p.addPoint(x3, y3);
            p.addPoint(x4, y4);
            gr.setColor(Color.BLUE);
            gr.fillPolygon(p);
            }
        }

    public void drawTrap(Graphics2D gr, ArrayList<Double[]> map, Double start, Double finish, Double gap) {
        int counter = 0;
        double xScale = ((double) DrawGraph.PREF_W - 2 * DrawGraph.BORDER_GAP) / (map.size());
        double yScale = ((double) DrawGraph.PREF_H - 2 * DrawGraph.BORDER_GAP)/ DrawGraph.MAX_SCORE;
        for (int i = 0; i < map.size(); i++) {
            Polygon p = new Polygon();
            Double[] coord = map.get(i);
            int x1 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y1 = (int) ((DrawGraph.MAX_SCORE - coord[0]) * yScale + DrawGraph.BORDER_GAP);
            int x2 = (int) (xScale * (counter++) + DrawGraph.BORDER_GAP);
            int y2 = (int) (DrawGraph.PREF_H - DrawGraph.BORDER_GAP);
            int x3 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y3 = y2;
            int x4 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y4 = (int) ((DrawGraph.MAX_SCORE - coord[1]) * yScale + DrawGraph.BORDER_GAP);
            p.addPoint(x1, y1);
            p.addPoint(x2, y2);
            p.addPoint(x3, y3);
            p.addPoint(x4, y4);
            gr.setColor(Color.BLUE);
            gr.fillPolygon(p);
        }
    }

    
    public void drawSimp(Graphics2D gr, ArrayList<Double[]> map, Double start, Double finish, Double gap) {
        int counter = 0;
        double xScale = ((double) DrawGraph.PREF_W - 2 * DrawGraph.BORDER_GAP) / (map.size()) / 2;
        double yScale = ((double) DrawGraph.PREF_H - 2 * DrawGraph.BORDER_GAP)/ DrawGraph.MAX_SCORE;
        for (int i = 0; i < map.size(); i++) {
            Polygon p = new Polygon();
            Double[] coord = map.get(i);
            int x1 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y1 = (int) ((DrawGraph.MAX_SCORE - coord[0]) * yScale + DrawGraph.BORDER_GAP);
            int x2 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y2 = (int) (DrawGraph.PREF_H - DrawGraph.BORDER_GAP);
            counter += 2;
            int x3 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y3 = y2;
            int x4 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y4 = (int) ((DrawGraph.MAX_SCORE - coord[2]) * yScale + DrawGraph.BORDER_GAP);
            counter -= 1;
            int x5 = (int) (xScale * (counter) + DrawGraph.BORDER_GAP);
            int y5 = (int) ((DrawGraph.MAX_SCORE - coord[1]) * yScale + DrawGraph.BORDER_GAP);
            counter += 1;
            p.addPoint(x1, y1);
            p.addPoint(x2, y2);
            p.addPoint(x3, y3);
            p.addPoint(x4, y4);
            p.addPoint(x5, y5);
            gr.setColor(Color.BLUE);
            gr.fillPolygon(p);
        }
    }
}
