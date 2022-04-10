package com.math.client;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawingCanvas extends JComponent {
    private int wigth;
    private int height;

    public DrawingCanvas(int w, int h) {
        wigth = w;
        height = h;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        gr.setRenderingHints(rh);

        Rectangle2D.Double r = new Rectangle2D.Double(0,0, wigth,height);
        gr.setColor(new Color(100,149,237));
        gr.fill(r); 

        Ellipse2D.Double e = new Ellipse2D.Double(200, 75, 100, 100);
        gr.setColor(Color.GREEN);
        gr.fill(e);

        Line2D.Double l = new Line2D.Double(100, 250, 300, 75);
        gr.setColor(Color.BLACK);
        gr.draw(l);
    }
}
