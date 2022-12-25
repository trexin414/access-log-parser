package ru.сourses.geometry;

import ru.сourses.any.Fraction1;

import java.util.Objects;

public class Line1 implements Cloneable{
    int x = 3;
    Point1 start,end;
    public Line1(Point1 start, Point1 end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Line1 l = (Line1) obj;
        return this.start.equals(l.start) && this.end.equals(l.end);
    }

//    public boolean equals(Line1 l){
//        return this.start.x == l.start.x && this.start.y == l.start.y && this.end.x == l.end.x && this.end.y == l.end.y;
//    }

//    @Override
//    protected Line1 clone() throws CloneNotSupportedException {
//        return new Line1(this.start, this.end);
//    }

    @Override
    public Line1 clone() throws CloneNotSupportedException {
        Line1 line = (Line1)super.clone();
        line.start=start.clone();
        line.end=end.clone();
        return line;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Line1 l1 = new Line1(new Point1(1,1), new Point1(2,2));
        Line1 l3 = new Line1(new Point1(1,1), new Point1(2,2));
        Line1 l2 = l1.clone();

        System.out.println(l1.equals(l2));
    }
}
