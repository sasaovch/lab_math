package com.math.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.function.Function;

public class App {

    private final static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final static PrintWriter out = new PrintWriter(System.out, true);
    private final static Function<Double, Double> funct = s -> Math.pow(s, 2);

    public static void main(String[] args) throws Exception {
        int start = write("Enter start point", Integer::parseInt);
        int finish = write("Enter finish point", Integer::parseInt);
        Double startPoint = (double) start;
        Double finishPoint = (double) finish;
        Integer numberSplitPoint = write("Enter number split points", Integer::parseInt);
        String wayforSplit = write("Enter way for split: left, rigth, center, random", s -> s);
        double length = finishPoint - startPoint;
        double gap = length / numberSplitPoint;
        double left = startPoint;
        double rigth = startPoint + gap;
        TreeMap<Double, Double> points = new TreeMap<>();
        double result = 0;
        double point;
        for (int i = 1; i <= numberSplitPoint; i++) {
            switch (wayforSplit) {
                case "left" :
                    point = funct.apply(left);
                    points.put(left, point);
                    left = rigth;
                    rigth += gap;
                    result += point * gap;
                    break;
                case "rigth" :
                    point = funct.apply(rigth);
                    points.put(left, point);
                    left = rigth;
                    rigth += gap;
                    result += point * gap;
                    break;
                case "center" :
                    point = funct.apply(((left + rigth)/ 2));
                    points.put(left, point);
                    left = rigth;
                    rigth += gap;
                    result += point * gap;
                    break;
                case "random" :
                    point = funct.apply(Math.random() * gap + left);
                    points.put(left, point);
                    left = rigth;
                    rigth += gap;
                    result += point * gap;
                    break;
            }
        }
        String[] mess = new String[]{"Integral = " + String.format("%.4f", result) , "Way split - " + wayforSplit,"Number split points = " + numberSplitPoint}; 
        out.println(result);
        DrawGraph.start(startPoint, finishPoint, points, gap, x -> Math.pow(x, 2), mess);
    }
    
    public static <T> T write(String message, Function<String, T> funct) throws IOException {
        while (true) {
            out.println(message);
            out.printf("%s", ">>>");
            String line = in.readLine().trim();
            T result;
            try {
                result = funct.apply(line);
            } catch (NumberFormatException e) {
                out.println("It isn't correct");
                continue;
            } 
            return result;
        }
    }
}
