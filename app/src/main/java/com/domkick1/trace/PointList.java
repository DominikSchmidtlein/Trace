package com.domkick1.trace;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by domin_2o9sb4z on 2016-02-17.
 */
public class PointList extends ArrayList<Point> {

    public PointList(){
        super();
    }

    public  PointList(int size){
        super(size);
    }

    public PointList(@NonNull Line line){
        super();
        add(line.getP1());
        add(line.getP2());
    }

    public PointList(float[] points){
        super(points.length / 2);
        for (int i = 0; i < points.length; i += 2)
            add(new Point(points[i], points[i + 1]));
    }

    public float[] toFloatArray(){
        float[] floats = new float[size() * 2];
        int i = 0;
        for (Point point : this) {
            floats[i] = point.getX();
            floats[i + 1] = point.getY();
            i += 2;
        }
        return floats;
    }

    /**
     * Checks if point is within "radius" of a point in points. Returns the point from points if
     * true, otherwise returns null.
     * @param touchPoint the point which may or may not be near a point from points
     * @param radius the maximum distance between point and a point in points, for them to be "near"
     * @return a point from points, if it is close, otherwise null
     */
    public Point isNearVertex(Point touchPoint, int radius){
        for(Point nearPoint: this)
            if(touchPoint.getDistance(nearPoint) < radius)
                return nearPoint;
        return null;
    }

    /**
     * Sort these points according to their distance to point. Sorting from smallest to biggest
     * distance.
     * @param point
     */
    public void sortDistanceToPoint(Point point) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < size(); i++) {
                if (point.getDistance(get(i - 1)) > point.getDistance(get(i))) {
                    swap(i - 1, i);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    /**
     * Swap the points are the 2 given indices.
     * @param index1 first index
     * @param index2 second index
     */
    private void swap(int index1, int index2) {
        Point temp = get(index1);
        set(index1, get(index2));
        set(index2, temp);
    }

    /**
     * Returns a list of points that sit on the given line.
     * @param line a line
     * @return a list of points that sit on the line
     */
    public PointList getPointsOnLine(Line line) {
        PointList intersectingPoints = new PointList();
        for (Point point : this)
            if (line.intersects(point))
                intersectingPoints.add(point);
        return intersectingPoints;
    }

}
