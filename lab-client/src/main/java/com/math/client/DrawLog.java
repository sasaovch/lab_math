package com.math.client;

import java.awt.*;
import java.awt.geom.*;
import java.util.Map;
import java.util.TreeMap;

public class DrawLog {

    public DrawLog() {}

    public void drawLog(Graphics2D gr, TreeMap<Double, Double> map, Double start, Double finish, Double gap) {
        int counter = 0;
        double xScale = ((double) DrawGraph.PREF_W - 2 * DrawGraph.BORDER_GAP) / (map.size());
        double yScale = ((double) DrawGraph.PREF_H - 2 * DrawGraph.BORDER_GAP)/ DrawGraph.MAX_SCORE;
        for (Map.Entry<Double, Double> coord : map.entrySet()) {
            Rectangle2D.Double r = new Rectangle2D.Double(xScale * counter++ + DrawGraph.BORDER_GAP,
            (DrawGraph.MAX_SCORE - coord.getValue()) * yScale + DrawGraph.BORDER_GAP, xScale, 
            DrawGraph.PREF_H - (DrawGraph.MAX_SCORE - coord.getValue()) * yScale - 2 * DrawGraph.BORDER_GAP);
            gr.setColor(Color.WHITE);
            gr.fill(r);
       }
       counter = 0;
       for (Map.Entry<Double, Double> coord : map.entrySet()) {
        Line2D.Double r = new Line2D.Double(xScale * counter + DrawGraph.BORDER_GAP,
        (DrawGraph.MAX_SCORE - coord.getValue()) * yScale + DrawGraph.BORDER_GAP, 
        xScale * counter + DrawGraph.BORDER_GAP, DrawGraph.PREF_H - DrawGraph.BORDER_GAP);
        gr.setColor(Color.BLACK);
        gr.draw(r);
        counter += 1;
        Line2D.Double l = new Line2D.Double(xScale * counter + DrawGraph.BORDER_GAP,
        (DrawGraph.MAX_SCORE - coord.getValue()) * yScale + DrawGraph.BORDER_GAP, 
        xScale * counter + DrawGraph.BORDER_GAP, DrawGraph.PREF_H - DrawGraph.BORDER_GAP);
        gr.setColor(Color.BLACK);
        gr.draw(l);
       }
    }
}
