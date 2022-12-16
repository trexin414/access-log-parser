package ru.сourses.geometry;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2){
        this.start = new Point(x1,y1);
        this.end = new Point(x2,y2);
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start.setX(start.getX());
        this.start.setY(start.getY());
    }

    public void setEnd(Point end) {
        this.end.setX(end.getX());
        this.end.setY(end.getY());
    }

    public double lineSize(){
        return Math.sqrt(Math.pow(this.end.getX()-this.start.getX(), 2) + Math.pow(this.end.getY() - this.start.getY(), 2));
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}
