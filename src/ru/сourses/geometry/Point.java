package ru.сourses.geometry;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static boolean toEqual(Point a, Point b){
        return ((a.x == b.x) && (a.y == b.y));
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + '}';
    }
}
