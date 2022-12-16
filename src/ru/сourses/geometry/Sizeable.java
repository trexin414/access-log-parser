package ru.сourses.geometry;

class Point2 {
    int x,y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class PolyLine implements Sizeable{
    Point2[] points;

    public PolyLine(Point2... points) {
        this.points = points;
    }

    public double length(){
        double sum=0;

        for(int i = 1; i <= this.points.length-1; i++){
            sum += Math.sqrt(Math.pow(points[i-1].x-points[i].x, 2) + Math.pow(points[i-1].y-points[i].y, 2));
        }
        return sum;
    }
}

class ClosePolyLine extends PolyLine implements Sizeable{
    public ClosePolyLine(Point2... points) {
        super(points);
    }
    @Override
    public double length(){
        double size = 0;

        for(int i = 1; i <= this.points.length-1; i++){
            size += Math.sqrt(Math.pow(points[i-1].x-points[i].x, 2) + Math.pow(points[i-1].y-points[i].y, 2));
        }
        size += Math.sqrt(Math.pow(points[points.length - 1].x-points[0].x, 2) + Math.pow(points[points.length - 1].y-points[0].y, 2));
        return size;
    }
}

class TestString implements Sizeable{
    String str;

    public TestString(String str){
        this.str = str;
    }

    public double length(){
        return str.length();
    }
}

public interface Sizeable{
    double length();
}
