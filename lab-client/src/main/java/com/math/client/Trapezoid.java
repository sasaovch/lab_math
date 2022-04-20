package com.math.client;

import java.util.ArrayList;
import java.util.function.Function;

public class Trapezoid {

    private ArrayList<Double[]> points;

    public double main(int start, int finish, Integer numberSplitPoint, Function<Double, Double> funct) {
        double length = finish - start;
        double gap = length / numberSplitPoint;
        double left = start;
        double rigth = start + gap;
        points = new ArrayList<>();
        double result = 0;
        double pointLeft, pointRight;
        pointLeft = funct.apply(left);
        for (int i = 1; i <= numberSplitPoint; i++) {
            pointRight = funct.apply(rigth);
            points.add(new Double[]{pointLeft, pointRight});
            result += (pointLeft + pointRight) / 2 * gap;

            left = rigth;
            pointLeft = pointRight;
            rigth += gap;
        }
        return result;
    }

    public ArrayList<Double[]> getPoints() {
        return points;
    }
}
