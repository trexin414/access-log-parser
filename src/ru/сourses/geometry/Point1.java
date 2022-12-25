package ru.сourses.geometry;

import ru.сourses.any.Fraction1;

import java.util.Objects;

public class Point1 implements Cloneable{
    int x,y;

    public Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Point1 p = (Point1) obj;
        return Objects.equals(this.x, p.x) && Objects.equals(this.y, p.y);
    }

    public boolean equals(Point1 p){
        return this.x == p.x && this.y == p.y;
    }

    @Override
    protected Point1 clone() throws CloneNotSupportedException {
        return (Point1) super.clone();
//        return new Point1(this.x, this.y);
    }
}
