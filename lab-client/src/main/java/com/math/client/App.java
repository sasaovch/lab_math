package com.math.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.Function;

public class App {

    private final static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final static PrintWriter out = new PrintWriter(System.out, true);
    private final static Function<Double, Double> funct = s -> Math.pow(s, 2);

    public static void main(String[] args) throws Exception {
        Way way = write("Enter the integration method: rectangle, trapezoid, simpson", s -> Way.valueOf(s.toUpperCase()));
        int start = write("Enter start point", Integer::parseInt);
        int finish = write("Enter finish point", Integer::parseInt);
        double startPoint = (double) start;
        double finishPoint = (double) finish;
        Integer numberSplitPoint = write("Enter number split points", Integer::parseInt);
        double length = finishPoint - startPoint;
        double gap = length / numberSplitPoint;
        ArrayList<Double[]> points = new ArrayList<>();
        double result = 0;
        String[] mess = null;
        if (way == Way.RECTANGLE) {
            WayPoint wayforSplit = write("Enter way for split: left, rigth, center, random", s -> WayPoint.valueOf(s.toUpperCase()));
            Rectangle r = new Rectangle();
            result = r.main(start, finish, numberSplitPoint, wayforSplit, funct);
            points = r.getPoints();
            mess = new String[]{"Integral = " + String.format("%.10f", result) , "The integration method - " + Way.RECTANGLE.getName(), "Way split - " + wayforSplit, "Number split points = " + numberSplitPoint}; 
        } else if (way == Way.TRAPEZOID) {
            Trapezoid t = new Trapezoid();
            result = t.main(start, finish, numberSplitPoint, funct);
            points =t.getPoints();
            mess = new String[]{"Integral = " + String.format("%.10f", result) , "The integration method - " + Way.TRAPEZOID.getName(), "Number split points = " + numberSplitPoint}; 
        } else if (way == Way.SIMPSON) {
            Simpson s = new Simpson();
            result = s.main(start, finish, numberSplitPoint, funct);
            points =s.getPoints();
            mess = new String[]{"Integral = " + String.format("%.10f", result) , "The integration method - " + Way.SIMPSON.getName(), "Number split points = " + numberSplitPoint}; 
        }
        out.printf("Result = %.10f", result);
        DrawGraph gr = new DrawGraph(startPoint, finishPoint, gap, points, x -> Math.pow(x, 2), mess, way);
        gr.startDraw();
    }
    
    public static <T> T write(String message, Function<String, T> funct) throws IOException {
        while (true) {
            out.println(message);
            out.printf("%s", ">>>");
            String line = in.readLine().trim();
            T result;
            try {
                result = funct.apply(line);
            } catch (IllegalArgumentException e) {
                out.println("It isn't correct");
                continue;
            } 
            return result;
        }
    }
}
