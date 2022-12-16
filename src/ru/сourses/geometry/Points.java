package ru.сourses.geometry;

import java.util.Date;

public class Points{
    int x;
    int y;
    int z;

    public Points(int x){
        this.x = x;
    }

    public Points(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Points(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class PointTimeColor extends Points {
    Date date;
    String color;

    public PointTimeColor(){
        this(0,0,0, null, null);
    }

    public PointTimeColor(Date date){
        this(0,0,0, date, null);
    }

    public PointTimeColor(String color){
        this(0,0,0,null, color);
    }

    public PointTimeColor(int x, Date date){
        this(x,0,0, date, null);
    }

    public PointTimeColor(int x, String color){
        this(x,0,0,null, color);
    }

    public PointTimeColor(int x, Date date, String color){
        this(x,0,0,date, color);
    }

    public PointTimeColor(int x, int y, Date date){
        this(x,y,0, date, null);
    }

    public PointTimeColor(int x, int y, String color){
        this(x,y,0,null, color);
    }

    public PointTimeColor(int x, int y, Date date, String color){
        this(x,y,0,date, color);
    }

    public PointTimeColor(int x, int y, int z, Date date){
        this(x,y,z, date, null);
    }

    public PointTimeColor(int x, int y, int z, String color){
        this(x,y,z,null, color);
    }

    public PointTimeColor(int x, int y, int z, Date date, String s) {
        super(x, y, z);
        this.date = date;
        this.color = s;
    }
}