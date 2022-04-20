package com.math.client;

import java.awt.*;
import java.util.ArrayList;


public class DrawLog {
    private final int PREF_W;
    private final int PREF_H;
    private final int BORDER_GAP;
    private final double MAX_SCORE;

    public DrawLog(int pref_w, int pref_h, int border_gap, double max_score) {
        PREF_W = pref_w;
        PREF_H = pref_h;
        BORDER_GAP = border_gap;
        MAX_SCORE = max_score;
    }

    public void drawRect(Graphics2D gr, ArrayList<Double[]> map, Double start, Double finish, Double gap) {
        int counter = 0;
        double xScale = ((double) PREF_W - 2 * BORDER_GAP) / (map.size());
        double yScale = ((double) PREF_H - 2 * BORDER_GAP)/ MAX_SCORE;
        int x1, x2, x3, x4;
        int y1, y2, y3, y4;
        for (int i = 0; i < map.size(); i++) {
            Polygon p = new Polygon();
            Double[] coord = map.get(i);
            x1 = (int) (xScale * (counter) + BORDER_GAP);
            y1 = (int) ((MAX_SCORE - coord[0]) * yScale + BORDER_GAP);
            x2 = (int) (xScale * (counter++) + BORDER_GAP);
            y2 = (int) (PREF_H - BORDER_GAP);
            x3 = (int) (xScale * (counter) + BORDER_GAP);
            y3 = y2;
            x4 = (int) (xScale * (counter) + BORDER_GAP);
            y4 = y1;
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
        double xScale = ((double) PREF_W - 2 * BORDER_GAP) / (map.size());
        double yScale = ((double) PREF_H - 2 * BORDER_GAP)/ MAX_SCORE;
        int x1, x2, x3, x4;
        int y1, y2, y3, y4;
        for (int i = 0; i < map.size(); i++) {
            Polygon p = new Polygon();
            Double[] coord = map.get(i);
            x1 = (int) (xScale * (counter) + BORDER_GAP);
            y1 = (int) ((MAX_SCORE - coord[0]) * yScale + BORDER_GAP);
            x2 = (int) (xScale * (counter++) + BORDER_GAP);
            y2 = (int) (PREF_H - BORDER_GAP);
            x3 = (int) (xScale * (counter) + BORDER_GAP);
            y3 = y2;
            x4 = (int) (xScale * (counter) + BORDER_GAP);
            y4 = (int) ((MAX_SCORE - coord[1]) * yScale + BORDER_GAP);
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
        double xScale = ((double) PREF_W - 2 * BORDER_GAP) / (map.size()) / 2;
        double yScale = ((double) PREF_H - 2 * BORDER_GAP)/ MAX_SCORE;
        int x1, x2, x3, x4, x5;
        int y1, y2, y3, y4, y5;
        for (int i = 0; i < map.size(); i++) {
            Polygon p = new Polygon();
            Double[] coord = map.get(i);
            x1 = (int) (xScale * (counter) + BORDER_GAP);
            y1 = (int) ((MAX_SCORE - coord[0]) * yScale + BORDER_GAP);
            x2 = (int) (xScale * (counter) + BORDER_GAP);
            y2 = (int) (PREF_H - BORDER_GAP);
            counter += 2;
            x3 = (int) (xScale * (counter) + BORDER_GAP);
            y3 = y2;
            x4 = (int) (xScale * (counter) + BORDER_GAP);
            y4 = (int) ((MAX_SCORE - coord[2]) * yScale + BORDER_GAP);
            counter -= 1;
            x5 = (int) (xScale * (counter) + BORDER_GAP);
            y5 = (int) ((MAX_SCORE - coord[1]) * yScale + BORDER_GAP);
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
